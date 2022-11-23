import React, {useState, useCallback} from "react";
import "../home_page/home_css/Sidebar.css";
import SidebarOption from "../home_page/SidebarOption";
import HomeIcon from "@mui/icons-material/Home";
import SearchIcon from "@mui/icons-material/Search";
import ListAltIcon from "@mui/icons-material/ListAlt";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import CampaignIcon from '@mui/icons-material/Campaign';
import {useLocation, useNavigate} from "react-router-dom";
import {logoutUser} from "../services/api/logout";


/**
 * This is the Navbar Component of the website
 *
 * @returns the navbar that is used whenever the page is too small for the sidebar
 */
function NavBar() {
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
        <div className="navbar">
            <div onClick={goToHome} id="/home"> <SidebarOption size="large" Icon={HomeIcon} active={location.pathname === "/home"}/> </div>

            <div onClick={goToOutreach} id="/outreach"> <SidebarOption size="large" Icon={CampaignIcon} active={location.pathname === "/outreach"}/> </div>

            <div onClick={goToLists} id="/lists"> <SidebarOption size="large" Icon={ListAltIcon} active={location.pathname === "/lists"}/> </div>

            <div onClick={goToSettings} id="/settings"> <SidebarOption size="large" Icon={SettingsIcon} active={location.pathname === "/settings"}/> </div>

            <div onClick={logOut} id="/logout"> <SidebarOption size="large" Icon={LogoutIcon} active={location.pathname === "/logout"}/> </div>
        </div>
    );
}

export default NavBar;