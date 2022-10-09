import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./home_page/Home";
import Login from "./login_page/Login"

/**
 * The App function, where users can navigate to and from different pages
 * 
 * @returns The user interface and the current page the user is on
 */

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login />}>
          </Route>
          <Route path="/home" element={<Home />}>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
