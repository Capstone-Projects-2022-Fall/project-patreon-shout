import { Button } from "@mui/material";
import { SearchRounded, Add } from "@mui/icons-material";
import React, { useState } from "react";
import "./home_css/Searchbar.css";
import { addFilter } from "../services/api/filters/addFilter";

function Searchbar({searchTerm, setSearchTerm}) {

    const [searchInput, setSearchInput] = useState("");
    const [newSearchFilter, setNewSearchFilter] = useState("");

    const addFilterRequest = async e => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        const filter = newSearchFilter;
        let filter_name = "";
        if (filter.length > 10) {
            filter_name = filter.split(0, 10) + "...";
        } else {filter_name = filter};
        
        const message = await addFilter({
            loginToken,
            filter,
            filter_name,
        });

        console.log(message);
    }

    let inputHandler = (e) => {
        let input = e.target.value;
        setSearchInput(input);
        setSearchTerm(searchInput.toLowerCase());
    }

    let searchHandler = () => {
        setSearchTerm(searchInput.toLowerCase());
    }

    let addFilterHandler = () => {
        if (searchInput.length > 0) {
            setNewSearchFilter(searchInput.toLowerCase());
            addFilterRequest().then();
            console.log("Hit me (o)");
        }  
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
                    <Button className="Searchbar__button" onClick={(searchHandler)}>
                        <SearchRounded/>
                    </Button>
                    <Button className="Addfilter__button" onClick={(addFilterHandler)}>
                        Add Filter<Add/>
                    </Button>
                </div>
            </form>
        </div>

    );
}

export default Searchbar;
