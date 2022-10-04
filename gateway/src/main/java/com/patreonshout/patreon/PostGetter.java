package com.patreonshout.patreon;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.PatreonAPI;
import com.patreon.resources.Campaign;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Gets Posts from a Patreon content creator in {@link org.json.simple.JSONArray} form
 */
public class PostGetter {

    /**
     * apiClient is the object used to connect to Patreon's API
     */
    private PatreonAPI apiClient;
    /**
     * Patreon API token needed to provide authorization to get Patreon content creator information
     */
    private String token;

    /**
     * Initialize {@link com.patreon.PatreonAPI} and set PatreonAPI token
     *
     * @param token the Patreon API token needed to provide authorization to get Patreon content creator information
     */
    public PostGetter(String token) {
        this.token = token;
        apiClient = new PatreonAPI(token);
    }

    /**
     * Returns a {@link org.json.simple.JSONArray} with all the content creator's posts
     *
     * @return a {@link org.json.simple.JSONArray} of content creator posts
     * @throws IOException in case the HTTP connection fails
     * @throws ParseException in case the scanner fails
     */
    public JSONArray getPosts() throws IOException, ParseException {

        JSONAPIDocument<List<Campaign>> campaignList = apiClient.fetchCampaigns();
        Campaign campaign = campaignList.get().get(0);

        URL url = new URL("https://www.patreon.com/api/oauth2/v2/campaigns/"+ campaign.getId() +"/posts");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Bearer "+ token);
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestMethod("GET");

        StringBuilder inline = new StringBuilder();
        Scanner scanner = new Scanner(conn.getInputStream());

        //Write all the JSON data into a string using a scanner
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }

        //Close the scanner
        scanner.close();

        //Using the JSON simple library parse the string into a json object
        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(inline.toString());

        //Get the required object from the above created object
        return (JSONArray) data_obj.get("data"); // dataarr = this line
        //        for (int i = 0; i < dataarr.size(); i++) {
//            JSONObject new_obj = (JSONObject) dataarr.get(i);
//            System.out.println(new_obj.toString());
//        }
    }



}
