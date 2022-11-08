import {
    FavoriteBorder,
    Language,
    ListAlt,
    VerifiedUser,
} from "@mui/icons-material";
import React, {useEffect, useState} from "react";
import "./home_css/Post.css";
import Popup from "reactjs-popup";
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import {getListsFromPost} from "../services/api/lists/getListsFromPost";
import {Button} from "@mui/material";
import CheckBox from "../components/CheckBox";

/**
 * The post object which will appear in the feed
 *
 * @param {string} title - The name of the post
 * @param {string} creator_page_url - The creator of the post
 * @param {boolean} is_public - The checkmark display for private posts
 * @param {string} content - The message contained in the post
 * @param {string} url - The url of the creator
 * @param {Date} published_at - The date and time the post was published
 * @param lists - The user's lists
 *
 * @returns A single post component to be displayed in the feed
 */

function Post({title, creator_page_url, url, content, published_at, is_public, lists}) {

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

    const [thisPostLists, setThisPostLists] = useState([]);

    useEffect(() => {
        let mounted = true;
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        getListsFromPost(loginToken, url)
            .then(items => {
                if (mounted) {
                    setThisPostLists(items);
                }
            })
        return () => mounted = false;
    }, [])

    const checkPostInList = (list_id) => {
        let insideList = false;

        thisPostLists.forEach((list) => {
            if (list.list_id === list_id && insideList === false) {
                insideList = true;
            }
        })
        return insideList;
    }

    const handleListSave = (event) => {
        event.preventDefault();

        for (let i = 0; i < lists.length; i++) {
            console.log(lists[i]);
            console.log(event.target[i].checked);
        }
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
                    <div className="post__footerFavorite">
                        <FavoriteBorder fontSize="small"/>
                    </div>

                    <div className="post__footerList">  {/*TODO*/}
                        <Popup trigger={<ListAlt fontSize="small"/>} modal>
                            {close => (
                                <div className="modalBox">
                                    <button className="close" onClick={close}>
                                        &times;
                                    </button>
                                    <div className="header">
                                        Lists
                                    </div>
                                    <br/>
                                    <form id="fields" onSubmit={handleListSave}>
                                        <FormGroup>
                                            {lists.map((item) => (
                                                <FormControlLabel
                                                    control={<CheckBox list={item} checkedDefault={checkPostInList(item.list_id)}/>}
                                                    value={item}
                                                    label=""/>
                                            ))}
                                        </FormGroup>
                                        <br/>
                                        <div id="buttonLocation">
                                            <Button disableElevation type="submit" variant="contained">Save</Button>
                                        </div>
                                    </form>
                                </div>
                            )}
                        </Popup>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Post;