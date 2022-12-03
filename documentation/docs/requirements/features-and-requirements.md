---
sidebar_position: 4
---

# Features and Requirements

## Functional Requirements
- Account Login and Registration
  - Login security will leverage a unique salt for each user ID and SHA2 hashing (using Java’s cryptography library) so no plaintext passwords are ever stored in the database and an adversary discovering one user's salt will not compromise all users
  - The website will handle password resets by sending a unique 15 minute TTL link to the email provided that requests a new password
- Patreon Posts Feed
  - The main feed will contain posts from creators in chronological order
  - Patrons can search for creators through the search bar at the top of the webpage
  - Patrons can filter their posts by different parameters after clicking the filter button
- Patreon Post Lists
  - These lists of Patreon posts can replace the original creator feed with the feed of Patreon posts specifically from the list
  - Any post can be added to any number of lists
- Patreon Link
  - Linking a user’s Patreon account will let Patreon Shout automatically add their currently following creators to their Patreon Shout account
  - Linking a user’s Patreon account will let Patreon Shout get information on the user’s creator posts
- Social Platform Link
  - Creators will be able to use our crosspost functionality
    - Automatically post new Patreon posts to whichever social platforms they indicate
  - Instagram, Twitter, and Discord integrations
- Credential Security
  - User login information will be secure via hashing the passwords with salt, using a unique salt for each password, then storing the hashed password into the database   

## Nonfunctional Requirements
- Patreon Shout will have an intuitive user-friendly interface.
  - Outputting patreon posts to other social platforms will the be centerpiece of the app
    - Linking a user’s account to Patreon will be easy to find and intuitive to figure out
  - There are user friendly messages when the user interacts with buttons
    - Users will be able to easily link their account with Patreon in a user friendly way
    - Users will be able to easily output their Patreon posts to other social platforms in a user friendly way
- To increase performance, posts will be stored per Patreon creator, so that we can send the same post data to multiple different users
