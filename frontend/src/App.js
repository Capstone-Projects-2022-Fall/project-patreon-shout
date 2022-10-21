import React from "react";
import { Routes, Route } from "react-router-dom";
import './App.css';
import Home from "./home_page/Home";
import LoginPage from "./login_page/LoginPage";
import ListPage from "./list_page/ListPage";
import useToken from './services/useToken';

/**
 * The App function, where users can navigate to and from different pages
 *
 * @returns The user interface and the current page the user is on
 */
function App() {

  // session token
  const { token, setToken } = useToken();

  if (!token) {
    return <LoginPage setToken={setToken}/>
  }


  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<LoginPage setToken={setToken}/>}/>
        <Route path="/home" element={<Home/>}/>
        <Route path="/lists" element={<ListPage/>}/>
      </Routes>
    </div>
  );
}

export default App;
