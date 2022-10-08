import React from 'react';
import './Login.css'
import FormContainers from './FormContainers';
import PropTypes from "prop-types";

/**
 * The login function which controls what is displayed on the login page 
 * 
 * @returns The login page user interface
 */

function Login({setToken}) {

  return (
    <div>
      <div className='navigation'>
        <FormContainers setToken={setToken}/>
      </div>
    </div>
  );
}

Login.propTypes = {
    setToken: PropTypes.func.isRequired
};

export default Login;
