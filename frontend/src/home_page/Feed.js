import React, {useEffect, useState} from "react";
import "./home_css/Feed.css";
import Post from "./Post";
import Searchbar from "./Searchbar";
//import { getPosts } from '../services/api/posts'
import jsonPosts from "../data/posts.json";
import Filter from "./Filter";

/**
 * This is the Feed function which will appear on the home page
 *
 * @returns Visual representation of the list of posts
 */

function Feed() {

    const [searchTerm, setSearchTerm] = useState([]);
    const [postList, setPostList] = useState(jsonPosts);
    const displayedList = [];

    let postcount = postList.length;
    let shouldSkip = false;

    postList.forEach((post, index) => {
        const postInfo = (({displayName, username, text}) => ({displayName, username, text}))(post);
        Object.values(postInfo).every((onlyValues, valIndex) => {
            if (shouldSkip) {return;}
            if (onlyValues.toLowerCase().includes(searchTerm)) {
                displayedList.push(post)
                shouldSkip = true;
            }
            return displayedList;
        })
        shouldSkip = false;
    });

        // if ((searchTerm!=null) && ((postList.forEach(displayName, i).includes(searchTerm)) || (postList[i].username.includes(searchTerm)) || (postList[i].text.includes(searchTerm)))) {
        // }


    // useEffect(() => {
    //     let mounted = true;
    //     getPosts("alexzwicky")
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
            <Searchbar
                searchTerm={searchTerm}
                setSearchTerm={setSearchTerm}
            />
            <Filter/>
            {/* Unsearched map of posts, don't delete yet
                {postList.map((item) => (
                <Post
                    displayName={item.displayName}
                    username={item.username}
                    verified={item.verified}
                    text={item.text}
                    avatar={item.avatar}
                />
            ))} */}
            {displayedList.map((item) => (
                <Post
                    displayName={item.displayName}
                    username={item.username}
                    verified={item.verified}
                    text={item.text}
                    avatar={item.avatar}
                />
            ))}
        </div>
    );
}
export default Feed;
