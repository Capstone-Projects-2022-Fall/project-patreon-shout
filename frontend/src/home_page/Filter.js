import React, {useState} from "react";
import Popup from "reactjs-popup";
import FilterListIcon from '@mui/icons-material/FilterList';
import "./home_css/Filter.css";

function Filter() {

    const [selection, setSelection] = useState("Filters");
    // const [filterChoice, setFilterChoice] = useState();

    let filterHandler = () => {}
    //     switch (filterChoice) {
    //         case "newestdate":
                
    //             break;
    //         case "oldestdate":
                
    //             break;
    //         case "privposts":
                
    //             break;
    //         case "pubposts":
                
    //             break;
    //         case "verified":
                
    //             break;
    //         case "daterange":
                
    //             break;
    //         default:
    //             break;
    //     }
    // }

    return (
        <Popup
        trigger={<div className="dropdown"><FilterListIcon/> <div>{selection} </div></div>}
        position="bottom left"
        on="click"
        contentStyle={{ padding: '0px', border: 'none' }}>
            <div className="menu">
                <div className="menu-item" id="newestdate" onClick={filterHandler}>Date (newest)</div>
                <div className="menu-item" id="oldestdate" onClick={filterHandler}>Date (oldest)</div>
                <div className="menu-item" id="privposts" onClick={filterHandler}>Private only</div>
                <div className="menu-item" id="pubposts" onClick={filterHandler}>Public only</div>
                <div className="menu-item" id="verified" onClick={filterHandler}>Verified</div>
                <div className="menu-item" id="daterange" onClick={filterHandler}>Date Range</div>
            </div>
        </Popup>
    );
}
export default Filter