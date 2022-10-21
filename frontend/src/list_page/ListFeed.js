import React from "react";
import SearchBar from "../home_page/Searchbar";
import PatreonConnect from "../home_page/PatreonConnect";
import Post from "../home_page/Post";
import "./list_css/ListFeed.css"

function ListFeed() {


    return (
        <div className="listfeed">
            <div className="listfeed__header">
                <h2>Lists</h2>
                <h3 id="addListButton">Add List</h3>
            </div>

        </div>
    );
}

export default ListFeed;