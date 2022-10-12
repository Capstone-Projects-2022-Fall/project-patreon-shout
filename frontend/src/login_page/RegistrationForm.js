import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './RegistrationForm.css'

const RegistrationForm = () => {

    const [fullName, setFullName] = useState('')
    const [user, setRegUserName] = useState('')
    const [pass, setRegPassword] = useState('')
    const [regEmail, setRegEmail] = useState('')

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
        e.preventDefault();
        const retVal = await registerUser({
            user,
            pass
        })

        if (retVal !== "CREATED") {
            console.log("account NOT created");
        }
        else {
            console.log("account created");
        }
    }

    return(

        <div className="mainPage">
            <h1>Register</h1>
            <TextField className='textLog' id="outlined-basic" label="Full Name" variant="outlined"
                       value={fullName} onChange={(e) => setFullName(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="User Name"
                       variant="outlined" value={user} onChange={(e) => setRegUserName(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="Email" variant="outlined"
                       value={regEmail} onChange={(e) => setRegEmail(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="Password" variant="outlined" type='password'
                       value={pass} onChange={(e) => setRegPassword(e.target.value)}/>

            <div className="registerButton" onClick={handleSubmit}>Submit</div>

        </div>
    )
}

export default RegistrationForm