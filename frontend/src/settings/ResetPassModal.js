import * as React from 'react';
import { Stack, Box, Typography, Button, Modal, TextField } from '@mui/material';
import PasswordChecklist from "react-password-checklist";
import {resetPassword} from "../services/api/resetpass";

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

function ResetPassModal() {

    const [popup, showPopup] = React.useState("hide")
    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const [curPass, setCurPass] = React.useState("");
    const [newPass, setNewPass] = React.useState("");
    const [sameNewPass, setSameNewPass] = React.useState("");
    let passIsValid;

    const handleSubmit = async e => {

        if (passIsValid === false) {
            document.getElementById("reset").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = "Password does not meet the requirements";
            showPopup("resetPop")
            return 1;
        }

        let current_password = curPass;
        let new_password = newPass;
        const tokenString = localStorage.getItem('token');
        const login_token = JSON.parse(tokenString).token;

        const retVal = await resetPassword({
            login_token,
            current_password,
            new_password
        })

        console.log(retVal);

        if (retVal.success === "true") {
            document.getElementById("reset").textContent = "Register Success";
            document.getElementById("errormsg").textContent = "";
        } else {
            document.getElementById("reset").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = retVal.status + ": " + retVal.message;
        }
        showPopup("resetPop")
    }


    return (
        <div>
            <Typography m={2} align="center">
                <Button variant="contained" onClick={handleOpen}>Reset Password</Button>
            </Typography>

            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box sx={style}>

                    <Typography variant="h6" component="h3" sx={{ mt: 1 }}>
                        Update your password
                    </Typography>

                    <Typography component="h2" sx={{ mt: 3 }}>
                        <TextField fullWidth id="outlined-basic" label="Current Password" type="password" variant="outlined" value={curPass} onChange={(e) => setCurPass(e.target.value)}/>
                    </Typography>

                    <Typography component="h2">
                        <TextField fullWidth id="outlined-basic" label="New Password" type="password" variant="outlined" value={newPass} onChange={(e) => setNewPass(e.target.value)}/>
                    </Typography>

                    <Typography component="h2">
                        <TextField fullWidth id="outlined-basic" label="Confirm New Password" type="password" variant="outlined" value={sameNewPass} onChange={(e) => setSameNewPass(e.target.value)}/>
                    </Typography>

                    <PasswordChecklist
                       rules={["minLength","capital","lowercase","number","specialChar"]}
                        minLength={8}
                        value={newPass}
                        iconSize={14}
                        messages={{
                            minLength: "At least 8 characters",
                            capital: "At least one uppercase letter",
                            lowercase: "At least one lowercase letter",
                            number: "At least one number",
                            specialChar: "At least one special character",
                        }}
                        onChange={(passIsValid) => {}}
                        className="passwordRequirements"
                    />

                    <Box sx={{ flexGrow: 1 }} mt={2}>
                        <Stack direction="row" spacing={21}>
                            <Button variant="text" color='error' onClick={handleClose}>cancel</Button>
                            <Button variant="contained" onClick={() => {
                                handleSubmit();
                            }}>Submit</Button>
                        </Stack>
                    </Box>

                    <div className={popup}>
                        <h3 id="reset"> </h3>
                        <p id="errormsg"> </p>
                    </div>
                </Box>
            </Modal>
        </div>
    );
}

export default ResetPassModal;