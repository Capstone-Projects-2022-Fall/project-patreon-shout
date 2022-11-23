import React, {useState} from "react";
import TwitterConnect from "../components/TwitterConnect";

import './outreach_css/Outreach.css';
import TextField from "@mui/material/TextField";
import NewSaveButton from "../settings/NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";
import avatarImg from "../img/cordturtle.png"
import FakeTweet from "fake-tweet";

/**
 * The twitter social platform cross-posting component
 *
 * @returns Interface for the user to cross post to twitter with custom post details
 */
function TwitterOutreach({publicMessage, privateMessage}) {

    const [publicTextField, setPublicTextField] = useState(publicMessage);
    const [privateTextField, setPrivateTextField] = useState(privateMessage);


    const publicConfig = {
        user: {
            nickname: "your username",
            name: "Your name",
            avatar: avatarImg,
            verified: true,
            locked: false
        },
        display: "dim",
        text: publicTextField,
        image: avatarImg,
        date: "3:32 PM · Feb 14, 1997",
        app: "Twitter for iPhone",
        retweets: 3,
        quotedTweets: 1,
        likes: 1
    };

    const privateConfig = {
        user: {
            nickname: "your username",
            name: "Your name",
            avatar: avatarImg,
            verified: true,
            locked: false
        },
        display: "dim",
        text: privateTextField,
        image: avatarImg,
        date: "3:32 PM · Feb 14, 1997",
        app: "Twitter for iPhone",
        retweets: 3,
        quotedTweets: 1,
        likes: 1
    };

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

            <div className="socialPlatformContainer">
                <FakeTweet config={publicConfig} />
            </div>

            <div className="socialPlatformContainer">
                <FakeTweet config={privateConfig} />
            </div>
        </div>
    );
}

export default TwitterOutreach;