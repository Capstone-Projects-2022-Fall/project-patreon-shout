import React, { useState } from "react";
import "./Searchbar.css";
import {useState} from "react";

/**
 * 
 * @returns Displays a Searchbar component
 */
function Searchbar() {
  const [query, setQuery] = useState("")
  return (
    <div className="Searchbar">
      <input placeholder="Search PatreonShout..." onChange={event => setQuery(event.target.value)} />
        {
          Data.map((post, index) => {
            <div key={index}>
              <p>{post.title}</p>
              <p>{post.author}</p>
            </div> })
        }
    </div>
  );
}

export default Searchbar;