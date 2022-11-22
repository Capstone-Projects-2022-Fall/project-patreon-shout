import React from "react";
import DiscordConnect from "../components/DiscordConnect";

import './outreach_css/Outreach.css';

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