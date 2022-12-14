import React, {useEffect, useState} from "react";
import ListButton from "./ListButton";
import AddListModal from "./AddListModal";
import Post from "../home_page/Post";
import "./list_css/ListFeed.css";
import {getLists} from "../services/api/lists/getLists";
import jsonPosts from "../data/posts.json";
import Searchbar from "../home_page/Searchbar";
import Filter from "../home_page/Filter";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import { getPosts } from '../services/api/posts';
import { v4 } from 'uuid';
import {CircularProgress} from "@mui/material";

/**
 * This is the List feed function to display the list feed
 *
 * @returns The list feed interface
 */

function ListFeed() {

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

    return HtmlReturn(userLists);
}

function HtmlReturn(userLists) {

    const [searchTerm, setSearchTerm] = useState([]);
    const [filterChoices, setFilterChoices] = useState([]);
    const [dateRange, setDateRange] = useState([]);
    const [postList, setPostList] = useState(["init"]);

    const searchedList = [];
    const avoidDefaults = ["Date(new → old)", "Date(old → new)", "Private Only", "Public Only", "Date Range"];
    let displayedList = [];

    const [lists, setLists] = useState("show");
    const [posts, setPosts] = useState("hide");

    if (userLists[0] === "init") {
        return (
            <div className="loading">
                <CircularProgress/>
            </div>
        );
    }

    // Searchbar Functionality
    let shouldSkip = false;
    postList.forEach((post, index) => {
        // console.log(index);

        if (post === "init") {
            return;
        }

        const postInfo = (({title, creator_name, content}) => ({title, creator_name, content}))(post);
        Object.values(postInfo).every((onlyValues) => {
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

    if (posts === "show" && postList[0] === "init") {
        return (
            <div className="loading">
                <CircularProgress/>
            </div>
        );
    }

    return (
        <div className="listfeed">
            <div className="listfeed__header">
                <h1 className={lists} id="listfeed__title">Lists</h1>
                <div id="backDiv">
                    <ArrowBackIcon id="backArrow" fontSize="large" className={posts} onClick={() => {setPosts("hide"); setLists("show"); setPostList(["init"]);}}/>
                </div>
                <AddListModal />
            </div>

            <div className={posts}> {/* hide until a list is chosen, then do http request to get list posts */}
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

                <div>
                    {displayedList.map((item) => {
                        return <Post
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
                    })}
                </div>
            </div>

            <div className={lists}>
                {userLists.map((item) => (
                    <ListButton
                        setPostData={setPostList}
                        setPosts={setPosts}
                        setLists={setLists}
                        id={item.list_id}
                        title={item.title}
                        description={item.description}
                    />
                ))}
            </div>
        </div>
    );
}

export default ListFeed;