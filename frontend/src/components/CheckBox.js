import React, {useState} from "react";
import Checkbox from "@mui/material/Checkbox";
import FormControlLabel from "@mui/material/FormControlLabel";



function CheckBox({list, checkedDefault}) {

    const [checked, setChecked] = useState(checkedDefault);

    const handleChange = (event) => {
        setChecked(event.target.checked);
    }

    return (
        <FormControlLabel value={list.list_id} control={<Checkbox checked={checked} onChange={handleChange}/>} label={list.title} />
    );
}

export default CheckBox;