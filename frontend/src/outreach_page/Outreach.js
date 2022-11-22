import React from "react";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import './outreach_css/Outreach.css';
import SocialPostSetting from "./SocialPostSetting";
import PropTypes from "prop-types";
import TwitterConnect from "../components/TwitterConnect";
import DiscordConnect from "../components/DiscordConnect";
import PatreonConnect from "../components/PatreonConnect";


/**
 * This is the Outreach function which creates the outreach page
 *
 * @returns The outreach page user interface
 */
function Outreach() {

    // --------- tab functionality start ---------
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
                    <SocialPostSetting
                        platform="Discord"
                    >
                        <DiscordConnect />
                    </SocialPostSetting>
                </TabPanel>

                <TabPanel value={value} index={1}>
                    <SocialPostSetting
                        platform="Twitter"
                    >
                        <TwitterConnect />
                    </SocialPostSetting>
                </TabPanel>

                <TabPanel value={value} index={2}>
                    <SocialPostSetting
                        platform="Instagram"
                    >

                    </SocialPostSetting>
                </TabPanel>

                <TabPanel value={value} index={3}>
                    <SocialPostSetting
                        platform="Reddit"
                    >

                    </SocialPostSetting>
                </TabPanel>
            </div>
        </div>
    );
}

export default Outreach;