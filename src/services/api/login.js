/**
 * Logs the user in using credentials
 *
 * @param credentials user login credentials
 * @returns {Promise<any>} is the json data returned
 */

export async function loginUser(credentials) {
    return fetch('http://localhost:5000/webaccount/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Origin': 'http://localhost:5000'
        },
        body: JSON.stringify(credentials)
    })
        .then(data => data.json())
}