import React, {useState} from 'react';
import {Button, ListItem, ListItemText, Switch} from '@mui/material';
import './setting_css/DropDownListItem.css';
import TextField from '@mui/material/TextField';
import PendingIcon from '@mui/icons-material/Pending';
import ErrorIcon from '@mui/icons-material/Error';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import SaveIcon from '@mui/icons-material/Save';
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";

function SettingsDropDownListItem({name, textfieldLabel, dropdown, defaultChecked, defaultValue, isDisabled}) {
    const [checked, setChecked] = useState(defaultChecked);
    const [showDropdown, setShowDropdown] = useState(defaultChecked && dropdown ? "show" : "hide");
    const [textfieldValue, setTextfieldValue] = useState(defaultValue);
    const [buttonColor, setButtonColor] = useState("primary");
    const [buttonIcon, setButtonIcon] = useState(<SaveIcon/>);
    const [isProcessing, setIsProcessing] = useState(null);

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
                            color={buttonColor}
                            type="submit"
                            variant="contained"
                            size={"medium"}
                            disabled={isDisabled || isProcessing}
                            endIcon={buttonIcon}
                            onClick={
                                async () => {
                                    if (isProcessing)
                                        return;

                                    setIsProcessing(true);
                                    setButtonIcon(<PendingIcon/>);

                                    const tokenString = localStorage.getItem('token');
                                    const retVal = await putSocialIntegration({
                                        login_token: JSON.parse(tokenString).token,
                                        integration_name: name.toUpperCase(),
                                        data: textfieldValue
                                    })

                                    if (retVal.status === 200) {
                                        setButtonColor("success");
                                        setButtonIcon(<CheckCircleIcon/>);
                                    } else {
                                        setButtonColor("error");
                                        setButtonIcon(<ErrorIcon/>);
                                    }

                                    setIsProcessing(false);
                                }}>
                            Save
                        </Button>
                    </div>
                </div>
            </div>
        </ListItem>
    );
}

export default SettingsDropDownListItem;