import React, {useState} from "react";

import './comp_css/DiscordConnect.css';
import TextField from "@mui/material/TextField";
import {Button} from "@mui/material";
import PendingIcon from "@mui/icons-material/Pending";
import {putSocialIntegration} from "../services/api/webaccount/putSocialIntegration";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import ErrorIcon from "@mui/icons-material/Error";
import SaveIcon from "@mui/icons-material/Save";

function DiscordConnect({webhook}) {

    console.log(webhook);
    const [textfieldValue, setTextfieldValue] = useState(webhook);
    const [buttonColor, setButtonColor] = useState("primary");
    const [buttonIcon, setButtonIcon] = useState(<SaveIcon/>);
    const [isProcessing, setIsProcessing] = useState(null);

   return (
       <div className="discordConnect">
           <TextField
               id={"discord-textfield"}
               label={"Webhook URL"}
               size="small"
               variant="outlined"
               value={textfieldValue}
               onChange={(e) => setTextfieldValue(e.target.value)}
           />
           <Button
               sx={{borderRadius: 10}}
               color={buttonColor}
               type="submit"
               variant="contained"
               size={"medium"}
               disabled={isProcessing}
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
                           integration_name: "DISCORD",
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
   );
}

export default DiscordConnect;