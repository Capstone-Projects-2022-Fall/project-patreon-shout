---
sidebar_position: 2
---
# Integration tests

Integration testing will be done using Mockito to mock API endpoints in Java to ensure the application properly handles this communication. Jest will be used alongside React to mock objects and ensure smooth user flow.

### Creator Social Platform Post Test:

Objective: Test the interface between a creators linked Patreon and other social platforms when a new post is published

Description: Link Patreon, Discord, Instagram, and Twitter accounts. Enable crossposting in Creator settings. Publish a new post on Patreon.
Expected Result: New post is also posted on Discord, Instagram, and Twitter with correct formatting

### Post Filtering Test:

Objective: Test the interface between the filter component and feed component

Description: On the main feed, add a new filter so the feed sorts by creator name
Expected Result: The main feed now sorts posts alphabetically by creator name

### Searching Posts Test:

Objective: Test the search bar and the search bar button components

Description: On the main feed, enter text into the search bar with text, and click the search button
Expected Result: The main feed now contains posts that contain text given in the search bar 

### Recommendation System Test:

Objective: Test the implementation of the recommendation system

Description: “Like” several posts on the main feed, and click “show recommendations”
Expected Result: The right sidebar should populate with 

### Main Feed Test:

Objective: Test the main feed functionality

Description: Follow creators, view creator posts on main feed, load more posts by scrolling
Expected Result: Posts should populate the main feed after creators are followed, and more posts are loaded when scrolling

### Discord Integration Test:

Objective: Test that users can view automated Patreon posts on Discord

Description:  Find the content creator’s Discord server that is linked with Patreon Shout, view new Patreon posts on Discord channel
Expected Result: Patreon posts should show up in the Discord channel of the user when published to Patreon

### Instagram Integration Test:

Objective: Test that users can view automated Patreon posts on Instagram

Description: Find the content creator’s account that is linked with Patreon Shout, follow the content creator, view new Patreon posts on Instagram
Expected Result: Patreon posts should show up in the Instagram feed of the user when published to Patreon

### Twitter Integration Test:

Objective: Test that users can view automated Patreon posts on Twitter

Description: Find the content creator’s account that is linked with Patreon Shout, follow the content creator, view new Patreon posts on Twitter
Expected Result: Patreon posts should show up in the Twitter feed of the user when published to Patreon
