/** 
 * TODO: CHANGE TO REFLECT REGISTER
 * Gets the posts of a specific creator
 *
 * @param creator is the creator we want to get the posts of
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