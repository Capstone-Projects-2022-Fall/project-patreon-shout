import React, {useState} from "react";
import Popup from "reactjs-popup";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import "./list_css/AddListModal.css";
import Creator from "../components/Creator";
import {deleteList} from "../services/api/lists/deleteList";
import {updateList} from "../services/api/lists/updateList";

function ListConfigModal({listCreators, setListCreators, listId, listTitle, listDesc, setListTitle, setListDesc}) {

    const [stableList] = useState(listCreators);
    const [titleInput, setTitle] = useState("");
    const [descInput, setDesc] = useState("");
    const [searchInput, setSearch] = useState("");

    const [displayedList, setDisplayedList] = useState(listCreators);

    const searchCreators = (value) => {
        let filteredList = [];

        stableList.forEach((creator) => {
            if (creator.toLowerCase().replaceAll(" ", "").includes(value.replaceAll(" ", ""))) {
                filteredList.push(creator);
            }
        });

        setDisplayedList(filteredList);
    }

    const eraseInfo = () => {
        setTitle("");
        setDesc("");
        setSearch("");
    }

    const handleEditList = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const list_id = listId;
        const title = titleInput;
        const description = descInput;
        const added_creators = listCreators.toString();

        console.log("added_creators: " + added_creators);

        const message = await updateList({
            loginToken,
            list_id,
            title,
            description,
            added_creators
        })

        console.log(message);
        window.location.reload(false);
    }

    const setupEditList = () => {
        setListTitle(titleInput);
        setListDesc(descInput);

        handleEditList()
            .then(response => {
                // console.log(response); // sends undefined :(
            })

        eraseInfo()
        return true;
    }


    const handleDeleteList = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const list_id = listId;

        const message = await deleteList({
            loginToken,
            list_id
        });

        console.log(message);
        window.location.reload(false);

    }

    const setupDeleteList = () => {

        handleDeleteList()
            .then(response => {
                console.log(response);
            })

        return true;
    }

    return (
        <Popup trigger={<MoreHorizIcon id="listSetting"/>} onClose={eraseInfo} modal>
            {close => (
                <div className="modalBox">
                    <button className="close" onClick={close}>
                        &times;
                    </button>
                    <div className="header">
                        List Settings
                    </div>

                    <form id="addListForm">
                        <input
                            value={titleInput}
                            onChange={(e) => setTitle(e.target.value)}
                            placeholder={listTitle || "Title"}
                            type="text"
                        />
                        <input
                            value={descInput}
                            onChange={(e) => setDesc(e.target.value)}
                            placeholder={listDesc || "Description"}
                            type="text"
                        />
                        <input
                            value={searchInput}
                            onChange={(e) => {setSearch(e.target.value); searchCreators(e.target.value);}}
                            placeholder="Search Creators"
                            type="text"
                        />
                        <input type="button" value="Edit List" onClick={() => {
                            const edited = setupEditList();
                            if (edited === true) {
                                close()
                            }
                            else {
                                console.log("edit list error msg");
                            }
                        }}/>
                        <input type="button" value="Delete List" onClick={() => {
                            const deleted = setupDeleteList();
                            if (deleted === true) {
                                close()
                            }
                            else {
                                console.log("something went wrong with deleting the list");
                            }
                        }}/>

                    </form>
                    {displayedList.map((item) => (
                        <Creator
                            addedState={true}
                            curCreatorList={listCreators}
                            setCreatorList={setListCreators}
                            displayName={item}
                            urlName={item}
                            description="very unique accurate cool description"
                            imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                            verified="true"
                        />
                    ))}

                </div>
            )}




        </Popup>
    );

}

export default ListConfigModal;