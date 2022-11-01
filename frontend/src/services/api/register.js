import { config } from "../../Constants";

/**
 * Registers the user
 *
 * @param credentials are the credentials of the user we want to register
 * @returns {Promise<any>} is the json data returned
 */

export async function registerUser(credentials) {
    return fetch(config.url.API_URL + '/webaccount/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
}