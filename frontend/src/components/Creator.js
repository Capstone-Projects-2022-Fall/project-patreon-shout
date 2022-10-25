import React, {useState} from "react";
import {Avatar} from "@mui/material";
import {VerifiedUser} from "@mui/icons-material";
import "./comp_css/Creator.css";


function Creator({curCreatorList, setCreatorList, displayName, urlName, description, imgUrl, verified}) {

    const [added, setAdded] = useState(false);

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
                    {!added ? <div className="creator__addButton" onClick={() => {
                            setAdded(s => !s);
                            setCreatorList(curCreatorList => [...curCreatorList, urlName]);
                        }}>
                        Add
                    </div> : null}
                    {added ? <div className="creator__addButton" onClick={() => {
                        setAdded(s => !s);
                        setCreatorList(curCreatorList.filter(name => name !== urlName));
                    }}>
                    Remove
                    </div> : null}
                </div>
            </div>
        </div>
    )
}

export default Creator;