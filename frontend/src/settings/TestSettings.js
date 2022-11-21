import TextField from "@mui/material/TextField";
import {useState} from "react";
import DiscordOutput from "./DiscordOutput";
import {List} from "@mui/material";

function TestSettings() {
    const [textfieldValue, setTextfieldValue] = useState('');
    // console.log("textfieldValue == " + textfieldValue);

    return (
        <List id="test-settings" sx={{width: '100%', bgcolor: 'background.paper', paddingLeft: '1em', paddingRight: '1em'}}>
            <TextField
                fullWidth
                margin="normal"
                id="outlined-multiline-static"
                label="Output"
                multiline
                rows={4}
                defaultValue="Default Value"
                onChange={(e) => setTextfieldValue(e.target.value)}
            />

            <DiscordOutput
                // sx={{borderRadius: '25px'}}
                publicMessage={textfieldValue}>
                privateMessage={textfieldValue}>
            </DiscordOutput>

        </List>
    )
}

export default TestSettings;