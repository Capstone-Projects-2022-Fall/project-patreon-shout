import React, {useState} from "react";
import FakeTweet from "fake-tweet";
import avatarImg from "../img/cordturtle.png";
import publicPost from "../img/twitter-public-post-embed.jpg";
import privatePost from "../img/twitter-private-post-embed.jpg";
import reactStringReplace from "react-string-replace";


function TwitterOutput(args) { // {publicConfig, message}

    const editDisplayMessage = (givenMessage) => {
        givenMessage = reactStringReplace(givenMessage, '\\n', (match, i) => (
            <br/>
        ));
        givenMessage = reactStringReplace(givenMessage, '{content}', (match, i) => (
            publicMessageExample
        ));
        return givenMessage;
    }

    // Constants
    const publicMessageExample = "This is example text for a public post!  When you create a new Patreon post and mark " +
        "it as public, all of its text will be put here."
    const emptyMessage = "Empty message!";

    // Raw message containers
    let rawMessage = (args.message && args.message.length > 0) ? args.message : emptyMessage;

    // Edited message containers
    let editedMessage = editDisplayMessage(rawMessage);


    const config = {
        user: {
            nickname: "your username",
            name: "Your name",
            avatar: avatarImg,
            verified: true,
            locked: false
        },
        display: "dim",
        text: editedMessage,
        image: args.publicConfig ? publicPost : privatePost,
        date: "3:32 PM · Feb 14, 1997",
        app: "Twitter for iPhone",
        retweets: 3,
        quotedTweets: 1,
        likes: 1
    };


    return (
        <FakeTweet config={config} />
    );

}

export default TwitterOutput;