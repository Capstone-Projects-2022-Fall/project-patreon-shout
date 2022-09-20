import java.awt.*;
import java.io.IOException;

public class PatreonShout {
    static String WebHookURL = "INSERT WEBHOOK URL HERE";

    public static void main(String[] args) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(WebHookURL);


        webhook.setContent("Test");
        webhook.setUsername("PatreonShout");
        webhook.setEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Title")
                .setDescription("TESTING123123123")
                .setColor(Color.RED)
                .addField("1st Field", "Test1", true)
                .addField("2nd Field", "Test2", false)
                .addField("3rd Field", "Test3", false));

        webhook.setEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("2nd Title")
                .setDescription("DEEZ")
                .setColor(Color.GREEN)
                .addField("Left", "Low", true)
                .addField("Right", "High", false));

        webhook.execute();
    }
}
