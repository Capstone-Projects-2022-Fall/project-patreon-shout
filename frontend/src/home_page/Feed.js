import React, {useEffect, useState} from "react";
import "./home_css/Feed.css";
import Post from "./Post";
import Searchbar from "./Searchbar";
//import { getPosts } from '../services/api/posts'
import jsonPosts from "../data/posts.json";
import Filter from "./Filter";
import {getLists} from "../services/api/lists/getLists";

/**
 * This is the Feed function which will appear on the home page
 *
 * @returns Visual representation of the list of posts
 */

function Feed() {

    const [searchTerm, setSearchTerm] = useState([]);
    const [filterChoices, setFilterChoices] = useState([]);
    const [dateRange, setDateRange] = useState([]);
    const [postList, setPostList] = useState(jsonPosts);
    const [userLists, setUserLists] = useState([]);
    const searchedList = [];
    const avoidDefaults = ["Date(new → old)", "Date(old → new)", "Private Only", "Public Only", "Date Range"];
    let displayedList = [];
    let shouldSkip = false;

    // Searchbar Functionality
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

    // Filter Functionality
    displayedList = [...searchedList];
    console.log(filterChoices);
    if (filterChoices.includes("Date(new → old)")) {
        displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
    }
    if (filterChoices.includes("Date(old → new)")) {
        console.log("hit");
        displayedList = displayedList.sort(function(a, b){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
    } else {displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});}
    if (filterChoices.includes("Private Only")) {
        displayedList = displayedList.filter(value => value.is_public === false);
    }
    if (filterChoices.includes("Public Only")) {
        displayedList = displayedList.filter(value => value.is_public === true);
    }
    if (filterChoices.includes("Date Range")) {
        displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
        displayedList = displayedList.filter(value => (new Date(value.published_at).getTime() <= new Date(dateRange.endDate).getTime() && 
            new Date(value.published_at).getTime() >= new Date(dateRange.startDate).getTime()));
    }

    for(const element of filterChoices) {
        if (!avoidDefaults.includes(element)) {
            let afterFiltersList = [];
            shouldSkip=false;
            displayedList.forEach((post) => {
                const postInfo = (({title, creator_page_url, content}) => ({title, creator_page_url, content}))(post);
                Object.values(postInfo).every((onlyValues) => {
                    if (shouldSkip) {return;}
                    if (onlyValues.toLowerCase().includes(element)) {
                        afterFiltersList.push(post);
                        displayedList = afterFiltersList;
                        shouldSkip = true;
                    }
                    return displayedList;
                })
                shouldSkip = false;
            });
        }
    }  

    useEffect(() => {
        let mounted = true;
        const tokenString = localStorage.getItem('token');
        const userToken = JSON.parse(tokenString);
        getLists(userToken.token)
            .then(items => {
                if (mounted) {
                    setUserLists(items)
                }
            })
        return () => mounted = false;
    }, [])

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
                <h1>Patreon Shout</h1>
            </div>
            <div className="feed__filters">
                <Searchbar
                    searchTerm={searchTerm}
                    setSearchTerm={setSearchTerm}
                />
                <Filter id="feed__filter"
                    filterChoices={filterChoices} 
                    setFilterChoices={setFilterChoices}
                    dateRange={dateRange}
                    setDateRange={setDateRange}
                />
            </div>
            {displayedList.map((item) => (
                <Post
                    title={item.title}
                    creator_page_url={item.creator_page_url}
                    is_public={item.is_public}
                    content={item.content}
                    published_at={item.published_at}
                    url = {item.url}
                    lists = {userLists}
                />
            ))}
        </div>
    );
}
export default Feed;