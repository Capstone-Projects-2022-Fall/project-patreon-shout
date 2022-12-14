import {
    FavoriteBorder,
    Favorite,
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
import {updateListsForPost} from "../services/api/lists/updateListsForPost";
import ReactDOM from "react-dom";
import {pink} from "@mui/material/colors";
import {addPostToFavoritesList} from "../services/api/lists/favorites/addPostToFavoritesList";
import {deletePostFromFavoritesList} from "../services/api/lists/favorites/deletePostFromFavoritesList";
import {getCampaign} from "../services/api/campaign";

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
 * @param campaignId - the post's campaign id
 * @param creatorName - the name of the post's creator
 * @returns A single post component to be displayed in the feed
 */

function Post({title, creator_page_url, url, content, published_at, is_public, lists, campaignId, creatorName}) {

    // TODO: clean this shit up
    if (content) {
        content = content.replace(/<p[^>]*>/g, "");
        content = content.replace(/<\/?p[^>]*>/g, "");
    }
    else {
        content = "No post content";
    }
    let posteddate = new Date(published_at);
    let year = String(posteddate.getFullYear());
    let month = String(posteddate.getMonth() + 1);
    if (month < 10) {month = "0" + month};
    let day = String(posteddate.getDate());
    if (day < 10) {day = "0" + day};
    let minute = String(posteddate.getMinutes());
    if (minute < 10) {minute = "0" + minute};
    let second = String(posteddate.getSeconds());
    if (second < 10) {second = "0" + second};
    let hour = String(posteddate.getHours());
    if (hour < 10) {hour = "0" + hour};
    if (hour === 0) {hour = "12"};
    if (hour >= 12) {hour = hour - 12; second = second + "pm"}
    else {second = second + "am"};

    const handleRedirect = (target) => {
        window.open("https://www.patreon.com" + target, "_blank");
    }

    const [successMsg, setSuccessMsg] = useState("hide");

    useEffect(() => {
        let mounted = true;
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        getListsFromPost(loginToken, url)
            .then(items => {
                if (mounted) {
                    favThisPostLists = items;
                    setThisPostLists(items);
                    checkForFavoritesList();
                }
            })
        return () => mounted = false;
    }, [])

    // useEffect(() => {
    //     let mounted = true;
    //     const tokenString = localStorage.getItem('token');
    //     const loginToken = JSON.parse(tokenString).token;
    //
    //     getCampaign(campaignId, loginToken)
    //         .then(items => {
    //             if (mounted) {
    //                 creator_page_url = items.pageName;
    //             }
    //         })
    //     return () => mounted = false;
    // })

    let favThisPostLists = [];
    const [thisPostLists, setThisPostLists] = useState([]);

    const handleListSave = (event) => {
        event.preventDefault();

        let list_updates = [];
        let dom_list_updates = [];

        for (let i = 0; i < lists.length; i++) {
            list_updates.push({"list_id": lists[i].list_id.toString(), "update": event.target[i].checked.toString()})

            if (event.target[i].checked.toString() === "true") {
                dom_list_updates.push(lists[i]);
            }
        }

        // reload react dom
        favThisPostLists = dom_list_updates;
        setThisPostLists(dom_list_updates);
        // ReactDOM.render(popup, document.getElementById("popup"));

        updateListsRequest(list_updates).then(r => {

        })
    }


    const [favorite, setFavorite] = useState(false); // checkForFavoritesList


    const handleFavoriteClick = () => {
        if (favorite === false) {
            addPostToFavoritesRequest().then();
        }
        else {
            deletePostFromFavoritesRequest().then();
        }

        setFavorite(!favorite)
    }

    // checks to see if the post is favorited
    const checkForFavoritesList = () => {
        let isFavoritePost = false;

        // console.log(favThisPostLists);
        // console.log("post: " + title);
        favThisPostLists.forEach((element) => {
            // console.log("checking: " + element.title);
            if (element.title === "Favorites") {
                isFavoritePost = true;
            }
        });

        setFavorite(isFavoritePost);
    }


    const checkPostInList = (list_id) => {
        let insideList = false;

        thisPostLists.forEach((list) => {
            if (list.list_id === list_id && insideList === false) {
                insideList = true;
            }
        })
        return insideList;
    }

    const popup = (
        <form id="fields" onSubmit={handleListSave}>
            <FormGroup id="popup">
                {lists.map((item) => (
                    <FormControlLabel
                        control={<CheckBox list={item} checkPostInList={checkPostInList}/>}
                        value={item}
                        label=""/>
                ))}
            </FormGroup>
            <div className={successMsg}>
                <br/>
                <p id="errormsg"> </p>
            </div>
            <br/>
            <div id="buttonLocation">
                <Button disableElevation type="submit" variant="contained">Save</Button>
            </div>
        </form>
    );


    const updateListsRequest = async (list_updates) => {
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;

        const message = await updateListsForPost({
            url,
            login_token,
            list_updates
        });

        console.log(message);

        if (message.status === 200) {
            document.getElementById("errormsg").textContent = "Lists successfully edited";
            setSuccessMsg("show");
        }
        else {
            document.getElementById("errormsg").textContent = "Error while editing lists";
            setSuccessMsg("show");
        }
    }


    const addPostToFavoritesRequest = async e => {
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;

        const message = await addPostToFavoritesList({
            login_token,
            url
        })

        console.log(message);
    }

    const deletePostFromFavoritesRequest = async e => {
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;

        const message = await deletePostFromFavoritesList({
            login_token,
            url
        })
    }

    return (
        <div className="post">
            <div className="post__body">
                <div className="post__header">
                    <div className="post__headerText">
                        <h3>
                            <span className="postPage" id="postPage" onClick={() => {handleRedirect(url)}}>
                                {title}
                            </span>
                            {" "}
                            <span className="post__headerSpecial">
                                {is_public}
                                {!is_public && <VerifiedUser className="post__badge"/>}
                                <span id="creatorPage" onClick={() => {handleRedirect("/" + creatorName)}}>
                                    @{creatorName}
                                </span>

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
                    <div className="post_footerWidgets">
                        <div className="post__footerFavorite">
                            <div onClick={() => {handleFavoriteClick();}}>
                                {favorite ? <Favorite sx={{ color: pink[500] }} fontSize="small"/> : <FavoriteBorder sx={{ color: pink[500] }} fontSize="small"/>}
                            </div>

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
                                        <div id="popup">
                                            {popup}
                                        </div>
                                    </div>
                                )}
                            </Popup>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}





export default Post;