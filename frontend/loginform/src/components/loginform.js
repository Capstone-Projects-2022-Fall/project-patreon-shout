import React, { useState } from 'react';
import "./loginform.css"
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import GoogleIcon from '@mui/icons-material/Google';

const LoginForm = () => {

    //shows the login failed if username/pass != correct
    const popup = () => {
        showPopup("loginPop")
        setTimeout(() => showPopup("hide"), 5000)
    }

    //hides the login failed warning in general
    const [popupFailed, showPopup] = useState("hide")

    return(
        <div className = "cover">
            <h1>Sign in</h1>
            <input type="text" placeholder="username" />
            <input type="password" placeholder="Password" />

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