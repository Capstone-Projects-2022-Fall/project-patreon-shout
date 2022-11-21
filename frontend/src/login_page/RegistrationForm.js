import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './login_css/RegistrationForm.css'
import { registerUser } from '../services/api/register';
import PasswordChecklist from "react-password-checklist"

/**
 * The Registration Form, where users can register an account
 *
 * @returns the registration form
 */
const RegistrationForm = () => {

    const [user, setRegUserName] = useState('')
    const [pass, setRegPassword] = useState('')
    const [popup, showPopup] = useState("hide")
    let passIsValid;

    const handleSubmit = async e => {

        if (!user) {
            document.getElementById("register").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = "Username not provided";
            showPopup("registerPop")
            return 1;
        }

        if (!pass) {
            document.getElementById("register").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = "Password not provided";
            showPopup("registerPop")
            return 1;
        }

        if (passIsValid === false) {
            document.getElementById("register").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = "Password does not meet the requirements";
            showPopup("registerPop")
            return 1;
        }

        e.preventDefault();
        const retVal = await registerUser({
            user,
            pass
        })

        if (retVal === "CREATED") {
            document.getElementById("register").textContent = "Register Success";
            document.getElementById("errormsg").textContent = "";
        } else {
            document.getElementById("register").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = retVal.message;
        }
        showPopup("registerPop")
    }


    return(

        <div className="mainPage">
            <h1>Register</h1>

            <div className="registerInfo">
                <TextField className='textLog' id="outlined-basic" label="Username"
                           variant="outlined" value={user} onChange={(e) => setRegUserName(e.target.value)}/>

                <TextField className='textLog' id="outlined-basic" label="Password" variant="outlined" type='password'
                           value={pass} onChange={(e) => setRegPassword(e.target.value)}/>

            </div>

            <PasswordChecklist
                rules={["minLength","capital","lowercase","number","specialChar"]}
                minLength={8}
                value={pass}
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

            <div className="registerButton" onClick={handleSubmit}>Register</div>

            <div className={popup}>
                <h3 id="register"> </h3>
                <p id="errormsg"> </p>
            </div>
        </div>
    )
}

export default RegistrationForm