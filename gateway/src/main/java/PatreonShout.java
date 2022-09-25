import com.patreonshout.output.DiscordSender;
import com.patreonshout.patreon.PostGetter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.IOException;

public class PatreonShout {
    static String webhook = "";
    static String token = "";

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
