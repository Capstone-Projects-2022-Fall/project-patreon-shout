import React from "react";
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./home_page/Home";
import LoginPage from "./login_page/LoginPage";
import useToken from './services/useToken';

/**
 * The App function, where users can navigate to and from different pages
 *
 * @returns The user interface and the current page the user is on
 */
function App() {
  
  const { token, setToken } = useToken();

  if (!token) { // TODO: check if session token is also valid via http request to database
    return <LoginPage setToken={setToken}/>
  }

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<LoginPage setToken={setToken}/>}/>
        <Route path="/home" element={<Home/>}/>
      </Routes>
    </div>
  );
}

export default App;
