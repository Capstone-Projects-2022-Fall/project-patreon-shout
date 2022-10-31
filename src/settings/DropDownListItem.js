import React, { useState } from 'react';
import { Button, ListItem, ListItemText, Switch } from '@mui/material';
import './setting_css/DropDownListItem.css';
import TextField from '@mui/material/TextField';

function DropDownListItem({dropdown, name}) {


    const [checked, setChecked] = useState([]);
    const [showDropdown, setShowDropdown] = useState("hide");

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex, 1);
        }

        if (newChecked.includes({name}.toString().toLowerCase()) && dropdown === "true") {
            setShowDropdown("show");
        }
        else{
            setShowDropdown("hide");
        }

        setChecked(newChecked);

    };

    return (
        <ListItem>
            <div classname="option">
                <div className="selector">
                    <ListItemText  primary={name} />
                    <Switch
                        edge="end"
                        onChange={handleToggle({name}.toString().toLowerCase())}
                        checked={checked.indexOf({name}.toString().toLowerCase()) !== -1}
                        inputProps={{
                            'aria-labelledby': 'switch-list-label-' + {name},
                        }}
                    />
                </div>
                <div className={showDropdown}>
                    <div className="dropdown">
                        <TextField id="outlined-basic" label={"WebHook"} size={"small"} variant="outlined" />
                        <Button sx={ { borderRadius: 10 } } color="primary" type="submit" variant="contained" size={"medium"} onClick={() => {console.log("hello");}}>
                            Submit
                        </Button>
                    </div>
                </div>
            </div>
        </ListItem>
    );
}

export default DropDownListItem;