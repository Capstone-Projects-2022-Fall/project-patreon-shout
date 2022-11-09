import React, {useState} from "react";
import Popup from "reactjs-popup";
import PostAddIcon from "@mui/icons-material/PostAdd";
import "./list_css/AddListModal.css";
import {addList} from "../services/api/lists/addList";
import {Button} from "@mui/material";
import TextField from "@mui/material/TextField";


function AddListModal() {

    const [popup, showPopup] = React.useState("hide");

    const [titleInput, setTitle] = useState("");
    const [descInput, setDesc] = useState("");

    const eraseInfo = () => {
        setTitle("");
        setDesc("");
    }

    const addListRequest = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const title = titleInput;
        const description = descInput;

        const message = await addList({
            loginToken,
            title,
            description,
        });

        console.log(message);
        window.location.reload();
    }

    const addListInit = () => {

        if (titleInput.replaceAll(" ", "") === "" || descInput.replaceAll(" ", "") === "") {
            return false;
        }

        addListRequest().then()

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
                    <div className="fields">
                        <TextField id="outlined-basic" label={"Title"} size={"small"} variant="outlined" value={titleInput} onChange={(e) => setTitle(e.target.value)}/>
                        <TextField id="outlined-basic" label={"Description"} size={"small"} variant="outlined" value={descInput} onChange={(e) => setDesc(e.target.value)}/>

                        <div className={popup}>
                            <p id="errormsg"> </p>
                        </div>

                        <div className="buttonContainer">
                            <Button id="createbtn" sx={ { borderRadius: 10 } } color="primary" type="submit" variant="contained" onClick={() => {
                                if (addListInit()) {
                                    close();
                                }
                                else {
                                    document.getElementById("errormsg").textContent = "Provide Title and Description";
                                    showPopup("show");
                                }
                                }}>
                                Create
                            </Button>
                        </div>
                    </div>
                </div>
            )}
        </Popup>
    );
}

export default AddListModal;