import React, {useState, useCallback} from "react";
import "./home_css/Sidebar.css";
import SidebarOption from "./SidebarOption";
import HomeIcon from "@mui/icons-material/Home";
import SearchIcon from "@mui/icons-material/Search";
import ListAltIcon from "@mui/icons-material/ListAlt";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import {useLocation, useNavigate} from "react-router-dom";
import {logoutUser} from "../services/api/logout";

/**
 * This is the Sidebar Component of the website
 *
 * @returns Visual representation of the sidebar and it's options
 */
function Sidebar() {

    const location = useLocation();
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

    const refreshIfCurrentPath = navTo => {
        if (navTo === location.pathname) {
            navigate(0);
        }
    }

    const goToHome = () => {
        refreshIfCurrentPath('/home')
        navigate('/home')
    }

    const goToOutreach = () => {
        refreshIfCurrentPath('/outreach')
        navigate('/outreach')
    }

    const goToLists = () => {
        refreshIfCurrentPath('/lists')
        navigate('/lists')
    }

    const goToSettings = () => {
        refreshIfCurrentPath('/settings')
        navigate('/settings')
    }

    const logOut = () => {
        request()
        localStorage.removeItem("token");
        navigate('/');
        console.log(logout);
    }

    return (

        <div className="sidebar">
            <div onClick={goToHome} id="/home"> <SidebarOption Icon={HomeIcon} text="Home" active={location.pathname === "/home"}/> </div>

            <div onClick={goToOutreach} id="/outreach"> <SidebarOption Icon={SearchIcon} text="Outreach" active={location.pathname === "/outreach"}/> </div>

            <div onClick={goToLists} id="/lists"> <SidebarOption Icon={ListAltIcon} text="Lists" active={location.pathname === "/lists"}/> </div>

            <div onClick={goToSettings} id="/settings"> <SidebarOption Icon={SettingsIcon} text="Settings" active={location.pathname === "/settings"}/> </div>

            <div onClick={logOut} id="/logout"> <SidebarOption Icon={LogoutIcon} text="Logout" active={location.pathname === "/logout"}/> </div>
        </div>
    );
}

export default Sidebar;