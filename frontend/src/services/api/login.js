import { config } from "../../Constants";

/**
 * Logs the user in using credentials
 *
 * @param credentials user login credentials
 * @returns {Promise<any>} is the json data returned
 */

export async function loginUser(credentials) {
    return fetch(config.url.API_URL + '/webaccount/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
}