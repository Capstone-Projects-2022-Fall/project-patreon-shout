import React, { useState } from "react";
import { Tag } from "@mui/icons-material";
import Popup from "reactjs-popup";
import "./tag_css/TagPopUp.css"


function TagPopUp() {

    const [choice, setChoice] = useState();
    const [tags, setTags] = useState([]);

    function handleKeyDown(e){
        const value = e.target.value;

        if(e.key !== 'Enter'){
            return;
        }

        if(!value.trim()){
            return;
        }

        setTags([...tags, value]);

        e.target.value = '';
    }

    function removeTag(index){
        setTags(tags.filter((el, i) => i !== index));
    }

    return (
        <Popup
            trigger={<div className="dropdown"><Tag/><div>{choice}</div>
            </div>}
            position="bottom center"
            on="click"
            contentStyle={{padding: '0px', border: 'none'}}>
            <div className="App">
                <div className="tags-input-container">
                    {tags.map((tag, index) => (
                        <div className="tag-item" key={index}>
                            <span className="text">{tag}</span>
                            <span onClick={() => removeTag(index)} className="close">&times;</span>
                        </div>
                    ))}
                    <input onKeyDown={handleKeyDown} type="text" className= "tags-input" placeholder="Add a Tag"/>
                    <div className="tag-limiter">
                        <p>
                            <span>5</span> Tags remaining
                        </p>
                    </div>
                </div>
            </div>
        </Popup>
    )
}

export default TagPopUp;