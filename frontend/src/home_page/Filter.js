import React, {useEffect, useState} from "react";
import Box from '@mui/material/Box';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Chip from '@mui/material/Chip';
import { DateRangePicker } from 'react-date-range';
import "./home_css/Filter.css";
import 'react-date-range/dist/styles.css';
import 'react-date-range/dist/theme/default.css';
import { IconButton } from "@mui/material";
import { Close } from "@mui/icons-material";
import { getFilters } from "../services/api/filters/getFilters";
import { deleteFilter } from "../services/api/filters/deleteFilter";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
    PaperProps: {
        style: {
        maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
        width: 200,
    },
  },
};

/**
 * This is the Filters function which will appear on the home page
 * @param {array[]} filterChoices - The name of current filters
 * @param {array[]} dateRange - The range of dates including start and end
 * 
 *
 * @returns Visual representation of the filters component
 */

function Filter({filterChoices, setFilterChoices, dateRange, setDateRange}) {
    const [filterName, setFilterName] = React.useState([]);
    const [addedFilterNames, setAddedFilterNames] = useState([]);

    const selectionRange = {
        startDate: new Date(),
        endDate: new Date(),
        key: 'dateRange',
    }
    const [timePeriod, setTimePeriod] = useState(selectionRange);
    const [hideCalendar, setHideCalendar] = useState(true);

    // Constant Filters
    const filterNames = [
        "Date(new → old)",
        "Date(old → new)",
        "Private Only",
        "Public Only",
        "Date Range",
    ];

    // User added filters
    useEffect(() => {
        let mounted = true;
        const tokenString = localStorage.getItem('token');
        const userToken = JSON.parse(tokenString);
        getFilters(userToken.token)
            .then(items => {
                if (mounted) {
                    console.log(items);
                    setAddedFilterNames(items)
                }
            })
        return () => mounted = false;
    }, []);

    // Delete user filters
    const deleteFilterRequest = async(value) => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        let index = -1;
        addedFilterNames.find(function(item, i){
            if(item.filter_name === value){
              index = i;
        }});
        const filter_id = addedFilterNames[index].filter_id;
        const message = await deleteFilter({
            loginToken,
            filter_id
        });
        console.log(message);
        setAddedFilterNames(addedFilterNames.splice(index, 1));
        window.location.reload();
    };


    // Adds new filter Chip
    const handleAddChip = (e) => {
        const { target: { value }, } = e;

        // Handles Date Range Component
        if (value.includes("Date Range")) {
            setHideCalendar(false);
        } else {setHideCalendar(true); };

        // Handles letting user select conflicting chronological order
        if ((value.includes("Date(new → old)")) && (value.includes("Date(old → new)"))) {
            const ind1 = value.indexOf("Date(new → old)");
            const ind2 = value.indexOf("Date(old → new)");
            if (ind1 < ind2) {value.splice(ind1, 1)} else {value.splice(ind2, 1)};
        }

        // Handles letting user select conflicting level of privacy
        if ((value.includes("Private Only")) && (value.includes("Public Only"))) {
            const ind1 = value.indexOf("Private Only");
            const ind2 = value.indexOf("Public Only");
            if (ind1 < ind2) {value.splice(ind1, 1)} else {value.splice(ind2, 1)};
        }

        // Sets and sends filter name to feed
        setFilterName(
          typeof value === 'string' ? value.split(',') : value,
        );
        setFilterChoices(value);
    };

    // Handles React Date Picker
    let handleSelectDates = (ranges) => {
        if(ranges === undefined) return;
        setDateRange(ranges.dateRange)
        if(ranges.dateRange.startDate !== ranges.dateRange.endDate) {
            setHideCalendar(true);
        }
    };

    let handleDeleteFilter = (value) => {
        deleteFilterRequest(value)
            .then(response => {
                console.log(response);
            });
    };

    useEffect(()=>{
        if(dateRange) {
            setTimePeriod(dateRange)
        }
    }, [dateRange]);

    useEffect(()=>{
        setTimePeriod(timePeriod)
    },[timePeriod]);

    return (
        <div>
            <div className="filterContainer">
                <FormControl className="filterDropdown">
                    <InputLabel className="filter" id="filter">Filters</InputLabel>
                    <Select
                        className="filter-chip-dropdown"
                        labelId="filter-chip-dropdown"
                        id="filter-chip"
                        multiple
                        value={filterName}
                        onChange={handleAddChip}
                        input={<OutlinedInput id="select-multiple-chip" label="Chip" />}
                        renderValue={(selected) => (
                            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                            {selected.map((value) => (
                                <Chip key={value} label={value} />
                            ))}
                            </Box>
                        )}
                        MenuProps={MenuProps}
                    >
                    {filterNames.map((name) => (
                        <MenuItem key={name} value={name}>
                            {name}
                        </MenuItem>
                    ))}
                    {addedFilterNames.map(({filter_name, filter}) => (
                        <MenuItem key={filter} value={filter} className="added-filters">
                            {filter_name}
                            <IconButton className="icon-delete-button" onClick={()=>{handleDeleteFilter(filter_name)}}><Close /></IconButton>
                        </MenuItem>
                    ))}
                    </Select>
                </FormControl>
            </div>
            <div className="dateRangePopup" hidden={hideCalendar}>
                <div className="blocker" onClick={() => {setHideCalendar(true);}} > </div>
                <DateRangePicker
                    className="content"
                    showWeekNumbers={false}
                    showISOWeekNumbers={false}
                    onChange={handleSelectDates}
                    ranges={[timePeriod]} 
                />
            </div>
        </div>
    );
}
export default Filter;