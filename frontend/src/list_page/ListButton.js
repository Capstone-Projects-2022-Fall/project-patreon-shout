import React, {useState} from "react";
import "./list_css/ListButton.css";
import ListConfigModal from "./ListConfigModal";
import {getPostsFromList} from "../services/api/lists/getPostsFromList";

function ListButton({ setPostData, setPosts, setLists, added_creators, id, title, description }) {

    const [listCreators, setListCreators] = useState(added_creators);
    const [listId, setListId] = useState(id);
    const [listTitle, setListTitle] = useState(title);
    const [listDesc, setListDesc] = useState(description);

    // TODO: change getPosts with a request to get the posts for a particular listId
    const getListPosts = () => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        let mounted = true;
        getPostsFromList(loginToken, id)
            .then(items => {
                if (mounted) {
                    setPostData(items)
                }
            })
        return () => mounted = false;
    }


    const openList = () => {
        console.log("open list");
        console.log(listCreators);

        getListPosts();
        setPosts("show");
        setLists("hide");
    }

    return (
        <div className="listbutton">
            <div className="listIdentifier" onClick={openList}>
                <div id="listTitle">{listTitle}</div>
                <div id="listDescription">{listDesc}</div>
            </div>

            <ListConfigModal
                listCreators={listCreators}
                setListCreators={setListCreators}
                listId={listId}
                listTitle={listTitle}
                listDesc={listDesc}
                setListTitle={setListTitle}
                setListDesc={setListDesc}
            />
        </div>
    )
}

export default ListButton;