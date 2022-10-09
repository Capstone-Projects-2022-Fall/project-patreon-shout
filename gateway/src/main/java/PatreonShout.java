import com.patreon.PatreonAPI;
import com.patreon.PatreonOAuth;
import com.patreon.resources.Campaign;
import com.patreonshout.beans.PostBean;
import com.patreonshout.output.DiscordSender;
import com.patreonshout.patreon.CustomPatreonAPI;
import com.patreonshout.patreon.PostGetter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.ListIterator;

/**
 * Testing how we will combine getting posts and how we will send posts to Discord
 */
public class PatreonShout {


    /**
     * webhook is the discord webhook url needed to send messages to a Discord channel
     */
    static String webhook = "";
    /**
     * token is the Patreon API token needed to get content creator posts
     */
    static String token = "";

    /**
     * Runs the {@link com.patreonshout.patreon.PostGetter} and the {@link com.patreonshout.output.DiscordSender} in
     *
     * @param args are the program arguments
     */
    public static void main(String[] args) throws IOException {


        CustomPatreonAPI apiClient = new CustomPatreonAPI("Sk-l6kVZbz8cQTManwnJVyZQec1_sBK6O-w9xT_Kfxw");
        String campaignId = apiClient.fetchCampaigns().get().get(0).getId();
        System.out.println(campaignId);

        String name = apiClient.fetchUser().get().getFullName();
        System.out.println(name);

        for (PostBean post : apiClient.fetchPosts(campaignId).get()) {
            System.out.println(post);

            // @Autowired Posts then set the creator name for each PostBean and call posts.putPost()
        }







//        PostGetter getter = new PostGetter(token);
//        DiscordSender sender = new DiscordSender(webhook);
//
//        try {
//            JSONArray posts = getter.getPosts();
//
//            for (Object post : posts) {
//                sender.setTitle("post");
//                sender.setDescription(((JSONObject) post).get("id").toString());
//
//                sender.send();
//            }
//
//            sender.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
