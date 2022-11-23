import React, {useState} from "react";
import TextField from "@mui/material/TextField";
import DiscordOutput from "../settings/DiscordOutput";
import NewSaveButton from "../settings/NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";

import './outreach_css/Outreach.css';

/**
 * The discord social platform cross-posting component
 *
 * @param webhook is the user's already saved discord webhook
 * @param publicMessage
 * @param privateMessage
 * @returns Interface for the user to cross post to discord with custom post details
 */
function DiscordOutreach({webhook, publicMessage, privateMessage}) {

    const [textFieldValue, setTextfieldValue] = useState(webhook);
    const [publicTextField, setPublicTextField] = useState(publicMessage);
    const [privateTextField, setPrivateTextField] = useState(privateMessage);

    return (
        <div className="outreachSettings">

            <div className="outreachConnectAndSave">
                <TextField
                    id={"discord-textfield"}
                    label={"Webhook URL"}
                    size="small"
                    variant="outlined"
                    value={textFieldValue}
                    onChange={(e) => setTextfieldValue(e.target.value)}
                />

                <div>
                    <NewSaveButton
                        givenFunc={[putSocialIntegrationMessages, putSocialIntegration]}
                        funcArgs={[
                            {
                                login_token: JSON.parse(localStorage.getItem('token')).token,
                                integration_name: "DISCORD",
                                public_message: publicTextField,
                                private_message: privateTextField
                            },
                            {
                                login_token: JSON.parse(localStorage.getItem('token')).token,
                                integration_name: "DISCORD",
                                data: textFieldValue
                            }
                        ]}
                    />
                </div>
            </div>

            <div className="postSettings">
                <TextField
                    fullWidth
                    margin="normal"
                    id="outlined-multiline-static"
                    label="Public"
                    multiline
                    rows={3}
                    value={publicTextField}
                    onChange={(e) => setPublicTextField(e.target.value)}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    id="outlined-multiline-static"
                    label="Private"
                    multiline
                    rows={3}
                    value={privateTextField}
                    onChange={(e) => setPrivateTextField(e.target.value)}
                />
            </div>

            <div className="socialPlatformContainer">
                <DiscordOutput
                    publicMessage={publicTextField}
                    privateMessage={privateTextField}
                >
                </DiscordOutput>
            </div>
        </div>
    );
}

export default DiscordOutreach;