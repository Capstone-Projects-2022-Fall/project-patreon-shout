import React from 'react'
import LoginForm from './LoginForm'
import RegistrationForm from './RegistrationForm';
import './FormContainers.css'
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import PropTypes from "prop-types";

/**
 * Calls the files that holds the function LoginForm and RegistrationForm
 *
 * @returns The header tab of sign in and registration form integrated
 */
function FormContainers({setToken}) {

    const [value, setValue] = React.useState(0)

    const handleChange = (event, newValue) => {
        setValue(newValue)
    }

    function TabPanel(props) {
        const { children, value, index, ...other } = props;

        return (
            <div
                role="tabpanel"
                hidden={value !== index}
                id={`simple-tabpanel-${index}`}
                aria-labelledby={`simple-tab-${index}`}
                {...other}
            >
                {value === index && (
                    <Box>
                        <Typography>{children}</Typography>
                    </Box>
                )}
            </div>
        );
    }

    return (
        <div className='paperStyle'>
            <Paper elevation={20}>
                <Tabs value={value} onChange={handleChange} centered>
                    <Tab label="Sign In" />
                    <Tab label="Register" />
                </Tabs>
                <TabPanel value={value} index={0}>
                    <LoginForm setToken={setToken}/>
                </TabPanel>
                <TabPanel value={value} index={1}>
                    <RegistrationForm />
                </TabPanel>
            </Paper>
        </div>
    );
}

FormContainers.propTypes = {
    setToken: PropTypes.func.isRequired
};

export default FormContainers