import React from "react";
import Sidebar from "../home_page/Sidebar";
import ListFeed from "./ListFeed";
import './list_css/ListPage.css';


/**
 * This is the List page function which creates the list page
 *
 * @returns The list page user interface
 */
function ListPage() {
    return (
        <div className="ListPage">
            <ListFeed />
        </div>
    );
}

export default ListPage;