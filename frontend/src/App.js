import React from "react";
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./home_page/Home";
import Login from "./login_page/Login";
import useToken from './useToken';

/**
 * The App function, where users can navigate to and from different pages
 *
 * @returns The user interface and the current page the user is on
 */
function App() {
  
  const { token, setToken } = useToken();

  if (!token) {
    return <Login setToken={setToken}/>
  }

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Login setToken={setToken}/>}/>
        <Route path="/home" element={<Home/>}/>
      </Routes>
    </div>
  );
}

export default App;
