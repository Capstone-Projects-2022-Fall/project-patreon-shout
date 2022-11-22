import React, {useEffect, useState} from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import './outreach_css/Outreach.css';
import DiscordOutreach from "./DiscordOutreach";
import TwitterOutreach from "./TwitterOutreach";
import InstagramOutreach from "./InstagramOutreach";
import RedditOutreach from "./RedditOutreach";
import {getSocialIntegrations} from "../services/api/webaccount/getSocialIntegration";
import {render} from "react-dom";
import {CircularProgress} from "@mui/material";


/**
 * This is the Outreach function which creates the outreach page
 *
 * @returns The outreach page user interface
 */
function Outreach() {

    const [socialIntegrations, setSocialIntegrations] = useState([]);

    let finished = false;
    useEffect(() => {
        const tokenString = localStorage.getItem('token');
        const loginToken = JSON.parse(tokenString).token;
        getSocialIntegrations(loginToken)
            .then(items => {
                if (!finished) {
                    setSocialIntegrations(items);
                    render(HtmlReturn(socialIntegrations), this); // Force Rerender this function component
                }
            })
        return () => finished = true;
    }, []);

    return HtmlReturn(socialIntegrations);

}

function HtmlReturn(socialIntegrations) {
    const [value, setValue] = React.useState(0)


    if (socialIntegrations.length === 0) {
        return (
            <div className="loading">
                <CircularProgress/>
            </div>
        );
    }

    // --------- tab functionality start ---------

    const handleChange = (event, newValue) => {
        setValue(newValue)
    }

    function TabPanel(props) {
        const {children, value, index, ...other} = props;

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

    // --------- tab functionality end ---------


    return (
        <div className="outreach">

            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs value={value} onChange={handleChange} centered>
                    <Tab label="Discord"/>
                    <Tab label="Twitter"/>
                    <Tab label="Instagram"/>
                    <Tab label="Reddit"/>
                </Tabs>
            </Box>


            <div className="content">
                <TabPanel value={value} index={0}>
                    <DiscordOutreach/>
                </TabPanel>

                <TabPanel value={value} index={1}>
                    <TwitterOutreach/>
                </TabPanel>

                <TabPanel value={value} index={2}>
                    <InstagramOutreach/>
                </TabPanel>

                <TabPanel value={value} index={3}>
                    <RedditOutreach/>
                </TabPanel>
            </div>
        </div>
    );
}

export default Outreach;