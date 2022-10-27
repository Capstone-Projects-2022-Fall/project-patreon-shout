/**
 * Registers the user
 *
 * @param credentials are the credentials of the user we want to register
 * @returns {Promise<any>} is the json data returned
 */

export async function registerUser(credentials) {
    return fetch('http://localhost:5000/webaccount/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Origin': 'http://localhost:5000'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
}