import React from 'react';
import './login_css/Login.css'
import FormContainers from './FormContainers';
import PropTypes from "prop-types";

/**
 * The login function which controls what is displayed on the login page 
 * 
 * @returns The login page user interface
 */

function LoginPage({setToken}) {

  return (
    <div>
      <div className='navigation'>
        <FormContainers setToken={setToken}/>
      </div>
    </div>
  );
}

LoginPage.propTypes = {
    setToken: PropTypes.func.isRequired
};

export default LoginPage;
