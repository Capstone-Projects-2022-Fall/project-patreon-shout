import { config } from "../../../Constants";


export async function putSocialIntegration(request) {
    return fetch(config.url.API_URL + '/webaccount/socialintegration', {
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