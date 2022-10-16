import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './RegistrationForm.css'

const RegistrationForm = () => {

    const [user, setRegUserName] = useState('')
    const [pass, setRegPassword] = useState('')
    const [popup, showPopup] = useState("hide")

    async function registerUser(credentials) {
        return fetch('http://localhost:5000/webaccount/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Origin': 'http://localhost:5000'
            },
            body: JSON.stringify(credentials)
        })
            .then(data => data.json())
    }

    const handleSubmit = async e => {

        if (!user || !pass) {
            document.getElementById("register").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = "Username or Password not provided";
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

            let errorMsg = "Error " + retVal.status + ": ";

            if (retVal.status === 409) {
                errorMsg += "Username already in use";
            }
            else {
                errorMsg += "Internal server error";
            }

            document.getElementById("register").textContent = "Register Failed";
            document.getElementById("errormsg").textContent = errorMsg;
            showPopup("registerPop")
        }
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