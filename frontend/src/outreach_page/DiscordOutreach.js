import React from "react";
import DiscordConnect from "../components/DiscordConnect";

import './outreach_css/Outreach.css';

/**
 * The discord social platform cross-posting component
 *
 * @param webhook is the user's already saved discord webhook
 * @returns Interface for the user to cross post to discord with custom post details
 */
function DiscordOutreach({webhook}) {


    return (
        <div className="outreachSettings">
            <DiscordConnect
                webhook={webhook}
            />
        </div>
    );
}

export default DiscordOutreach;