import { config } from "../../../Constants";

/**
 * Overwrites the user's social integration messages with the given information
 *
 * @param request contains login_token, integration_name, <integration_name>_public_message, <integration_name>_private_message
 * @returns {Promise<any>}
 */
export async function putSocialIntegrationMessages(request) {
    return fetch(config.url.API_URL + '/webaccount/socialintegrationmessages', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        },
        body: JSON.stringify(request)
    })
        .then(data => data.json())
}