import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.patreon.PatreonAPI;
import com.patreon.PatreonOAuth;
import com.patreon.resources.User;
import com.patreon.resources.Campaign;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import java.io.IOException;

public class getposts {
    public static void main(String[] args) throws IOException {
        String accessToken = "pppp";

        PatreonAPI apiClient = new PatreonAPI(accessToken);
        JSONAPIDocument<List<Campaign>> campaignList = apiClient.fetchCampaigns();
        List<Campaign> campaigns = campaignList.get();
        Campaign campaign = campaigns.get(0);
        System.out.println(campaign);
        System.out.println(campaign.getCreator());
        System.out.println(campaign.getSummary());
        System.out.println(campaign.getCreatedAt());
        System.out.println(campaign.getId());
        String campaignID = campaign.getId();

//"
        try {
            URL url = new URL("https://www.patreon.com/api/oauth2/v2/campaigns/"+campaignID+"/posts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Bearer "+ accessToken);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestMethod("GET");

//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            in.close();
//            System.out.println(content);

            String inline = "";
            Scanner scanner = new Scanner(conn.getInputStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }

            //Close the scanner
            scanner.close();

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            //Get the required object from the above created object
            JSONArray dataarr = (JSONArray) data_obj.get("data");

            for (int i = 0; i < dataarr.size(); i++) {
                JSONObject new_obj = (JSONObject) dataarr.get(i);
                System.out.println(new_obj.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
