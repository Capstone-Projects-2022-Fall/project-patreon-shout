import { Button } from "@material-ui/core";
import React, { useState } from "react";
import "./Searchbar.css";

function Searchbar() {
  const [searchInput, setSearch] = useState("");
  return (
    <div className="Searchbar">
      <form>
        <div className="Searchbar__input">
          <input
          value={searchInput}
          onChange={(e) => setSearch(e.target.value)}
          placeholder="Search PatreonShout"
          type="text"
          />
        </div>
        <Button type="submit" className="Searchbar__button">
          Search
        </Button>
      </form>
    </div>
  );
}

export default Searchbar;