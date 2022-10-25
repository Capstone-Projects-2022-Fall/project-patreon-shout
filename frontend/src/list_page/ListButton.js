import React, {useState} from "react";
import "./list_css/ListButton.css";
import ListConfigModal from "./ListConfigModal";

function ListButton({ title, description }) {

    const [listTitle, setListTitle] = useState(title);
    const [listDesc, setListDesc] = useState(description);

    const openList = () => {
        console.log("open list");
    }



    return (
        <div className="listbutton">
            <div className="listIdentifier" onClick={openList}>
                <div id="listTitle">{listTitle}</div>
                <div id="listDescription">{listDesc}</div>
            </div>

            <ListConfigModal
                setListTitle={setListTitle}
                setListDesc={setListDesc}
            />
            {/*<MoreHorizIcon id="listSetting" onClick={listSettings}/>*/}
        </div>
    )
}

export default ListButton;