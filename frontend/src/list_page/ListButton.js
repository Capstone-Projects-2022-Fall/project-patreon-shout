import React, {useState} from "react";
import "./list_css/ListButton.css";
import ListConfigModal from "./ListConfigModal";

function ListButton({ setPosts, setLists, added_creators, id, title, description }) {

    const [listCreators, setListCreators] = useState(added_creators);
    const [listId, setListId] = useState(id);
    const [listTitle, setListTitle] = useState(title);
    const [listDesc, setListDesc] = useState(description);

    const openList = () => {
        console.log("open list");
        console.log(listCreators);
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
            {/*<MoreHorizIcon id="listSetting" onClick={listSettings}/>*/}
        </div>
    )
}

export default ListButton;