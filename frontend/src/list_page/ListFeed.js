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

function ListFeed() {

    const [searchTerm, setSearchTerm] = useState([]);
    const displayedList = [];

    const [userLists, setUserLists] = useState([]);
    const [posts, setPosts] = useState("hide");
    const [lists, setLists] = useState("show");
    const [postData, setPostData] = useState(jsonPosts);

    useEffect(() => {
        let mounted = true;
        const tokenString = localStorage.getItem('token');
        const userToken = JSON.parse(tokenString);
        getLists(userToken.token)
            .then(items => {
                if (mounted) {
                    console.log(items);
                    setUserLists(items)
                }
            })
        return () => mounted = false;
    }, [])


    let shouldSkip = false;

    postData.forEach((post, index) => {
        const postInfo = (({title, creator_page_url, content}) => ({title, creator_page_url, content}))(post);
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


    return (
        <div className="listfeed">
            <div className="listfeed__header">
                <h2 className={lists}>Lists</h2>
                <div id="backDiv">
                    <ArrowBackIcon id="backArrow" fontSize="large" className={posts} onClick={() => {setPosts("hide"); setLists("show");}}/>
                </div>
                <AddListModal />
            </div>

            {/*TODO: fill with list specific creator posts when we get posts assigned to creators */}
            <div className={posts}> {/* hide until a list is chosen, then do http request to get list posts */}
                <div className="feed__filters">
                    <Searchbar
                        searchTerm={searchTerm}
                        setSearchTerm={setSearchTerm}
                    />
                    <Filter id="feed__filter"/>
                </div>

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

            <div className={lists}>
                {userLists.map((item) => (
                    <ListButton
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