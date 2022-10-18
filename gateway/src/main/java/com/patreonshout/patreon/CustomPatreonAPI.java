// Source code from: https://mvnrepository.com/artifact/com.patreon/patreon PatreonAPI.class

package com.patreonshout.patreon;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.SerializationFeature;
import com.patreon.PatreonAPI;
import com.patreon.resources.Campaign;
import com.patreon.resources.Pledge;
import com.patreon.resources.User;
import com.patreon.resources.Pledge.PledgeField;
import com.patreon.resources.shared.BaseResource;
import com.patreon.resources.shared.Field;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import com.patreonshout.beans.MembershipBean;
import com.patreonshout.beans.PostBean;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to send Patreon API requests
 */
public class CustomPatreonAPI {
    /**
     * log is used to send log output to the terminal
     */
    private static final Logger log = LoggerFactory.getLogger(PatreonAPI.class);
    /**
     * accessToken is used to access the patreon API for different users
     */
    private final String accessToken;
    /**
     * converter is used to convert the json data returned into an object of our choosing
     */
    private ResourceConverter converter;

    /**
     * Initializes variables needed for sending requests to the Patreon API
     *
     * @param accessToken is used to access the patreon API for different users
     */
    public CustomPatreonAPI(String accessToken) {
        this.accessToken = accessToken;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.converter = new ResourceConverter(objectMapper, new Class[]{User.class, Campaign.class, Pledge.class, PostBean.class});
//        this.converter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        this.converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
    }

    /**
     * Gets the Patreon posts from a given campaignId
     *
     * @param campaignId is the id of the campaign we want to get posts from
     * @return a {@link com.github.jasminb.jsonapi.JSONAPIDocument} containing a list of {@link PostBean} objects
     * @throws IOException when it can't parse the json request
     */
    public JSONAPIDocument<List<PostBean>> fetchPosts(String campaignId) throws IOException {
        URIBuilder pathBuilder = (new URIBuilder()).setPath("campaigns/" + campaignId + "/posts")
                .addParameter("fields[post]", "content,is_public,published_at,title,url");
        return this.converter.readDocumentCollection(this.getDataStream(pathBuilder.toString(), true), PostBean.class);
    }

    /**
     * Gets the list of campaigns that the user is either following or pledged to
     *
     * @return a list of {@link MembershipBean} objects
     * @throws IOException when it can't parse the json request
     */
    public List<MembershipBean> fetchFollowingCampaigns() throws IOException{
        // build uri
        URIBuilder pathBuilder = (new URIBuilder()).setPath("identity")
                .addParameter("include", "memberships.campaign")
                .addParameter("fields[member]", "is_follower,patron_status");

        // send request
        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(this.getDataStream(pathBuilder.toString(), true));

        // write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }

        // close the scanner
        scanner.close();

        // convert to JsonNode object
        ObjectMapper map = new ObjectMapper();
        JsonNode data = map.readTree(inline.toString()).findPath("included");

        List<MembershipBean> mbList = new ArrayList<>();

        // go through each value/2 because data is duplicated at the end of the JsonNode
        for (int i = 0; i < data.size() / 2; i++) {
            MembershipBean mb = new MembershipBean();

            mb.set_follower(data.get(i).findPath("attributes").get("is_follower").isBoolean());
            mb.setPatron_status(data.get(i).findPath("attributes").get("patron_status").asText());
            mb.setCampaignid(data.get(i).findPath("relationships").findPath("campaign").findPath("data").get("id").asInt());

            mbList.add(mb);
        }

        return mbList;
    }

    /**
     * Gets the Patreon User information
     *
     * @return User object containing user information
     * @throws IOException when it can't parse the json request
     */
    public JSONAPIDocument<User> fetchUser() throws IOException { // TODO: if we need more fields other than full_name we need to add onto this
        URIBuilder pathBuilder = (new URIBuilder()).setPath("identity").addParameter("fields[user]", "full_name");
        return this.converter.readDocument(this.getDataStream(pathBuilder.toString(), true), User.class);
    }

