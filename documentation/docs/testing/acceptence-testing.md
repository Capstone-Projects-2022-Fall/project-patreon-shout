---
sidebar_position: 3
---
# Acceptance test

Use Case Acceptance Testing will be performed to ensure that certain features are functional and match the users’ expectations.

### Account Login and Registration

Actions: Open Patreon Shout website, select “register”, enter new account information, click “register”, go to the login page, fill in text boxes with information used during registration, click “login”

Expected Result: You should be redirected to the home page where you will see the main feed

### Patreon Posts Feed

Actions: Scroll up to view new posts, search for a particular creator in the search bar at the top of the main feed, click the filter button to the right of the search bar, filter posts by shown categories via checkbox

Expected Result: You should be able to view Patreon posts, search for creators, and filter through Patreon posts on the main feed

### Patreon Creator Lists

Actions: Click the “Lists” button to the right of the main feed, click “create new list”, search and add creators into your list, name your list, click “Finish”, click on the newly created list to have that list populate the main feed with Patreon posts from the selected creators

Expected Result: View specific creator posts from a custom list of creators on the main feed

### Patreon Link

Actions: When navigating to the Creator settings you are greeted with a “Link Patreon” button.  Click it and a new browser window pops up with Patreon asking if you would like to allow or deny PatreonShout specific access to my account’s details.  Click allow.

Expected Result: You will be taken back to the Creator settings with their Patreon account linked to their PatreonShout account, revealing Creator integration features. 

### Social Platform Link

Actions: Click “Settings” to the right of the main feed, navigate to the creator settings tab, link your Patreon account, select a social platform to link to, provide the social platform api token in the textbox, publish a post on Patreon

Expected Result: Automatically send your newly published Patreon post to another social platform

### Credential Security

Actions: Register an account with your desired credentials.

Expected Result: The given password is securely stored on the website’s database.

## Testing Sheet

![excel](https://user-images.githubusercontent.com/29709311/195232930-7e2143fd-19e9-473e-b403-578539191ea2.png)
