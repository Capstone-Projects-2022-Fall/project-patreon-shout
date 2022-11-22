import { config } from "../../../Constants";


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