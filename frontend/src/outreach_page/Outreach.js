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
import {getSocialIntegrationMessages} from "../services/api/webaccount/getSocialIntegrationMessages";
import InfoOutreach from "./InfoOutreach";
import {getInstagramPostDetails} from "../services/api/webaccount/getInstagramPostDetails";


/**
 * This is the Outreach function which creates the outreach page
 *
 * @returns The outreach page user interface
 */
function Outreach() {

    const [socialIntegrations, setSocialIntegrations] = useState([]);
    const [socialIntegrationMessages, setSocialIntegrationMessages] = useState([]);
    const [instagramPostDetails, setInstagramPostDetails] = useState([]);

    const tokenString = localStorage.getItem('token');
    const loginToken = JSON.parse(tokenString).token;

    let finished = false;
    useEffect(() => {

        getSocialIntegrations(loginToken)
            .then(items => {
                if (!finished) {
                    setSocialIntegrations(items);
                    render(HtmlReturn(socialIntegrations, socialIntegrationMessages, instagramPostDetails), this); // Force Rerender this function component
                }
            })
        return () => finished = true;
    }, []);

    let mounted = false;
    useEffect(() => {
        if (mounted)
            return;

        getSocialIntegrationMessages(loginToken)
            .then(items => {
                setSocialIntegrationMessages(items);
                render(HtmlReturn(socialIntegrations, socialIntegrationMessages, instagramPostDetails), this);
            })
        return () => mounted = true;
    }, []);

    let mountedDos = false;
    useEffect(() => {
        if (mountedDos)
            return;

        getInstagramPostDetails(loginToken)
            .then(items => {
                setInstagramPostDetails(items);
                render(HtmlReturn(socialIntegrations, socialIntegrationMessages, instagramPostDetails), this);
            })
        return () => mountedDos = true;
    }, []);

    return HtmlReturn(socialIntegrations, socialIntegrationMessages, instagramPostDetails);

}

function HtmlReturn(socialIntegrations, socialIntegrationMessages, instagramPostDetails) {
    const [value, setValue] = React.useState(0)


    if (socialIntegrations.length === 0 || socialIntegrationMessages.length === 0) {
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

            <Box className='outreach__tabs' sx={{ borderBottom: 1, borderColor: 'divider'}}>
                <Tabs variant="scrollable" scrollButtons allowScrollButtonsMobile value={value} onChange={handleChange} centered>
                    <Tab label="Info" sx={{fontFamily: 'Helvetica'}}/>
                    <Tab label="Discord" sx={{fontFamily: 'Helvetica'}}/>
                    <Tab label="Twitter" sx={{fontFamily: 'Helvetica'}}/>
                    <Tab label="Instagram" sx={{fontFamily: 'Helvetica'}}/>
                    <Tab label="Reddit" sx={{fontFamily: 'Helvetica'}}/>
                </Tabs>
            </Box>


            <div className="entire-content">
                <TabPanel value={value} index={0}>
                    <InfoOutreach/>
                </TabPanel>

                <TabPanel value={value} index={1}>
                    <DiscordOutreach
                        webhook={socialIntegrations.discord}
                        publicMessage={socialIntegrationMessages.discord_public_message}
                        privateMessage={socialIntegrationMessages.discord_private_message}
                    />
                </TabPanel>

                <TabPanel value={value} index={2}>
                    <TwitterOutreach
                        publicMessage={socialIntegrationMessages.twitter_public_message}
                        privateMessage={socialIntegrationMessages.twitter_private_message}
                    />
                </TabPanel>

                <TabPanel value={value} index={3}>
                    <InstagramOutreach
                        publicMessage={socialIntegrationMessages.instagram_public_message}
                        privateMessage={socialIntegrationMessages.instagram_private_message}
                        storedImageUrl={instagramPostDetails.instagram_image_url}
                        blurAmount={instagramPostDetails.instagram_blur_amount}
                        textColor={instagramPostDetails.instagram_message_color}
                    />
                </TabPanel>

                <TabPanel value={value} index={4}>
                    <RedditOutreach
                        subreddit={socialIntegrations.reddit_subreddit_location}
                        publicMessage={socialIntegrationMessages.reddit_public_message}
                        privateMessage={socialIntegrationMessages.reddit_private_message}
                    />
                </TabPanel>
            </div>
        </div>
    );
}

export default Outreach;