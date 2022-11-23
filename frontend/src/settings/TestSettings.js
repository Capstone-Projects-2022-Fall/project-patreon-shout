import TextField from "@mui/material/TextField";
import {useEffect, useState} from "react";
import DiscordOutput from "./DiscordOutput";
import {List} from "@mui/material";
import NewSaveButton from "./NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import {getSocialIntegrationMessages} from "../services/api/webaccount/getSocialIntegrationMessages";

function TestSettings() {
    // Constants
    const loginToken = JSON.parse(localStorage.getItem('token')).token;
    const initialPublicText = "{content}";
    const initialPrivateText = "This Patreon post is exclusive to my patrons!  Visit the link above to see the post.";

    // Dynamics
    const [publicTextField, setPublicTextField] = useState(initialPublicText);
    const [privateTextField, setPrivateTextField] = useState(initialPrivateText);
    let finished = false; // Utilized inside useEffect()

    useEffect(() => {
        if (finished)
            return;

        getSocialIntegrationMessages(loginToken)
            .then(items => {
                setPublicTextField(items.discord_public_message);
                setPrivateTextField(items.discord_private_message);
            })
        return () => finished = true;
    }, []);

    return (
        <List
            id="test-settings"
            sx={{width: '100%', bgcolor: 'background.paper', paddingLeft: '1em', paddingRight: '1em'}}
        >
            <TextField
                fullWidth
                margin="normal"
                id="outlined-multiline-static"
                label="Public"
                multiline
                rows={2}
                value={publicTextField}
                onChange={(e) => setPublicTextField(e.target.value)}
            />

            <TextField
                fullWidth
                margin="normal"
                id="outlined-multiline-static"
                label="Private"
                multiline
                rows={2}
                value={privateTextField}
                onChange={(e) => setPrivateTextField(e.target.value)}
            />

            <DiscordOutput
                publicMessage={publicTextField}
                privateMessage={privateTextField}
            >
            </DiscordOutput>

            <NewSaveButton
                givenFunc={putSocialIntegrationMessages}
                funcArgs={{
                    login_token: loginToken,
                    integration_name: "DISCORD",
                    public_message: publicTextField,
                    private_message: privateTextField
                }}
            />

        </List>
    )
}

export default TestSettings;