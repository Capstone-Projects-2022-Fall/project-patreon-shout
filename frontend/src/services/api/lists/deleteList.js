import { config } from "../../../Constants";

/**
 * Deletes a user list
 *
 * @param request is the request json body sent to the endpoint
 * @returns {Promise<any>} is the json data returned
 */

export async function deleteList(request) {
    console.log(JSON.stringify(request));
    return fetch(config.url.API_URL + '/lists/list', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': config.url.API_URL
        },
        body: JSON.stringify(request)
    })
        .then(data => data.json())
}