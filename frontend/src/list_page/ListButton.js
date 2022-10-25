import React from "react";
import "./list_css/ListButton.css";
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';

function ListButton({ title, description }) {

    const openList = () => {
        console.log("open list");
    }

    const listSettings = () => {
        console.log("list settings popup open");
    }

    return (
        <div className="listbutton">
            <div className="listIdentifier" onClick={openList}>
                <div id="listTitle">{title}</div>
                <div id="listDescription">{description}</div>
            </div>

            <MoreHorizIcon id="listSetting" onClick={listSettings}/>
        </div>
    )
}

export default ListButton;