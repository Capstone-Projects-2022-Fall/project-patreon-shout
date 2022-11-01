import * as React from 'react';
import ResetPassModal from "./ResetPassModal";
import DropDownListItem from "./DropDownListItem";
import {List, ListItem } from '@mui/material';

function PatronSettings() {

    const [checked, setChecked] = React.useState(['discord']);

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        setChecked(newChecked);
    };

    return (

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

