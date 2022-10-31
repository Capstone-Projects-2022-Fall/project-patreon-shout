import { Button } from "@mui/material";
import { SearchRounded } from "@mui/icons-material";
import React, { useState } from "react";
import "./home_css/Searchbar.css";

function Searchbar({searchTerm, setSearchTerm}) {

    const [searchInput, setSearchInput] = useState("");

    let inputHandler = (e) => {
        var input = e.target.value;
        setSearchInput(input);
    }
    let buttonHandler = () => {
        setSearchTerm(searchInput.toLowerCase())
    }
    //This is currently preventing Enter from being the input method, only search button
    const onSubmitForm = e => {
        e.preventDefault();
    }

    return (
        <div className="Searchbar">
            <form onSubmit={onSubmitForm}>
                <div className="Searchbar__input">
                    <input
                        value={searchInput}
                        placeholder="Search PatreonShout..."
                        onChange={inputHandler}
                        type="text"
                    />
                    <Button className="Searchbar__button" onClick={(buttonHandler)}>
                        <SearchRounded/>
                    </Button>
                </div>
            </form>
        </div>

    );
}

export default Searchbar;
