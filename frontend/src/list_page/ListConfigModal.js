import React, {useState} from "react";
import Popup from "reactjs-popup";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import "./list_css/AddListModal.css";
import Creator from "../components/Creator";

function ListConfigModal({setListTitle, setListDesc}) {

    const [creatorList, setCreatorList] = useState([]);
    const [titleInput, setTitle] = useState("");
    const [descInput, setDesc] = useState("");
    const [searchInput, setSearch] = useState("");

    const eraseInfo = () => {
        setTitle("");
        setDesc("");
        setSearch("");
    }

    const editList = () => {
        setListTitle(titleInput)
        setListDesc(descInput)
        eraseInfo()
        return true;
    }

    const deleteList = () => {
        console.log("delete");
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
                            onChange={(e) => setSearch(e.target.value)}
                            placeholder="Search Creators"
                            type="text"
                        />
                        <input type="button" value="Edit List" onClick={() => {
                            const edited = editList();
                            if (edited === true) {
                                close()
                            }
                        }}/>
                        <input type="button" value="Delete List" onClick={deleteList}/>

                    </form>

                    <Creator
                        addedState={true}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="AlexS"
                        urlName="alex"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        addedState={true}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="AyserJ"
                        urlName="ayser"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        addedState={true}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="ChrisS"
                        urlName="chris"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        addedState={true}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="JonahM"
                        urlName="jonah"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />

                </div>
            )}




        </Popup>
    );

}

export default ListConfigModal;