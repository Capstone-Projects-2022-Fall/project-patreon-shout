import React, {useEffect, useState} from "react";
import "./home_css/Feed.css";
import Post from "./Post";
import Searchbar from "./Searchbar";
//import { getPosts } from '../services/api/posts'
import jsonPosts from "../data/posts.json";
import Filter from "./Filter";
import {getLists} from "../services/api/lists/getLists";
import { v4 } from 'uuid';
import {getPosts} from "../services/api/posts";
import {CircularProgress} from "@mui/material";

/**
 * This is the Main Feed function which will appear on the home page
 *
 * @returns Visual representation of the list of posts
 */

function Feed() {

    const [postList, setPostList] = useState(["init"]);
    const [userLists, setUserLists] = useState(["init"]);

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

    useEffect(() => {
        let mounted = true;
        getPosts("8432541")
            .then(items => {
                if (mounted) {
                    setPostList(items)
                }
            })
        return () => mounted = false;
    }, [])


    return HtmlReturn(postList, userLists);
}

function HtmlReturn(postList, userLists) {

    const [searchTerm, setSearchTerm] = useState([]);
    const [filterChoices, setFilterChoices] = useState([]);
    const [dateRange, setDateRange] = useState([]);
    const searchedList = [];
    const avoidDefaults = ["Date(new → old)", "Date(old → new)", "Private Only", "Public Only", "Date Range"];
    let displayedList = [];

    if (postList[0] === "init" || userLists[0] === "init") {
        return (
            <div className="loading">
                <CircularProgress/>
            </div>
        );
    }

    // Searchbar Functionality
    let shouldSkip = false;
    postList.forEach((post, index) => {
        const postInfo = (({title, creator_name, content}) => ({title, creator_name, content}))(post);
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
    // console.log(filterChoices);
    if (filterChoices.includes("Date(new → old)")) {
        displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
    }
    if (filterChoices.includes("Date(old → new)")) {
        // console.log("hit");
        displayedList = displayedList.sort(function(a, b){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});
    } else {displayedList = displayedList.sort(function(b, a){return new Date(a.published_at).getTime() - new Date(b.published_at).getTime()});}
    if (filterChoices.includes("Private Only")) {
        displayedList = displayedList.filter(value => (value.is_public === "true") === false);
    }
    if (filterChoices.includes("Public Only")) {
        displayedList = displayedList.filter(value => (value.is_public === "true") === true);
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
                const postInfo = (({title, creator_name, content}) => ({title, creator_name, content}))(post);
                Object.values(postInfo).every((onlyValues) => {
                    if (shouldSkip) {return;}
                    if (onlyValues.toLowerCase().includes(element)) {
                        afterFiltersList.push(post);
                        displayedList = afterFiltersList;
                        shouldSkip = true;
                    } else {displayedList = afterFiltersList;}
                    return displayedList;
                })
                shouldSkip = false;
            });
        }
    }



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
                    key={v4()}
                    title={item.title}
                    creator_page_url={item.creator_page_url}
                    is_public={(item.is_public === "true")}
                    content={item.content}
                    published_at={item.published_at}
                    url = {item.url}
                    campaignId = {item.campaign_id}
                    creatorName = {item.creator_name}
                    lists = {userLists}
                />
            ))}
            <div className="patreonConnectPopup">

            </div>
        </div>
    );
}

export default Feed;