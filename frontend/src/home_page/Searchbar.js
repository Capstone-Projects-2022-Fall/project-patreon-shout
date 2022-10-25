import { Button } from "@mui/material";
import { SearchRounded } from "@mui/icons-material";
import React, { useState } from "react";
import "./home_css/Searchbar.css";

function Searchbar() {

    const handleChange = (e) => {
        e.preventDefault();
        setSearch(e.target.value);
      };

//    if (searchInput.length > 0) {
//        posts.filter((post) => {
//        return post.name.match(searchInput);
//        });
//    }

    const [searchInput, setSearch] = useState("");
    return (
        <div className="Searchbar">
            <form>
                <div className="Searchbar__input">
                    <input
                        value={searchInput}
                        placeholder="Search PatreonShout..."
                        onChange={handleChange}
                        type="text"
                    />
                    <Button type="submit" className="Searchbar__button">
                        <SearchRounded/>
                    </Button>
                </div>
            </form>
        </div>
    );
}

export default Searchbar;