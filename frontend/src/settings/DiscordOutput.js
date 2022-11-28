import {
    DiscordEmbed,
    DiscordEmbedField,
    DiscordEmbedFields,
    DiscordMessage,
    DiscordMessages
} from "@skyra/discord-components-react";
import './setting_css/DiscordMessages.css';
import EditDisplayMessage from "../outreach_page/EditDisplayMessage";

/**
 * Function component that displays text given in a Discord-like fashion for users to understand how their text
 * will look when redirected
 *
 * @param args publicMessage and privateMessage texts to be used in the public and private text fields
 * @returns {JSX.Element}
 */
function DiscordOutput(args) {
    // Constants
    const emptyMessage = "Empty message!";

    // Raw message containers
    let publicMessage = (args.publicMessage && args.publicMessage.length > 0) ? args.publicMessage : emptyMessage;
    let privateMessage = (args.privateMessage && args.privateMessage.length > 0) ? args.privateMessage : emptyMessage;

    // Edited message containers
    let editedPublicMessage = EditDisplayMessage(publicMessage, true, "#01AFF4");
    let editedPrivateMessage = EditDisplayMessage(privateMessage, true, "#01AFF4");

    return (
        <DiscordMessages className="discord-messages">
            <DiscordMessage author="PatreonShout" bot={true}>
                <DiscordEmbed slot="embeds" color="#00FF00">
                    <DiscordEmbedFields slot="fields">
                        <DiscordEmbedField fieldTitle="This will be the title of your public Patreon post" inline>
                            {editedPublicMessage}⠀{/* Leave this empty unicode character!  It will break the component otherwise. */}
                        </DiscordEmbedField>
                    </DiscordEmbedFields>
                </DiscordEmbed>
            </DiscordMessage>

            <DiscordMessage author="PatreonShout" bot={true}>
                <DiscordEmbed slot="embeds" color="#FF0000">
                    <DiscordEmbedFields slot="fields">
                        <DiscordEmbedField fieldTitle="This will be the title of your private Patreon post" inline>
                            {editedPrivateMessage}⠀{/* Leave this empty unicode character!  It will break the component otherwise. */}
                        </DiscordEmbedField>
                    </DiscordEmbedFields>
                </DiscordEmbed>
            </DiscordMessage>
        </DiscordMessages>
    );
}

export default DiscordOutput;