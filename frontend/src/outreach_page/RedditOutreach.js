import React, {useState} from "react";
import './outreach_css/Outreach.css';
import RedditConnect from "../components/RedditConnect";
import TwitterConnect from "../components/TwitterConnect";
import TextField from "@mui/material/TextField";
import NewSaveButton from "../settings/NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import FakeTweet from "fake-tweet";
import avatarImg from "../img/cordturtle.png";
import publicPost from "../img/twitter-public-post-embed.jpg";
import privatePost from "../img/twitter-private-post-embed.jpg";
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";

/**
 * The instagram social platform cross-posting component
 *
 * @returns Interface for the user to cross post to instagram with custom post details
 */
function RedditOutreach({publicMessage, privateMessage, subreddit}) {

    const [subredditField, setSubredditField] = useState(subreddit);
    const [publicTextField, setPublicTextField] = useState(publicMessage);
    const [privateTextField, setPrivateTextField] = useState(privateMessage);

    return (
        <div className="outreachSettings">

            <div className="postSettings">
                <div className="connect">
                    <RedditConnect />
                </div>

                <TextField
                    fullWidth
                    margin="normal"
                    id="outlined-multiline-static"
                    label="Subreddit"
                    multiline
                    value={subredditField}
                    onChange={(e) => {setSubredditField(e.target.value);}}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    id="outlined-multiline-static"
                    label="Public"
                    multiline
                    rows={3}
                    value={publicTextField}
                    onChange={(e) => {setPublicTextField(e.target.value);}}
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
                        givenFunc={[putSocialIntegrationMessages, putSocialIntegration]}
                        funcArgs={[
                            {
                                login_token: JSON.parse(localStorage.getItem('token')).token,
                                integration_name: "REDDIT",
                                public_message: publicTextField,
                                private_message: privateTextField
                            },
                            {
                                login_token: JSON.parse(localStorage.getItem('token')).token,
                                integration_name: "REDDIT",
                                data: "::" + subredditField // ONLY USE THIS NOTATION ON REDDIT - "::subredditField" will only change the subreddit field and not the access or refresh tokens
                            }
                        ]}
                    />
                </div>
            </div>

            <div className="socialPlatformContainer">



            </div>
        </div>
    );
}

export default RedditOutreach;