/** 
 * TODO: CHANGE TO REFLECT LOGIN
 * Saves the local session token from the database
 *
 * @param token is the session token
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