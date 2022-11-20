import { config } from "../../Constants";

/**
 * Gets the campaign info of a campaign id
 *
 * @param campaignId is the id of the campaign we want to get
 * @param loginToken is the user login token needed for accessing the website
 * @returns {Promise<any>} is the json data returned
 */
export function getCampaign(campaignId, loginToken) {
    return fetch(config.url.API_URL + '/posts/campaign?campaignId=' + campaignId + '&loginToken=' + loginToken, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Origin': config.url.API_URL
        }
    })
        .then(data => data.json())
}