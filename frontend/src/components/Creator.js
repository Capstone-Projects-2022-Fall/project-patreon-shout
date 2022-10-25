import React from "react";
import {Avatar} from "@mui/material";
import {VerifiedUser} from "@mui/icons-material";
import "./comp_css/Creator.css";


function Creator({addCreator, setCreator, displayName, urlName, description, imgUrl, verified}) {

    function wrapAddCreator(event) {
        console.log("addcreator");
        addCreator(event, urlName);
    }

    function wrapSetCreator(name) {
        console.log("setcreator: " + name);
        wrapAddCreator();
    }

    return (
        <div className="creator">
            <div className="creator__img">
                <Avatar src={imgUrl}/>
            </div>
            <div className="creator__body">
                <div className="creator__header">
                    <div className="creator__headerText">
                        <h3>
                            {displayName}{" "}
                            <span className="creator__headerSpecial">
                                {verified && <VerifiedUser className="post__badge"/>} @
                                {urlName}
                            </span>
                        </h3>
                    </div>
                    <div className="creator__headerDescription">
                        <p>{description}</p>
                    </div>
                    <div className="creator__addButton" onClick={() => { setCreator(urlName); }}>
                        Add
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Creator;