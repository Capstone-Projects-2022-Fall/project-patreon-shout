import React, {useState} from "react";
import {Avatar} from "@mui/material";
import {VerifiedUser} from "@mui/icons-material";
import "./comp_css/Creator.css";

/**
 * This is the Creator function which will populate the posts with the Creator information
 *
 * @param addedState
 * @param curCreatorList
 * @param setCreatorList
 * @param displayName
 * @param urlName
 * @param description
 * @param imgUrl
 * @param verified
 *
 * @returns the Creator information
 * @constructor
 */
function Creator({addedState, curCreatorList, setCreatorList, displayName, urlName, description, imgUrl, verified}) {

    const [added, setAdded] = useState(addedState);

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
                        <div>
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
            </div>
        </div>
    )
}

export default Creator;