import * as React from 'react';
import ResetPassModal from "./ResetPassModal";
import DropDownListItem from "./DropDownListItem";
import {List, ListItem, ListItemText, Switch, Typography} from '@mui/material';

function PatronSettings() {

    return(

        <List
            sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}
        >

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

            <ResetPassModal/>

        </List>
    );
}

export default PatronSettings;

