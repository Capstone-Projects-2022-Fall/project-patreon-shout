import {
    FavoriteBorder,
    Language,
    ListAlt,
    VerifiedUser,
} from "@mui/icons-material";
import React from "react";
import "./home_css/Post.css";
import TagPopUp from "./TagPopUp";

/**
 * The post object which will appear in the feed
 *
 * @param {string} title - The name of the post
 * @param {string} creator_page_url - The creator of the post
 * @param {boolean} is_public - The checkmark display for private posts
 * @param {string} content - The message contained in the post
 * @param {string} url - The url of the creator
 * @param {Date} published_at - The date and time the post was published
 *
 * @returns A single post component to be displayed in the feed
 */

function Post({title, creator_page_url, url, content, published_at, is_public}) {

    // TODO: clean this shit up
    content = content.replace(/<p[^>]*>/g, "");
    content = content.replace(/<\/?p[^>]*>/g, "");
    var posteddate = new Date(published_at);
    var year = String(posteddate.getFullYear());
    var month = String(posteddate.getMonth() + 1);
    if (month < 10) {month = "0" + month};
    var day = String(posteddate.getDate());
    if (day < 10) {day = "0" + day};
    var minute = String(posteddate.getMinutes());
    if (minute < 10) {minute = "0" + minute};
    var second = String(posteddate.getSeconds());
    if (second < 10) {second = "0" + second};
    var hour = String(posteddate.getHours());
    if (hour < 10) {hour = "0" + hour};
    if (hour === 0) {hour = "12"};
    if (hour >= 12) {hour = hour - 12; second = second + "pm"}
    else {second = second + "am"};

    const handleRedirect = (e) => {
        window.open(url, "_blank");
    }


    return (
        <div className="post">
            <div className="post__body">
                <div className="post__header">
                    <div className="post__headerText">
                        <h3>
                            {title}{" "}
                            <span className="post__headerSpecial">
                                {!is_public && <VerifiedUser className="post__badge"/>}
                                @{creator_page_url}
                            </span>
                        </h3>
                    </div>
                    <div className="post__headerDescription">
                        {content}
                    </div>
                </div>
                <div className="post__footer">
                    <div className="post__footerDateTime">
                        {month + "/" + day + "/" + year + "  " + hour + ":" + minute + ":" + second}
                    </div>
                    <div className="post__footerRedirect">
                        <Language fontSize="small" type="button" onClick={handleRedirect} hover="true"/>
                    </div>
                    <div className="post__footerTag">
                        <TagPopUp fontSize="small"/>
                    </div>
                    <div className="post__footerFavorite">
                        <FavoriteBorder fontSize="small"/>
                    </div>
                    <div className="post__footerList">
                        <ListAlt fontSize="small"/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Post;