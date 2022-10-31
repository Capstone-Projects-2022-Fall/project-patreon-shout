import {Avatar} from "@mui/material";
import {
    FavoriteBorder,
    Language,
    ListAlt,
    VerifiedUser,
} from "@mui/icons-material";
import React from "react";
import "./home_css/Post.css";

/**
 * The post object which will appear in the feed
 *
 * @param {string} displayName - The name displayed on the post
 * @param {string} username - The username displayed on the post
 * @param {boolean} verified - The checkmark display for users who have verified their Patreon with us
 * @param {string} text - The message contained in the post
 * @param {image} image - The embedded image of the post
 * @param {image} avatar - The avatar provided by the user
 *
 * @returns A single post component to be displayed in the feed
 */
function Post({displayName, username, verified, text, image, avatar}) {
    return (
        <div className="post">
            <div className="post__avatar">
                <Avatar src={avatar}/>
            </div>
            <div className="post__body">
                <div className="post__header">
                    <div className="post__headerText">
                        <h3>
                            {displayName}{" "}
                            <span className="post__headerSpecial">
                                {verified && <VerifiedUser className="post__badge"/>} @
                                {username}
                            </span>
                        </h3>
                    </div>
                    <div className="post__headerDescription">
                        <p>{text}</p>
                    </div>
                </div>
                <img src={image} alt=""/>
                <div className="post__footer">
                    <Language fontSize="small"/>
                    <FavoriteBorder fontSize="small"/>
                    <ListAlt fontSize="small"/>
                </div>
            </div>
        </div>
    );
}

export default Post;