import React, { useState } from 'react';
import TextField from '@mui/material/TextField';
import './RegistrationForm.css'

const RegistrationForm = () => {

    const [fullName, setFullName] = useState('')
    const [regUserName, setRegUserName] = useState('')
    const [regPassword, setRegPassword] = useState('')
    const [regEmail, setRegEmail] = useState('')

    return(

        <div className="mainPage">
            <h1>Register</h1>
            <TextField className='textLog' id="outlined-basic" label="Full Name" variant="outlined"
                       value={fullName} onChange={(e) => setFullName(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="User Name"
                       variant="outlined" value={regUserName} onChange={(e) => setRegUserName(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="Email" variant="outlined"
                       value={regEmail} onChange={(e) => setRegEmail(e.target.value)}/>

            <TextField className='textLog' id="outlined-basic" label="Password" variant="outlined" type='password'
                       value={regPassword} onChange={(e) => setRegPassword(e.target.value)}/>

            <div className="registerButton">Submit</div>

        </div>
    )
}

export default RegistrationForm