import React from 'react';
import LoginForm from "./components/loginform"
import ButtonAppBar from './components/Appbar';


function App() {
  return (
    <div>
      <div className='navigation'>
      <ButtonAppBar/>
      </div>
      <div className='page'>
      <LoginForm />
      </div>
    </div>
  );
}

export default App;
