import React, {useState} from 'react';
import {Button, ListItem, ListItemText, Switch} from '@mui/material';
import './setting_css/DropDownButton.css';
import SaveIcon from '@mui/icons-material/Save';
import TwitterConnect from "../components/TwitterConnect";

function SettingsDropDownButton({name, dropdown, defaultChecked, defaultValue}) {
    const [checked, setChecked] = useState(defaultChecked);
    const [showDropdown, setShowDropdown] = useState(defaultChecked && dropdown ? "show" : "hide");
    const [buttonColor, setButtonColor] = useState("primary");
    const [buttonIcon, setButtonIcon] = useState(<SaveIcon/>);

    // Button and switch toggling
    const handleToggle = () => () => {
        setChecked(!checked);
        setShowDropdown(checked && dropdown ? "hide" : "show");
    };

    return (
        <ListItem>
            <div className="option">
                <div className="selector">
                    <ListItemText primary={name}/>
                    <Switch
                        edge="end"
                        onChange={handleToggle()}
                        checked={checked}
                        inputProps={{
                            'aria-labelledby': 'switch-list-label-' + {name},
                        }}
                    />
                </div>
                <div className={showDropdown}>
                    <div className="dropdown">
                        {/*<Button*/}
                        {/*    sx={{borderRadius: 10}}*/}
                        {/*    color={buttonColor}*/}
                        {/*    type="submit"*/}
                        {/*    variant="contained"*/}
                        {/*    size={"medium"}*/}
                        {/*    onClick={() => {console.log("connect to twitter")}}*/}
                        {/*>*/}
                        {/*    Connect To Twitter*/}
                        {/*</Button>*/}
                        <TwitterConnect/>
                    </div>
                </div>
            </div>
        </ListItem>
    );
}

export default SettingsDropDownButton;