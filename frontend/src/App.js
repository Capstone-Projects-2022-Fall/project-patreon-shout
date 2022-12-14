import React from "react";
import {Routes, Route, useLocation} from "react-router-dom";
import './App.css';
import "@fontsource/ibm-plex-sans";
import Home from "./home_page/Home";
import Outreach from "./outreach_page/Outreach";
import LoginPage from "./login_page/LoginPage";
import ListPage from "./list_page/ListPage";
import SettingsPage from "./settings/SettingsContainers"
import useToken from './services/useToken';
import Sidebar from "./home_page/Sidebar";
import NavBar from "./components/NavBar";

/**
 * The App function, where users can navigate to and from different pages
 *
 * @returns The user interface and the current page the user is on
 */
function App() {

    // session token
    const {token, setToken} = useToken();

    // current path
    const location = useLocation();

    // if we don't login token then we go to login page
    if (!token) {
        return <LoginPage setToken={setToken}/>
    }

    // if we have login token but we are in the login page, then don't show the sidebar component
    if (token && location.pathname === "/") {
        return <LoginPage setToken={setToken}/>
    }


    return (
        <div className="App">
            <div className="column left">
                <Sidebar/>
            </div>
            <div id="middle" className="column main">
                <Routes>
                    <Route path="/" element={<LoginPage setToken={setToken}/>}/>
                    <Route path="/home" element={<Home/>}/>
                    <Route path="/outreach" element={<Outreach />}/>
                    <Route path="/lists" element={<ListPage/>}/>
                    <Route path="/settings" element={<SettingsPage/>}/>
                </Routes>
            </div>
            <div className="column right">

            </div>
            <NavBar />
        </div>
    );
}

export default App;
