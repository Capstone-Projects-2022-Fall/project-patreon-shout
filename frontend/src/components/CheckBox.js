import React, {useState} from "react";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";


/**
 *
 * This is the checkbox function which will be used in the posts popups
 *
 * @param list
 * @param checkPostInList
 *
 * @returns the formcontrol label material
 *
 */

function CheckBox({list, checkPostInList}) {

    const [checked, setChecked] = useState(checkPostInList(list.list_id));

    const handleChange = (event) => {
        setChecked(event.target.checked);
    }

    return (
        <FormControlLabel value={list.list_id} control={<Checkbox checked={checked} onChange={handleChange}/>} label={list.title} />
    );
}

export default CheckBox;