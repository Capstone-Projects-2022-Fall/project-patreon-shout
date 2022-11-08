import React, {useEffect, useState} from 'react';
import {List, Typography} from '@mui/material';
import PatreonConnect from "../components/PatreonConnect";
import DropDownListItem from "./DropDownListItem";
import {getSocialIntegrations} from "../services/api/webaccount/getSocialIntegration";
import {render} from "react-dom";

function CreatorSettings() {
    const [socialIntegrations, setSocialIntegrations] = useState([]);

    const tokenString = localStorage.getItem('token');
    const loginToken = JSON.parse(tokenString).token;
    let finished = false;

    useEffect(() => {
        getSocialIntegrations(loginToken)
            .then(items => {
                if (!finished) {
                    setSocialIntegrations(items);
                    render(htmlReturn(socialIntegrations), this); // Force Rerender this function component
                }
            })
        return () => finished = true;
    }, []);

    return htmlReturn(socialIntegrations);
}

function htmlReturn(socialIntegrations) {
    if (socialIntegrations.length === 0)
        return "Loading..."; // TODO make this look better, or something...

    return (
        <List id="creator-settings" sx={{width: '100%', maxWidth: 360, bgcolor: 'background.paper'}}>
            <Typography mt={1} component="div" align="center">
                <PatreonConnect/>
            </Typography>

            <DropDownListItem
                name="Discord"
                textfieldLabel="Webhook URL"
                dropdown={true}
                defaultChecked={typeof socialIntegrations.discord === 'string'}
                defaultValue={socialIntegrations.discord}
            />

            <DropDownListItem
                name="Twitter"
                textfieldLabel="Twitter Token"
                dropdown={true}
                defaultChecked={typeof socialIntegrations.twitter === 'string'}
                defaultValue={socialIntegrations.twitter}
            />

            <DropDownListItem
                name="Instagram"
                textfieldLabel="Instagram Token"
                dropdown={true}
                defaultChecked={typeof socialIntegrations.instagram === 'string'}
                defaultValue={socialIntegrations.instagram}
                isDisabled={true}
            />
        </List>
    );
}

export default CreatorSettings;

