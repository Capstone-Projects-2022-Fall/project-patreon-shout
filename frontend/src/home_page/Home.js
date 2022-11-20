import Feed from "./Feed";
import Sidebar from "./Sidebar";
import React from "react";
import './home_css/Home.css';

/**
 * This is the Home function which creates the home page
 * 
 * @returns The home page user interface
 */
function Home() {
    return (
      <div className="Home">
        <Feed />
      </div>
    );
  }
  
  export default Home;