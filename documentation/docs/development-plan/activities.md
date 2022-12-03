---
sidebar_position: 1
---

# Activities

### Requirements Gathering

In order to fulfill the requirements proposed later in this document, our team plans to analyze the needs of content creators and patrons of Patreon and use the findings to actualize the specific points into our application. The previous version of our application showed us what things we did well and what things needed improvement on both the software we created and the idea behind the software. We also are in direct contact with a content creator who has expressed his concerns about Patreon and cross-posting to drive our ideas on what details are needed in our application for content creators. Furthermore, we are in direct contact with active patrons of content creators on Patreon who have expressed their concerns about Patreon and their content viewing experience that drive our ideas on what details are needed in our application for patrons. The information we gathered will allow us to build prototypes of our application and the steps we will take to complete this are listed below.

### Top-Level Design

1. Create backend & database communication
2. Create pipeline between Patreon and a social platform
3. Create web server and layout for UI
4. Integrate Patreon
5. Integrate Discord
6. Integrate Twitter
7. Integrate Instagram
8. Integrate Reddit
9. Database management
10. Implement user feed tools
11. Testing


### Detailed Design

1. **Create backend & database communication**
    - Create MySQL database
        - Database will utilize AWS RDS to host a MySQL 8 instance.
        - Database will be used to store usernames, passwords, creator tokens, following creators, patreon posts, social
          platform tokens, webhooks, and patron lists
          CRUD operations to communicate with database
    - CRUD operations to communicate with database
2. **Create pipeline between Patreon and a social platform**
    - Get posts from Patreon API
        - Call the Patreon API and save the information received in the database
    - Send posts to Discord using webhooks
        - Send the posts from the database to Discord using a webhook
3. **Create web server and layout for UI**
    - Web server
        - Web server will utilize hostinger.com for the following:
            - Domain name
            - Web hosting
    - Home Page
        - Main Feed
        - User settings
            - Creator settings
                - This page will let the creator link Patreon to social platforms
                    - Requires the user to provide a Patreon creator token
                - Social platform selection includes the following:
                    - Discord
                    - Twitter
                    - Instagram
                    - Reddit
            - Patron settings
                - This page will let the patron do the following:
                    - Change password
                    - Redo Patreon OAuth
        - Sign-in page
            - User can provide login details to proceed to the website
            - Information will be validated in the database before approving sign-in
        - Registration page
            - User can provide account details to create an account
            - Validation from email
        - Secure database password storage
            - Upon sign-in information will be transferred from client(user) to server via TLS 1.3 encryption
            - Password will be encrypted immediately and won’t be stored without encryption
4. **Integrate Patreon**
    - These things will happen when a user provides a Patreon creator token:
        - Creator token is verified
        - Creator token is used to get user information
            - Following creators is saved and set onto their Patreon Shout profile
        - Social integration selection menu is shown to the user to allow their Patreon posts to be automatically sent
          to other social platforms
5. **Integrate Discord**
    - User creates and provides a Discord webhook via a guide provided
    - Whenever a Patreon post is published, a Discord message is sent using the webhook providing the post details
6. **Integrate Twitter**
    - User authenticates and approves authorization via login dialog
    - User provides a template for the message they want to be sent on their Twitter account
    - Whenever a Patreon post is created, a tweet will be published on the user’s Twitter account providing the post
      details
7. **Integrate Instagram**
    - User authenticates and approves permissions via login dialog
    - User provides images they want to send and a caption for the image they provide
    - Whenever a Patreon post is created, the images and caption will be published onto their Instagram account
      providing the post details
8. **Integrate Reddit**
    - User authenticates and approves permissions via login dialog
    - User provides a subreddit location to output cross posting messages to
    - User provides a template for the message they want to send to a subreddit
    - Whenever a Patreon post is created, the images and caption will be published onto their Instagram account providing the post details
9. **Implement user feed tools**
    - Search bar
        - This feature lets the user search for posts from their following creators at the top of the screen
    - Post filtering
        - This feature lets the user filter through following creator’s posts to find specific posts
    - Creator lists
        - Replace the main feed with a list of specified creator’s posts
        - There will be a create a list option that will let you search through creators to add to the list
10. **Testing**
    - Tests will be done most sprints in different phases. Types of testing are explained below

### Testing

We will be using 3 different types of testing for PatreonShout. These include unit testing, integration testing and
acceptance testing.

<ins>Unit:</ins>

* Our team will utilize JUnit testing framework to implement unit tests for our Java code and Jest to implement React
  unit tests. For endpoint test, we will send HTTP requests with crafted requests to acquire specific responses

<ins>Integration:</ins>

* Integration tests will by done by ensuring that each component tested, works in conjunction with the other components throughout the website

<ins>Acceptance:</ins>

* Use Case Acceptance Testing will be performed to ensure that certain features are functional and match the users’
  expectations.