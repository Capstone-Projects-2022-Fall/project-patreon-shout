import React, { useState } from 'react';
import PropTypes from 'prop-types';
import {useNavigate} from 'react-router-dom'
import "./LoginForm.css"
import FacebookIcon from '@mui/icons-material/Facebook';
import TwitterIcon from '@mui/icons-material/Twitter';
import GoogleIcon from '@mui/icons-material/Google';
import TextField from '@mui/material/TextField';

const LoginForm = ({setToken}) => {

    const [userName, setUserName] = useState('')
    const [userPassword, setPassword] = useState('')
    const navigate = useNavigate();


    async function loginUser(credentials) {
        return fetch('http://localhost:5000/webaccount/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Origin': 'http://localhost:5000'
            },
            body: JSON.stringify(credentials)
        })
            .then(data => data.json())
    }


    //shows the login failed if username/pass != correct
    const handleSubmit = async e => {
        e.preventDefault();
        const token = await loginUser({
            userName,
            userPassword
        });
        setToken(token);

        if (!userName || !userPassword) {
            showPopup("loginPop")

        }
        else {
            navigate('/home');
        }

        // showPopup("loginPop")
        // setTimeout(() => showPopup("hide"), 4000)
    }

    //hides the login failed warning in general
    const [popupFailed, showPopup] = useState("hide")

    return(
        <div className="cover">
            <h1>Sign in</h1>
            <TextField className='textInputLog' id="outlined-basic" label="Username" variant="outlined"
                       value={userName} onChange={(e) => setUserName(e.target.value)}/>

            <TextField className='textInputLog' id="outlined-basic" label="Password" type='password'
                       variant="outlined" value={userPassword} onChange={(e) => setPassword(e.target.value)}/>

            <div className="loginButton" onClick={handleSubmit}>Sign in</div>

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

LoginForm.propTypes = {
    setToken: PropTypes.func.isRequired
};

export default LoginForm;