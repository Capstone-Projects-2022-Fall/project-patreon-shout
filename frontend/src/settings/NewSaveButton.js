import React, {useState} from 'react';
import {Button} from '@mui/material';
import './setting_css/DropDownListItem.css';
import PendingIcon from '@mui/icons-material/Pending';
import ErrorIcon from '@mui/icons-material/Error';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import SaveIcon from '@mui/icons-material/Save';

/**
 * Dynamic button that changes its looks based on the status of a given function and arguments
 *
 * @param givenFunc function to call
 * @param funcArgs arguments for the given function
 * @param isDisabled Boolean for if this button should be disabled
 * @returns the interface of a save button
 */
function NewSaveButton({givenFunc, funcArgs, isDisabled}) {
    const [buttonColor, setButtonColor] = useState("primary");
    const [buttonIcon, setButtonIcon] = useState(<SaveIcon/>);
    const [isProcessing, setIsProcessing] = useState(null);

    return (
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

                    let retVals = [];
                    for (const func of givenFunc) {
                        const index = givenFunc.indexOf(func);
                        retVals[index] = await func(funcArgs[index]);
                    }

                    if (retVals.length !== givenFunc.length) {
                        setButtonColor("error");
                        setButtonIcon(<ErrorIcon/>);
                    }

                    const allEqual = arr => arr.every( v => v.status === 200)
                    if (allEqual(retVals)) {
                        setButtonColor("success");
                        setButtonIcon(<CheckCircleIcon/>);
                    }
                    else {
                        setButtonColor("error");
                        setButtonIcon(<ErrorIcon/>);
                    }

                    setIsProcessing(false);
                }}>
            Save
        </Button>
    );
}

export default NewSaveButton;