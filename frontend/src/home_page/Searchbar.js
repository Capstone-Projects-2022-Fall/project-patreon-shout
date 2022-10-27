import { Button } from "@mui/material";
import { SearchRounded } from "@mui/icons-material";
import React, { useState } from "react";
import "./home_css/Searchbar.css";

function Searchbar({data, setData}) {

    const [searchInput, setSearchInput] = useState("");

    let inputHandler = (e) => {
        var input = e.target.value;
        setSearchInput(input);
    }
    let buttonHandler = () => {
        console.log(data)
        setData(searchInput)
    }

    return (
        <div className="Searchbar">
            <form>
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