import React from "react";
import {Button} from "@mui/material";

function ConnectIntegrationButton({
                                      button_name,
                                      integration_url,
                                      client_id,
                                      redirect_uri,
                                      response_type,
                                      scope,
                                      state,
                                      code_challenge,
                                      code_challenge_method
                                  }) {

    const hrefUrl = integration_url +
        "?response_type=" + response_type +
        "&client_id=" + client_id +
        "&redirect_uri=" + redirect_uri +
        "&scope=" + scope +
        (!state ? '' : "&state=" + state) +
        (!code_challenge ? '' : "&code_challenge=" + code_challenge) +
        (!code_challenge_method ? '' : "&code_challenge_method=" + code_challenge_method);

    return (<div className="PatreonConnect">
        <Button
            target="_blank"
            href={hrefUrl}
            sx={{borderRadius: 28}}
            color="primary"
            type="submit"
            variant="contained"
        >
            {button_name}
        </Button>
    </div>);
}

export default ConnectIntegrationButton;