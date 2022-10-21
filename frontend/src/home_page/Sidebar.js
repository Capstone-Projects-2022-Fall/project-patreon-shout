import React, {useState, useCallback} from "react";
import "./home_css/Sidebar.css";
import SidebarOption from "./SidebarOption";
import HomeIcon from "@mui/icons-material/Home";
import SearchIcon from "@mui/icons-material/Search";
import ListAltIcon from "@mui/icons-material/ListAlt";
import PermIdentityIcon from "@mui/icons-material/PermIdentity";
import SettingsIcon from "@mui/icons-material/Settings";
import LogoutIcon from "@mui/icons-material/Logout";
import { useNavigate } from "react-router-dom";
import { logoutUser } from "../services/api/logout";

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

    const logOut = () => {
        request()
        localStorage.removeItem("token");
        navigate('/');
        console.log(logout);
    }


    return (
    <div className="sidebar">
      <SidebarOption Icon={HomeIcon} text="Home" active={true} />
      <SidebarOption Icon={SearchIcon} text="Explore" />
      <SidebarOption Icon={ListAltIcon} text="Lists" />
      <SidebarOption Icon={PermIdentityIcon} text="Profile" />
      <SidebarOption Icon={SettingsIcon} text="Settings" />
      <div onClick={logOut}>
      <SidebarOption Icon={LogoutIcon} text="Logout"/>
      </div>
    </div>
  );
}

export default Sidebar;