    /**
     * Gets the campaigns of a given access_token
     *
     * @return {@link com.github.jasminb.jsonapi.JSONAPIDocument} with a list of {@link com.patreon.resources.Campaign} objects
     * @throws IOException when it can't parse the json request
     */
    public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
        String path = (new URIBuilder()).setPath("campaigns").toString();
        return this.converter.readDocumentCollection(this.getDataStream(path, true), Campaign.class);
    }

    /**
     * Wrapper for fetching a page of pledges
     *
     * @param campaignId is the id of a campaign
     * @param pageSize is the size of a page
     * @param pageCursor is the page cursor
     * @return
     * @throws IOException when it can't parse the json request
     */
    public JSONAPIDocument<List<Pledge>> fetchPageOfPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
        return this.fetchPageOfPledges(campaignId, pageSize, pageCursor, (Collection)null);
    }

    /**
     * Gets a page of pledges from Patreon API
     *
     * @param campaignId is the id of a campaign
     * @param pageSize is the size of a page
     * @param pageCursor is the page cursor
     * @param optionalFields are the optional fields a user may want to provide for more information
     * @return a {@link com.github.jasminb.jsonapi.JSONAPIDocument} with a list of {@link com.patreon.resources.Pledge} objects
     * @throws IOException when it can't parse the json request
     */
    public JSONAPIDocument<List<Pledge>> fetchPageOfPledges(String campaignId, int pageSize, String pageCursor, Collection<PledgeField> optionalFields) throws IOException {
        URIBuilder pathBuilder = (new URIBuilder()).setPath(String.format("campaigns/%s/pledges", campaignId)).addParameter("page[count]", String.valueOf(pageSize));
        if (pageCursor != null) {
            pathBuilder.addParameter("page[cursor]", pageCursor);
        }

        if (optionalFields != null) {
            Set<PledgeField> optionalAndDefaultFields = new HashSet(optionalFields);
            optionalAndDefaultFields.addAll(PledgeField.getDefaultFields());
            this.addFieldsParam(pathBuilder, Pledge.class, optionalAndDefaultFields);
        }

        return this.converter.readDocumentCollection(this.getDataStream(pathBuilder.toString(), false), Pledge.class);
    }

    /**
     * Gets the cursor from a {@link com.github.jasminb.jsonapi.JSONAPIDocument} object
     *
     * @param document is the document to be parsed for a cursor
     * @return a String containing the cursor
     */
    public String getNextCursorFromDocument(JSONAPIDocument document) {
        Links links = document.getLinks();
        if (links == null) {
            return null;
        } else {
            Link nextLink = links.getNext();
            if (nextLink == null) {
                return null;
            } else {
                String nextLinkString = nextLink.toString();

                try {
                    List<NameValuePair> queryParameters = URLEncodedUtils.parse(new URI(nextLinkString), "utf8");
                    Iterator var6 = queryParameters.iterator();

                    while(var6.hasNext()) {
                        NameValuePair pair = (NameValuePair)var6.next();
                        String name = pair.getName();
                        if (name.equals("page[cursor]")) {
                            return pair.getValue();
                        }
                    }
                } catch (URISyntaxException var9) {
                    log.error(var9.getMessage());
                }

                return null;
            }
        }
    }

    /**
     * Gets all the pledges from Patreon API
     *
     * @param campaignId is the id of a campaign
     * @return a list of {@link com.patreon.resources.Pledge} objects
     * @throws IOException when it can't parse the json request
     */
    public List<Pledge> fetchAllPledges(String campaignId) throws IOException {
        Set<Pledge> pledges = new HashSet();
        String cursor = null;

        do {
            JSONAPIDocument<List<Pledge>> pledgesPage = this.fetchPageOfPledges(campaignId, 15, cursor);
            pledges.addAll((Collection)pledgesPage.get());
            cursor = this.getNextCursorFromDocument(pledgesPage);
        } while(cursor != null);

        return new ArrayList(pledges);
    }

    /**
     * Wrapper for the request() function
     *
     * @param suffix the suffix of the Patreon request url
     * @param apiV2 is the version of the endpoint we will be calling from Patreon
     * @return an {@link java.io.InputStream} object pointing to the Patreon api
     * @throws IOException when it can't parse the json request
     */
    private InputStream getDataStream(String suffix, boolean apiV2) throws IOException {
        return this.request(suffix, this.accessToken, apiV2);
    }

    /**
     * Adds Field parameters to the Patreon request url
     *
     * @param builder is the uri request builder
     * @param type is the type of param given to Patreon
     * @param fields are the fields we want from Patreon's type of param
     * @return a {@link org.apache.http.client.utils.URIBuilder} of the field params we want to add
     */
    private URIBuilder addFieldsParam(URIBuilder builder, Class<? extends BaseResource> type, Collection<? extends Field> fields) {
        List<String> fieldNames = new ArrayList();
        Iterator var5 = fields.iterator();

        while(var5.hasNext()) {
            Field f = (Field)var5.next();
            fieldNames.add(f.getPropertyName());
        }

        String typeStr = BaseResource.getType(type);
        builder.addParameter("fields[" + typeStr + "]", String.join(",", fieldNames));
        return builder;
    }

    /**
     * Gets the {@link java.io.InputStream} for the Patreon API request link we want to use
     *
     * @param pathSuffix is the suffix of the Patreon request url
     * @param accessToken is used to access the patreon API for a specific user's data
     * @param apiV2 is a boolean representing what version of the Patreon API we want to use
     * @return an {@link java.io.InputStream} object pointing to our Patreon API request link
     * @throws IOException when it can't parse the json request
     */
    private InputStream request(String pathSuffix, String accessToken, boolean apiV2) throws IOException {
        String prefix = PatreonAPI.BASE_URI + (apiV2 ? "/api/oauth2/v2/" : "/api/oauth2/api/");
        URL url = new URL(prefix.concat(pathSuffix));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
        connection.setRequestProperty("User-Agent", String.format("Patreon-Java, version %s, platform %s %s", this.getVersion(), System.getProperty("os.name"), System.getProperty("os.version")));
        return connection.getInputStream();
    }

    /**
     * Gets the versions of different items from the place that the request is sent
     *
     * @return a String holding the versions of different items fromt he place that the request is sent
     * @throws IOException when it can't parse the json request
     */
    private String getVersion() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
        Properties prop = new Properties();
        prop.load(resourceAsStream);
        return prop.getProperty("version");
    }
}

