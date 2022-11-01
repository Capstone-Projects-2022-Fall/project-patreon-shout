import React, {useEffect, useState} from "react";
import Popup from "reactjs-popup";
import FilterListIcon from '@mui/icons-material/FilterList';
import { DateRangePicker } from 'react-date-range';
import "./home_css/Filter.css";
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';


function Filter({filterChoice, setFilterChoice, dateRange, setDateRange}) {

    const selectionRange = {
        startDate: new Date(),
        endDate: new Date(),
        key: 'dateRange',
    }
    const [selection, setSelection] = useState("Filters");
    const [timePeriod, setTimePeriod] = useState(selectionRange);
    const [showCalendar, setShowCalendar] = useState(true);
    

    let handleNewestDate = () => {
        setFilterChoice("newestdate");
    }

    let handleOldestDate = () => {
        setFilterChoice("oldestdate");
    }

    let handlePrivPosts = () => {
        setFilterChoice("privposts");
    }

    let handlePubPosts = () => {
        setFilterChoice("pubposts");
    }

    let handleDateRange = (e) => {
        if(e.target === e.currentTarget) {
            setShowCalendar(showCalendar => !showCalendar)
            setFilterChoice("daterange");
        } 
    }

    let handleSelectDates = (ranges) => {
        if(ranges === undefined) return;
        // console.log("DateRangePicker: ", ranges)  
        setDateRange(ranges.dateRange)
    }

    useEffect(()=>{
        if(dateRange) {
            setTimePeriod(dateRange)
        }
    }, [dateRange]);

    useEffect(()=>{
        setTimePeriod(timePeriod)
      },[timePeriod])

    useEffect(()=>{
        setTimePeriod(timePeriod)
      },[timePeriod])

    useEffect(()=>{
        setTimePeriod(timePeriod)
      },[timePeriod])

    useEffect(()=>{
        setTimePeriod(timePeriod)
      },[timePeriod])

    return (
        <Popup
        trigger={<div className="dropdown"><FilterListIcon/> <div>{selection} </div></div>}
        position="bottom left"
        on="click"
        contentStyle={{ padding: '0px', border: 'none' }}>
            <div className="menu">
                <div className="menu-item" onClick={handleNewestDate}>Date (newest)</div>
                <div className="menu-item" onClick={handleOldestDate}>Date (oldest)</div>
                <div className="menu-item" onClick={handlePrivPosts}>Private only</div>
                <div className="menu-item" onClick={handlePubPosts}>Public only</div>
                <div className="menu-item" onClick={handleDateRange}>
                    Date Range
                    <div className="dateRangePicker" hidden={showCalendar}>
                        <DateRangePicker
                            onChange={handleSelectDates}
                            ranges={[timePeriod]} 
                        />
                    </div>
                </div>
                {/* <div className="menu-item" id="searchterm" onClick={setFilterChoice("newestdate")}>{}</div> */}
            </div>
        </Popup>
    );
}
export default Filter;