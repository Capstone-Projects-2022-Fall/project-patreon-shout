import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './login_css/RegistrationForm.css'
import { registerUser } from '../services/api/register';

/**
 * The Registration Form, where users can register an account
 *
 * @returns the registration form
 */
const RegistrationForm = () => {

    const [user, setRegUserName] = useState('')
    const [pass, setRegPassword] = useState('')
    const [popup, showPopup] = useState("hide")

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

            <TextField className='textLog' id="outlined-basic" label="Username"
                       variant="outlined" value={user} onChange={(e) => setRegUserName(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="Password" variant="outlined" type='password'
                       value={pass} onChange={(e) => setRegPassword(e.target.value)}/>

            <div className="registerButton" onClick={handleSubmit}>Register</div>

            <div className={popup}>
                <h3 id="register"> </h3>
                <p id="errormsg"> </p>
            </div>
        </div>
    )
}

export default RegistrationForm