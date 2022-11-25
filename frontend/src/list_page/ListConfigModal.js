import React, {useState} from "react";
import Popup from "reactjs-popup";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import "./list_css/AddListModal.css";
import {deleteList} from "../services/api/lists/deleteList";
import {updateList} from "../services/api/lists/updateList";
import TextField from "@mui/material/TextField";
import {Button} from "@mui/material";

/**
 * This is the List config modal function which creates the list options
 *
 * @returns The list options allowing users to create new lists and edit them
 */

function ListConfigModal({listCreators, setListCreators, listId, listTitle, listDesc, setListTitle, setListDesc}) {

    const [popup, showPopup] = React.useState("hide")

    const [titleInput, setTitle] = useState("");
    const [descInput, setDesc] = useState("");


    const eraseInfo = () => {
        setTitle("");
        setDesc("");
    }

    const editListRequest = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const list_id = listId;
        const title = titleInput;
        const description = descInput;


        const message = await updateList({
            loginToken,
            list_id,
            title,
            description,
        })

        console.log(message);
        window.location.reload();
    }

    const editListInit = () => {

        if (titleInput.replaceAll(" ", "") === "" || descInput.replaceAll(" ", "") === "") {
            return false;
        }

        editListRequest().then()

        setListTitle(titleInput);
        setListDesc(descInput);

        eraseInfo()
        return true;
    }


    const deleteListRequest = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const list_id = listId;

        const message = await deleteList({
            loginToken,
            list_id
        });

        console.log(message);
        window.location.reload();

    }

    const deleteListInit = () => {

        deleteListRequest()
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
                        Edit "{listTitle}"
                    </div>
                    <div className="fields">
                        <TextField id="outlined-basic" label={"Title"} size={"small"} variant="outlined" value={titleInput} onChange={(e) => setTitle(e.target.value)}/>
                        <TextField id="outlined-basic" label={"Description"} size={"small"} variant="outlined" value={descInput} onChange={(e) => setDesc(e.target.value)}/>

                        <div className={popup}>
                            <p id="errormsg"> </p>
                        </div>

                        <div className="buttonContainer">
                            <Button id="savebtn" sx={ { borderRadius: 10 } } color="primary" type="submit" variant="contained"  onClick={() => {
                                    if (editListInit()){
                                        close();
                                    }
                                    else {
                                        document.getElementById("errormsg").textContent = "Provide Title and Description";
                                        showPopup("show");
                                    }
                                }}>
                                Save
                            </Button>
                            <Button id="deletebtn" sx={ { borderRadius: 10 } } color="primary" type="submit" variant="contained"  onClick={() => {deleteListInit(); close();}}>
                                Delete
                            </Button>
                        </div>
                    </div>
                </div>
            )}




        </Popup>
    );

}

export default ListConfigModal;