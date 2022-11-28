import React, {useState} from "react";
import TwitterConnect from "../components/TwitterConnect";

import './outreach_css/Outreach.css';
import TextField from "@mui/material/TextField";
import NewSaveButton from "../settings/NewSaveButton";
import {putSocialIntegrationMessages} from "../services/api/webaccount/putSocialIntegrationMessages";
import avatarImg from "../img/cordturtle.png";
import publicPost from "../img/twitter-public-post-embed.jpg";
import privatePost from "../img/twitter-private-post-embed.jpg";
import FakeTweet from "fake-tweet";
import EditDisplayMessage from "./EditDisplayMessage";

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

    // Constants
    const emptyMessage = "Empty message!";

    // Raw message containers
    let rawPublicMessage = (publicTextField && publicTextField.length > 0) ? publicTextField : emptyMessage;
    let rawPrivateMessage = (privateTextField && privateTextField.length > 0) ? privateTextField : emptyMessage;

    // Edited message containers
    let editedPublicMessage = EditDisplayMessage(rawPublicMessage, false);
    let editedPrivateMessage = EditDisplayMessage(rawPrivateMessage, false);

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
                        givenFunc={[putSocialIntegrationMessages]}
                        funcArgs={[
                            {
                                login_token: JSON.parse(localStorage.getItem('token')).token,
                                integration_name: "TWITTER",
                                public_message: publicTextField,
                                private_message: privateTextField
                            }
                        ]}
                    />
                </div>
            </div>

            <div className="socialPlatformContainer">
                <FakeTweet config={
                    {
                        user: {
                            nickname: "your username",
                            name: "Your name",
                            avatar: avatarImg,
                            verified: true,
                            locked: false
                        },
                        display: "dim",
                        text: editedPublicMessage,
                        image: rawPublicMessage.includes("{link}") ? publicPost : null,
                        date: "3:32 PM · Feb 14, 1997",
                        app: "Twitter for iPhone",
                        retweets: 3,
                        quotedTweets: 1,
                        likes: 1
                    }
                } />

                <FakeTweet config={
                    {
                        user: {
                            nickname: "your username",
                            name: "Your name",
                            avatar: avatarImg,
                            verified: true,
                            locked: false
                        },
                        display: "dim",
                        text: editedPrivateMessage,
                        image: rawPrivateMessage.includes("{link}") ? privatePost : null,
                        date: "3:32 PM · Feb 14, 1997",
                        app: "Twitter for iPhone",
                        retweets: 3,
                        quotedTweets: 1,
                        likes: 1
                    }
                } />
            </div>
        </div>
    );
}

export default TwitterOutreach;