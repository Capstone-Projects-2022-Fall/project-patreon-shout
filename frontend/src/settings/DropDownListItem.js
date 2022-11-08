import React, {useState} from 'react';
import {Button, ListItem, ListItemText, Switch} from '@mui/material';
import './setting_css/DropDownListItem.css';
import TextField from '@mui/material/TextField';
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";

function DropDownListItem({name, textfieldLabel, dropdown, defaultChecked, defaultValue, isDisabled}) {
    const [checked, setChecked] = useState(defaultChecked);
    const [showDropdown, setShowDropdown] = useState(defaultChecked && dropdown ? "show" : "hide");
    const [textfieldValue, setTextfieldValue] = useState(defaultValue);

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
                        disabled={isDisabled}
                        inputProps={{
                            'aria-labelledby': 'switch-list-label-' + {name},
                        }}
                    />
                </div>
                <div className={showDropdown}>
                    <div className="dropdown">
                        <TextField
                            id={name.toLowerCase() + "-textfield"}
                            label={textfieldLabel}
                            size="small"
                            variant="outlined"
                            value={textfieldValue}
                            disabled={isDisabled}
                            onChange={(e) => setTextfieldValue(e.target.value)}
                        />
                        <Button
                            sx={{borderRadius: 10}}
                            color="primary"
                            type="submit"
                            variant="contained"
                            size={"medium"}
                            disabled={isDisabled || !textfieldValue}
                            onClick={async () => {
                                const tokenString = localStorage.getItem('token');
                                const message = await putSocialIntegration({
                                    login_token: JSON.parse(tokenString).token,
                                    integration_name: name.toUpperCase(),
                                    data: textfieldValue
                                })

                                console.log(message)
                            }}>
                            Submit
                        </Button>
                    </div>
                </div>
            </div>
        </ListItem>
    );
}

export default DropDownListItem;