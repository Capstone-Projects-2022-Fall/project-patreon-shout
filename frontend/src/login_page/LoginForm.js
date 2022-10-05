import React, { useState } from 'react';
import { TextField } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';
import "./LoginForm.css"
import FacebookIcon from '@material-ui/icons/Facebook';
import TwitterIcon from '@material-ui/icons/Twitter';
import GoogleIcon from '@mui/icons-material/Google';

/**
 * The LoginForm function which displays the login component on the login page
 * 
 * @returns The login component which holds a username and password to be verified on submission
 */
const LoginForm = () => {

    const [userName, setUserName] = useState('')
    const [userPassword, setPassword] = useState('')
    const [popupFailed, showPopup] = useState("hide")
    const navigate = useNavigate();


    //shows the login failed if username/pass != correct
    const popup = () => {
        if (!userName || !userPassword) {
            showPopup("loginPop")
            setTimeout(() => showPopup("hide"), 4000)
        }
        else {
            navigate('/home');
        }
    }

    return(
    <div className="cover">
        <h1>Sign in</h1>
        <TextField className='textInputLog' id="outlined-basic" label="Username" variant="outlined"
                   value={userName} onChange={(e) => setUserName(e.target.value)}/>

        <TextField className='textInputLog' id="outlined-basic" label="Password" type='password'
                   variant="outlined" value={userPassword} onChange={(e) => setPassword(e.target.value)}/>

        <div className="loginButton" onClick={popup}>Sign in</div>

        <p className="text">Or sign in with with</p>

        <div className="OtherLogin">
            <TwitterIcon></TwitterIcon>
            <FacebookIcon></FacebookIcon>
            <GoogleIcon></GoogleIcon>
        </div>

        <div className={popupFailed}>
            <h3>Sign in Failed</h3>
            <p>Incorrect Username or Password. Please try again</p>

        </div>
    </div>
    )
}

export default LoginForm