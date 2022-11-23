import React, {useState} from "react";
import TwitterConnect from "../components/TwitterConnect";

import './outreach_css/Outreach.css';
import TextField from "@mui/material/TextField";
import NewSaveButton from "../settings/NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";
import avatarImg from "../img/cordturtle.png";
import publicPost from "../img/twitter-public-post-embed.jpg";
import privatePost from "../img/twitter-private-post-embed.jpg";
import FakeTweet from "fake-tweet";
import TwitterOutput from "../components/TwitterOutput";

/**
 * The twitter social platform cross-posting component
 *
 * @param publicMessage the public twitter message saved in the database
 * @param privateMessage the private twitter message saved in the database
 * @returns Interface for the user to cross post to twitter with custom post details
 */
function TwitterOutreach({publicMessage, privateMessage}) {

    const [publicTextField, setPublicTextField] = useState(publicMessage);
    const [privateTextField, setPrivateTextField] = useState(privateMessage);

    return (
        <div className="outreachSettings">

            <div className="postSettings">
                <div className="connect">
                    <TwitterConnect />
                </div>


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

                <div className="save">
                    <NewSaveButton
                        // givenFunc={[putSocialIntegrationMessages, putSocialIntegration]}
                        // funcArgs={[
                        //     {
                        //         login_token: JSON.parse(localStorage.getItem('token')).token,
                        //         integration_name: "DISCORD",
                        //         public_message: publicTextField,
                        //         private_message: privateTextField
                        //     },
                        //     {
                        //         login_token: JSON.parse(localStorage.getItem('token')).token,
                        //         integration_name: "DISCORD",
                        //         data: textFieldValue
                        //     }
                        // ]}
                    />
                </div>
            </div>

            <div id="renderSocialOutput" className="socialPlatformContainer">
                <TwitterOutput publicConfig={true} message={publicMessage}/>
                <TwitterOutput publicConfig={false} message={privateMessage}/>
            </div>
        </div>
    );
}

export default TwitterOutreach;