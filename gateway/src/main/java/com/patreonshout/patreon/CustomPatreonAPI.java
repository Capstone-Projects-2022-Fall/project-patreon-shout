// Source code from: https://mvnrepository.com/artifact/com.patreon/patreon PatreonAPI.class

package com.patreonshout.patreon;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.patreon.PatreonAPI;
import com.patreon.resources.Campaign;
import com.patreon.resources.Pledge;
import com.patreon.resources.User;
import com.patreon.resources.Pledge.PledgeField;
import com.patreon.resources.User.UserField;
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
import java.util.Set;

import com.patreonshout.beans.PostBean;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomPatreonAPI {
    public static final String BASE_URI = System.getProperty("patreon.rest.uri", "https://www.patreon.com");
    private static final Logger log = LoggerFactory.getLogger(PatreonAPI.class);
    private final String accessToken;
    private ResourceConverter converter;

    public CustomPatreonAPI(String accessToken) {
        this.accessToken = accessToken;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.converter = new ResourceConverter(objectMapper, new Class[]{User.class, Campaign.class, Pledge.class, PostBean.class});
        this.converter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
    }

    public JSONAPIDocument<List<PostBean>> fetchPosts(String campaignId) throws IOException {
        URIBuilder pathBuilder = (new URIBuilder()).setPath("campaigns/" + campaignId + "/posts")
                .addParameter("fields[post]", "content,is_public,published_at,title,url");
        System.out.println(pathBuilder.toString());
        return this.converter.readDocumentCollection(this.getDataStream(pathBuilder.toString(), true), PostBean.class);
    }

    public JSONAPIDocument<User> fetchUser() throws IOException {
        return this.fetchUser((Collection)null);
    }

    public JSONAPIDocument<User> fetchUser(Collection<UserField> optionalFields) throws IOException {
        URIBuilder pathBuilder = (new URIBuilder()).setPath("current_user").addParameter("include", "pledges");
        if (optionalFields != null) {
            Set<UserField> optionalAndDefaultFields = new HashSet(optionalFields);
            optionalAndDefaultFields.addAll(UserField.getDefaultFields());
            this.addFieldsParam(pathBuilder, User.class, optionalAndDefaultFields);
        }

        return this.converter.readDocument(this.getDataStream(pathBuilder.toString(), false), User.class);
    }

    public JSONAPIDocument<List<Campaign>> fetchCampaigns() throws IOException {
        String path = (new URIBuilder()).setPath("current_user/campaigns").addParameter("include", "rewards,creator,goals").toString();
        System.out.println("path: " + path);
        return this.converter.readDocumentCollection(this.getDataStream(path, false), Campaign.class);
    }

    public JSONAPIDocument<List<Pledge>> fetchPageOfPledges(String campaignId, int pageSize, String pageCursor) throws IOException {
        return this.fetchPageOfPledges(campaignId, pageSize, pageCursor, (Collection)null);
    }

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

    private InputStream getDataStream(String suffix, boolean apiV2) throws IOException {
        return this.request(suffix, this.accessToken, apiV2);
    }

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

    private InputStream request(String pathSuffix, String accessToken, boolean apiV2) throws IOException {
        String prefix = PatreonAPI.BASE_URI + (apiV2 ? "/api/oauth2/v2/" : "/api/oauth2/api/");
        URL url = new URL(prefix.concat(pathSuffix));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer ".concat(accessToken));
        connection.setRequestProperty("User-Agent", String.format("Patreon-Java, version %s, platform %s %s", this.getVersion(), System.getProperty("os.name"), System.getProperty("os.version")));
        return connection.getInputStream();
    }

    private String getVersion() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/version.properties");
        Properties prop = new Properties();
        prop.load(resourceAsStream);
        return prop.getProperty("version");
    }
}

