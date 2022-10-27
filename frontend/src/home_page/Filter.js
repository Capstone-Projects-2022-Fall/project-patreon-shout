import React, {useState} from "react";
import Popup from "reactjs-popup";
import { Button } from "@mui/material";
import "./home_css/Filter.css";

function Filter() {

    const [selection, setSelection] = useState("We can see it");

    return (
        <Popup
        trigger={<div className="dropdown">{selection}</div>}
        position="bottom"
        on="click"
        contentStyle={{ padding: '0px', border: 'none' }}>
            <div className="menu">
                <div className="menu-item"> item 1</div>
                <div className="menu-item"> item 2</div>
                <div className="menu-item"> item 3</div>      
            </div>
        </Popup>
    );
}
export default Filter