import com.patreonshout.output.DiscordSender;
import com.patreonshout.patreon.PostGetter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.IOException;

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
    public static void main(String[] args){

        PostGetter getter = new PostGetter(token);
        DiscordSender sender = new DiscordSender(webhook);

        try {
            JSONArray posts = getter.getPosts();

            for (Object post : posts) {
                sender.setTitle("post");
                sender.setDescription(((JSONObject) post).get("id").toString());

                sender.send();
            }

            sender.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
