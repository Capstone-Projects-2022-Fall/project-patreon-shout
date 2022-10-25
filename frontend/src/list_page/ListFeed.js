import React from "react";
import ListButton from "./ListButton";
import AddListModal from "./AddListModal";
import Post from "../home_page/Post";
import "./list_css/ListFeed.css";

function ListFeed() {


    return (
        <div className="listfeed">
            <div className="listfeed__header">
                <h2>Lists</h2>
                <AddListModal />
            </div>

            <div className="listfeed__posts"> {/* hide until a list is chosen, then do http request to get list posts */}
                <Post
                    displayName="Alex"
                    username="Alex2"
                    verified="true"
                    text="text text text text hello there alex"
                    avatar="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                />
            </div>

            <div className="listfeed__lists" >
                <ListButton
                    title="hi alex"
                    description="alex number 2"
                />
                <ListButton
                    title="hi alex"
                    description="alex number 2"
                />
                <ListButton
                    title="hi alex"
                    description="alex number 2"
                />
                <ListButton
                    title="hi alex"
                    description="alex number 2"
                />
            </div>
        </div>
    );
}

export default ListFeed;