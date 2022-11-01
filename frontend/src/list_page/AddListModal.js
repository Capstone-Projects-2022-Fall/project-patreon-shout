import React, {useState} from "react";
import Popup from "reactjs-popup";
import PostAddIcon from "@mui/icons-material/PostAdd";
import Creator from "../components/Creator";
import "./list_css/AddListModal.css";
import {addList} from "../services/api/lists/addList";
import jsonCreators from "../data/creators.json";


function AddListModal() {

    // showing the options of creators
    const [stableList] = useState(jsonCreators);
    const [displayedList, setDisplayedList] = useState(jsonCreators);

    // adding creators to a list
    const [creatorList, setCreatorList] = useState([]);
    const [titleInput, setTitle] = useState("");
    const [descInput, setDesc] = useState("");
    const [searchInput, setSearch] = useState("");

    const searchCreators = (value) => {
        let filteredList = [];
        let added = false;

        stableList.forEach((creator) => {
            if (creator.displayName.toLowerCase().replaceAll(" ", "").includes(value.replaceAll(" ", "")) && added === false) {
                filteredList.push(creator);
                added = true;
            }
            if (creator.urlName.toLowerCase().replaceAll(" ", "").includes(value.replaceAll(" ", "")) && added === false) {
                filteredList.push(creator);
                added = true;
            }
            if (creator.description.toLowerCase().replaceAll(" ", "").includes(value.replaceAll(" ", "")) && added === false) {
                filteredList.push(creator);
                added = true;
            }
            added = false;
        });

        setDisplayedList(filteredList);
    }

    const eraseInfo = () => {
        setCreatorList([]);
        setTitle("");
        setDesc("");
        setSearch("");
    }

    const handleAddList = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const title = titleInput;
        const description = descInput;
        const added_creators = creatorList.toString();
        console.log(added_creators);

        const message = await addList({
            loginToken,
            title,
            description,
            added_creators
        });

        console.log(message);
        window.location.reload(false);
    }

    const handleCreateList = () => {
        console.log("title: " + titleInput);
        console.log("desc: " + descInput);
        console.log(creatorList);

        handleAddList()
            .then(response => {
                console.log(response);
            })

        eraseInfo();
        return true;
    }


    return (
        <Popup trigger={<PostAddIcon id="addListButton" />} onClose={eraseInfo} modal>
            {close => (
                <div className="modalBox">
                    <button className="close" onClick={close}>
                        &times;
                    </button>
                    <div className="header">
                        Create a new List
                    </div>

                    <form id="addListForm">
                        <input
                            value={titleInput}
                            onChange={(e) => setTitle(e.target.value)}
                            placeholder="Title"
                            type="text"
                        />
                        <input
                            value={descInput}
                            onChange={(e) => setDesc(e.target.value)}
                            placeholder="Description"
                            type="text"
                        />
                        <input
                            value={searchInput}
                            onChange={(e) => {setSearch(e.target.value); searchCreators(e.target.value);}}
                            placeholder="Search Creators"
                            type="text"
                        />
                        <input type="button" value="Create List" onClick={() => {
                            const added = handleCreateList();
                            if (added === true) {
                                close();
                            }
                            else {
                                console.log("add list error msg");
                            }
                        }}/>
                    </form>

                    {displayedList.map((item) => (
                        <Creator
                            addedState={false}
                            curCreatorList={creatorList}
                            setCreatorList={setCreatorList}
                            displayName={item.displayName}
                            urlName={item.urlName}
                            description={item.description}
                            imgUrl={item.imgUrl}
                            verified={item.verified}
                        />
                    ))}
                </div>
            )}
        </Popup>
    );
}

export default AddListModal;