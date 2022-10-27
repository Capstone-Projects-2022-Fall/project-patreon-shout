import React, { useState } from 'react';
import { Typography, Button, List, ListItem, ListItemText, Switch } from '@mui/material';
import PatreonConnect from "../components/PatreonConnect";
import DropDownListItem from "./DropDownListItem";


function UserSettings() {

    const [checked, setChecked] = useState(['discord']);
    const [showDiscord, setShowDiscord] = useState("hide");

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        if (newChecked.includes('discord')) {
            setShowDiscord("show");
        }
        else{
            setShowDiscord("hide");
        }

        setChecked(newChecked);

    };

    return (

        <List
            sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
        >
            <Typography mt={1} component="div" align="center">
                <PatreonConnect />
            </Typography>

            <DropDownListItem
                name="Discord"
                dropdown="true"
            />

            <DropDownListItem
                name="Twitter"
                dropdown="false"
            />


            <DropDownListItem
                name="Instagram"
                dropdown="true"
            />
        </List>
    );
}

export default UserSettings;

