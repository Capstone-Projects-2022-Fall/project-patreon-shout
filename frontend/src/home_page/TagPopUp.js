import React, { useState, useEffect } from "react";
import { Tag } from "@mui/icons-material";
import Popup from "reactjs-popup";
import "./tag_css/TagPopUp.css"
import { deleteTag } from "../services/api/tags/deleteTag";
import { addTag } from "../services/api/tags/addTag";
import { getTags } from "../services/api/tags/getTags";


function TagPopUp({url}) {

    const [choice, setChoice] = useState();
    const [remaining, setRemaining] = useState(5);
    const [userTags, setUserTags] = useState([]);

    function handleKeyDown(e){
        if (remaining > 0) {
            const value = e.target.value;
            if (e.key !== 'Enter'){return;}
            if (!value.trim()){return;}
            if(!userTags.includes(value)) {
                setUserTags([...userTags, value]);
                addTagRequest(value);
                setRemaining(remaining-1);
            }
            // setTags([...tags, value]);
            e.target.value = ''; 
        }  
    }

    function removeTag(tag, index){
        setUserTags(userTags.filter((el, i) => i !== index));
        // setTags(tags.filter((el, i) => i !== index));
        deleteTagRequest(tag);
        setRemaining(remaining+1);
    }


    useEffect(() => {
        let mounted = true;
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;
        getTags(login_token, url)
            .then(items => {
                if (mounted) {
                    setUserTags(items);
                    setRemaining((5 - items.length));
                }
            })
        return () => mounted = false;
    }, []);

    const deleteTagRequest = async(tag) => {
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;
        const message = await deleteTag({
            login_token,
            tag,
            url
        });

        console.log(message);
    };

    const addTagRequest = async(value) => {
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;
        const tag = value;
        const message = await addTag({
            login_token,
            tag,
            url
        });

        console.log(message);
    };

    return (
        <Popup
            trigger={<div className="dropdown"><Tag/><div>{choice}</div>
            </div>}
            position="bottom right"
            on="click"
            contentStyle={{padding: '0px', border: 'none'}}>
            <div className="App">
                <div className="tags-input-container">
                    {userTags.map((tag, index) => (
                        <div className="tag-item" key={index}>
                            <span className="text">{tag}</span>
                            <span onClick={() => removeTag(tag, index)} className="close">&times;</span>
                        </div>
                    ))}
                    <input onKeyDown={handleKeyDown} type="text" className= "tags-input" placeholder="Add a Tag"/>
                    <div className="tag-limiter">
                        <p>
                            <span>{remaining}</span> Tags Remaining
                        </p>
                    </div>
                </div>
            </div>
        </Popup>
    )
}

export default TagPopUp;