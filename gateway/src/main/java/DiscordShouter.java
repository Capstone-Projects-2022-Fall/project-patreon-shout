import java.awt.*;
import java.io.IOException;

public class DiscordShouter {
    static String WebHookURL = "https://discord.com/api/webhooks/1021120139749511229/Rv0dh9-py-8FItdomuTi4dErkhbPb4Ic3sKKChUEqgI0EqRmYfCDfueO1awSKWAnHjXO";

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
                .setDescription("suck nuts")
                .setColor(Color.GREEN)
                .addField("Left", "Low", true)
                .addField("Right", "High", false));

        webhook.execute();
    }
}
