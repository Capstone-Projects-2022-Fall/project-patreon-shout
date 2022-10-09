import React from 'react';
import './Login.css'
import FormContainers from './FormContainers';

/**
 * The login function which controls what is displayed on the login page 
 * 
 * @returns The login page user interface
 */

function Login() {
  return (
    <div>
      <div className='navigation'>
        <FormContainers />
      </div>
    </div>
  );
}

export default Login;
