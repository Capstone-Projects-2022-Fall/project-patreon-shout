import React, {useState} from "react";
import Popup from "reactjs-popup";
import PostAddIcon from "@mui/icons-material/PostAdd";
import Creator from "../components/Creator";
import "./list_css/AddListModal.css";


function AddListModal() {

    const [creatorList, setCreatorList] = useState([]);
    const [titleInput, setTitle] = useState("");
    const [descInput, setDesc] = useState("");
    const [searchInput, setSearch] = useState("");

    const eraseInfo = () => {
        setCreatorList([]);
        setTitle("");
        setDesc("");
        setSearch("");
    }

    const addList = () => {
        console.log("title: " + titleInput);
        console.log("desc: " + descInput);
        console.log(creatorList);
        eraseInfo();

        return true;
        // TODO: send http request to put list for user here
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
                            onChange={(e) => setSearch(e.target.value)}
                            placeholder="Search Creators"
                            type="text"
                        />
                        <input type="button" value="Create List" onClick={() => {
                            const added = addList();
                            if (added === true) {
                                close();
                            }
                            else {
                                console.log("add list error msg");
                            }
                        }}/>
                    </form>

                    <Creator
                        addedState={false}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="AlexS"
                        urlName="alex"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        addedState={false}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="AyserJ"
                        urlName="ayser"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        addedState={false}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="ChrisS"
                        urlName="chris"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        addedState={false}
                        curCreatorList={creatorList}
                        setCreatorList={setCreatorList}
                        displayName="JonahM"
                        urlName="jonah"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />
                    <Creator
                        displayName="Alex Sawicki"
                        urlName="alexzwicky"
                        description="very cool omg so cool omg"
                        imgUrl="https://i.picsum.photos/id/505/536/354.jpg?hmac=zvFVVisk0oG7zcCY4MmROU21E0SnGTOk3g2OA3fCszo"
                        verified="true"
                    />

                </div>
            )}
        </Popup>
    );
}

export default AddListModal;