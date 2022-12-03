import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom'
import "./login_css/LoginForm.css"
import TextField from '@mui/material/TextField';
import { loginUser } from '../services/api/login';

/**
 * The Login Form, where users can login to the website
 *
 * @returns the login form
 */
const LoginForm = ({setToken}) => {

    const [userName, setUserName] = useState('')
    const [userPassword, setPassword] = useState('')
    const [popupFailed, showPopup] = useState("hide")
    const navigate = useNavigate();

    //shows the login failed if username/pass != correct
    const handleSubmit = async e => {
        e.preventDefault();
        const token = await loginUser({
            userName,
            userPassword
        });
        console.log(token);

        if (!userName || !userPassword || !token.token) {
            showPopup("loginPop")
        }
        else {
            setToken(token);
            navigate("/home");
        }
    }


    return(
        <div className="cover">
            <h1>Sign in</h1>

            <div className="loginContent">
                <TextField data-testid="login-username" className='textInputLog' id="outlined-basic" label="Username" variant="outlined"
                           value={userName} onChange={(e) => setUserName(e.target.value)}/>

                <TextField data-testid="login-password" className='textInputLog' id="outlined-basic" label="Password" type='password'
                           variant="outlined" value={userPassword} onChange={(e) => setPassword(e.target.value)}/>

                <div className="loginButtonContainer">
                    <div data-testid="login-button" className="loginButton" onClick={handleSubmit}>Sign in</div>
                </div>
            </div>

            <div className={popupFailed}>
                <h3>Sign in Failed</h3>
                <p>Incorrect Username or Password. Please try again</p>
            </div>
        </div>
    )
}

LoginForm.propTypes = {
    setToken: PropTypes.func.isRequired
};

export default LoginForm;