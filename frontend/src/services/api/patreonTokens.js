import { config } from "../../Constants";

/**
 * Gets the saved patreon info from a user
 *
 * @param loginToken users login token
 * @returns the user's patreon token info
 */
export function getPatreonTokens(loginToken) {
    return fetch(config.url.API_URL + '/webaccount/patreontokens?login_token=' + loginToken, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}