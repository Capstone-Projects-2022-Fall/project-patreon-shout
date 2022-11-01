import { config } from "../../Constants";

/**
 * Deletes the local session token from the database
 *
 * @param token is the session token
 * @returns {Promise<any>} is the json data returned
 */

export function logoutUser(token) {
    return fetch(config.url.API_URL + '/webaccount/logout?login_token=' + token, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}