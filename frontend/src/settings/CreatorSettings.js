import React from 'react';
import { Typography, List } from '@mui/material';
import PatreonConnect from "../components/PatreonConnect";
import DropDownListItem from "./DropDownListItem";


function CreatorSettings() {

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
                dropdown="false"
            />
        </List>
    );
}

export default CreatorSettings;

