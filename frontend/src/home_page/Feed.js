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
    const [filterChoice, setFilterChoice] = useState("");
    const [dateRange, setDateRange] = useState([]);
    const [postList, setPostList] = useState(jsonPosts);
    const searchedList = [];
    var displayedList = [];

    let shouldSkip = false;

    postList.forEach((post, index) => {
        const postInfo = (({title, creator_page_url, content}) => ({title, creator_page_url, content}))(post);
        Object.values(postInfo).every((onlyValues, valIndex) => {
            if (shouldSkip) {return;}
            if (onlyValues.toLowerCase().includes(searchTerm)) {
                searchedList.push(post)
                shouldSkip = true;
            }
            return searchedList;
        })
        shouldSkip = false;
    });

    displayedList = [...searchedList];
    console.log(filterChoice);
    switch (filterChoice) {
        case "newestdate":
            displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
            break;
        case "oldestdate":
            displayedList = displayedList.sort(function(a, b){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
            break;
        case "privposts":
            displayedList = displayedList.filter(value => value.is_public === false);
            break;
        case "pubposts":
            displayedList = displayedList.filter(value => value.is_public === true);
            break;
        case "daterange":
            displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
            displayedList = displayedList.filter(value => (new Date(value.published_at).getTime() <= new Date(dateRange.endDate).getTime() && 
                new Date(value.published_at).getTime() >= new Date(dateRange.startDate).getTime()));
            break;
        default:
            displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
            break;
    }
    

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
            <div className="feed__filters">
                <Searchbar
                    searchTerm={searchTerm}
                    setSearchTerm={setSearchTerm}
                />
                <Filter id="feed__filter" 
                    filterChoice={filterChoice} 
                    setFilterChoice={setFilterChoice}
                    dateRange={dateRange}
                    setDateRange={setDateRange}
                />
            </div>
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
                    title={item.title}
                    creator_page_url={item.creator_page_url}
                    is_public={item.is_public}
                    content={item.content}
                    published_at={item.published_at}
                    url = {item.url}
                />
            ))}
        </div>
    );
}
export default Feed;