import React from "react";
import Sidebar from "../home_page/Sidebar";
import ListFeed from "./ListFeed";
import './list_css/ListPage.css';


/**
 * This is the Home function which creates the home page
 *
 * @returns The home page user interface
 */
function ListPage() {
    return (
        <div className="ListPage">
            <Sidebar />
            <ListFeed />
        </div>
    );
}

export default ListPage;