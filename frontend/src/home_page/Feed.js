import React, {useEffect, useState} from "react";
import "./home_css/Feed.css";
import Post from "./Post";
import Searchbar from "./Searchbar";
import PatreonConnect from "../components/PatreonConnect";
import { getPosts } from '../services/api/posts'

/**
 * This is the Feed function which will appear on the home page
 *
 * @returns Visual representation of the list of posts
 */
function Feed() {
    const [postList, setPostList] = useState([]);

    // useEffect(() => {
    //     let mounted = true;
    //     getPosts("Alex Sawicki")
    //         .then(items => {
    //             if (mounted) {
    //                 setPostList(items)
    //             }
    //         })
    //     return () => mounted = false;
    // }, [])

    return (
        <div className="feed">
            <div className="feed__header">
                <h2>Home</h2>
            </div>
            <Searchbar/>
            <PatreonConnect/>
            {/*{postList.map((item) => (*/}
            {/*    <Post*/}
            {/*        displayName={item.title}*/}
            {/*        username={item.creator}*/}
            {/*        verified={item.verified}*/}
            {/*        text={item.content.replaceAll("<p>", "").replaceAll("</p>", "")}*/}
            {/*        avatar={item.avatar}*/}
            {/*        image={item.image}*/}
            {/*    />*/}
            {/*))}*/}
        </div>
    );
}

export default Feed;