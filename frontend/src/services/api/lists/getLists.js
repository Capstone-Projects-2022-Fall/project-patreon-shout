import { config } from "../../../Constants";

/**
 * Gets the List objects of the user
 *
 * @param token is the login token of the user's session
 * @returns {Promise<any>} is the json data returned
 */
export async function getLists(token) {
    return fetch(config.url.API_URL + '/lists/user?loginToken=' + token, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}