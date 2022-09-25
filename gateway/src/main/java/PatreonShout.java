import com.patreonshout.output.DiscordSender;
import com.patreonshout.patreon.PostGetter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.io.IOException;

public class PatreonShout {
    static String webhook = "https://discord.com/api/webhooks/1023406452813078598/-mrW2qVevsaG3hyZa5vdtvZGaLhUr4rxgm7X_yjxPtZMaft67pwZ0-vUMQR7cmTAQVnN";
    static String token = "aMjGNuaBYN9JTkn7S0CRHAJ7LQUHX85MHa5BjetJIyc";

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
