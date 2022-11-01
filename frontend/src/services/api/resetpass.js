import { config } from "../../Constants";

/**
 * resets a user password
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */
export async function resetPassword(request) {
    return fetch(config.url.API_URL + '/webaccount/resetpassword', {
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