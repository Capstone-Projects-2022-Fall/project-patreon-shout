import React, {useState, useCallback} from "react";
import "./home_css/Sidebar.css";
import SidebarOption from "./SidebarOption";
import HomeIcon from "@mui/icons-material/Home";
import SearchIcon from "@mui/icons-material/Search";
import ListAltIcon from "@mui/icons-material/ListAlt";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import {useNavigate} from "react-router-dom";
import {logoutUser} from "../services/api/logout";

/**
 * This is the Sidebar Component of the home page
 *
 * @returns Visual representation of the sidebar and it's options
 */
function Sidebar() {

    const navigate = useNavigate();
    const [logout, setLogoutValue] = useState([]);

    const request = useCallback(() => {
        const tokenString = localStorage.getItem('token');
        const userToken = JSON.parse(tokenString);
        logoutUser(userToken.token)
            .then(items => {
                setLogoutValue(items)
            })
    }, [])

    const goToHome = () => {
        navigate('/home')
    }

    const goToExplore = () => {
        navigate('/explore')
    }

    const goToLists = () => {
        navigate('/lists')
    }

    const goToSettings = () => {
        navigate('/settings')
    }

    const logOut = () => {
        request()
        localStorage.removeItem("token");
        navigate('/');
        console.log(logout);
    }

    return (
        <ul className="sidebar">
            <li onClick={goToHome} id="/home"><SidebarOption Icon={HomeIcon} text="Home" active="true"/></li>

            <li onClick={goToExplore} id="/explore"><SidebarOption  Icon={SearchIcon} text="Explore"/></li>

            <li onClick={goToLists} id="/lists"><SidebarOption  Icon={ListAltIcon} text="Lists"/></li>

            <li onClick={goToSettings} id="/settings"><SidebarOption  Icon={SettingsIcon} text="Settings"/></li>

            <li onClick={logOut} id="/logout"><SidebarOption Icon={LogoutIcon} text="Logout"/></li>
        </ul>
    );
}

export default Sidebar;