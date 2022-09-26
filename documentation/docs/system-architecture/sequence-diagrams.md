---
sidebar_position: 2
---

# Sequence Diagrams

### Use Case 1
As a user, I want to be able to forward my Patreon posts to Discord, Instagram, and Twitter, so that my outreach to future potential patrons is greater.
1. User signs up on the Patreon Shout website and automatically logs in.
2. After logging in, the user is prompted to link their Patreon account to their Patreon Shout account.
3. After linking accounts, the user is redirected to a Twitter-like feed of the Patreon creator’s posts that the user is following.
4. From there the user will see an option for Patreon content creators and click that option.
5. The user will then be asked to link their Patreon account if they haven’t already done so and will see a drop-down selection of social platforms appear after their account is linked.
6. After deciding which social platforms they wish to output their posts to, the user will be given instructions on providing the information needed to allow Patreon Shout to post on the selected social platforms.
7. User provides Patreon Shout with the information needed to post on a social platform
8. Whenever the user publishes a post on Patreon, the post will subsequently be published to the selected social platforms.


```mermaid
sequenceDiagram
    participant User
    participant PatreonShout
    participant Patreon
    participant Platform
User ->>+ PatreonShout: Signs up
PatreonShout -->> User: Response with session cookie
PatreonShout -->>- User: Prompt to link their Patreon account

User ->>+ Patreon: Allow OAuth authorization
activate Patreon
Patreon -->>+ PatreonShout: OAuth Response code
deactivate Patreon
PatreonShout ->>- Patreon: Request creator access token using Patreon API
activate Patreon
Patreon -->>+ PatreonShout: Response with creator access token
deactivate Patreon
PatreonShout -->>- User: Redirected to main feed

User ->>+ PatreonShout: Clicks content creator settings
PatreonShout -->>- User: Redirected to content creator settings

User ->>+ PatreonShout: Selects a social platform
PatreonShout -->>- User: Instructions needed to post on the platform returned

User ->>+ PatreonShout: Provides information needed to post on a social platform and clicks "save"
PatreonShout -->>- User: Tells the user that cross-posting is enabled

User ->>+ Patreon: Publishes Patreon post
Patreon -->>- PatreonShout: New Patreon post information
activate PatreonShout
PatreonShout ->>+ Platform: Post creation request
deactivate PatreonShout
Platform -->>- PatreonShout: Post creation request approved
```


### Use Case 2
As a user, I want to be able to filter through the creators I’m following on Patreon, so I can easily view specific content, like the category “gaming”, from my list of creators I follow.
1. After logging into Patreon Shout, the user will be shown a Twitter-like feed of posts from the content creators they follow.
2. To filter through posts, the user will see a "Filters" button to the side of the search bar at the top of the screen.
3. In the “Filters” section, the user is shown a list of checkboxes to filter for specific categories and a search bar they can use to filter for specific words.
4. After clicking a category in the “Filters” section, only that category of posts is shown to the user on the main feed
5. After typing in the search bar in the “Filters” section, only the posts with the specific string of characters typed will show in the main feed to the user


```mermaid
sequenceDiagram
    participant User
    participant PatreonShout

User ->>+ PatreonShout: Log in
PatreonShout -->>- User: Response with session cookie

User ->>+ PatreonShout: Clicks the "Filter" button
PatreonShout -->>- User: Reveals the "Filter" checkboxes and search bar

User ->>+ PatreonShout: Checks a category checkbox
PatreonShout -->>- User: Filters the main feed with posts only containing the category checked on the checkbox

User ->>+ PatreonShout: Types in the "Filter" section searchbar
PatreonShout -->>- User: Filters the main feed with posts only containing the specific words typed in the searchbar
```


### Use Case 3
As a user, I want to be able to search for Patreon posts from the entire list of my following creators, so I can easily get to specific posts that I want to revisit.
1. After logging into Patreon Shout, the user will be shown a Twitter-like feed of posts from the content creators they follow.
2. To search for specific Patreon posts, the user will see a large search bar near the top of their post feed and typing in this search bar will provide relevant posts based on the input provided.

```mermaid
sequenceDiagram
    participant User
    participant PatreonShout

User ->>+ PatreonShout: Log in
PatreonShout -->>- User: Response with session cookie

User ->>+ PatreonShout: Searches for content in the main search bar
PatreonShout -->>- User: Replaces the main feed with posts and creators relevant to the typed words
```


### Use Case 4
As a user, I want to be introduced to content creators on Patreon that I haven’t seen before, so I can get new sources of content that I would otherwise miss out on.
1. After logging into Patreon Shout, the user will be shown a Twitter-like feed of posts from the content creators they follow.
2. To view recommended content creators, the user will see a “Recommended'' section to the side of the feed of posts.
3. In the “Recommended” section, the user is shown a list of recommended creators based on the content creators they currently follow.

```mermaid
sequenceDiagram
    participant User
    participant PatreonShout

User ->>+ PatreonShout: Log in
PatreonShout -->>- User: Response with session cookie

User ->>+ PatreonShout: Clicks the "Recommended" section
PatreonShout -->>- User: Replaces main feed with a list of recommended creators based on their interests
```


### Use Case 5
As a user, I want to be able to view the posts of the content creators I follow in one place, so I can easily get updated on what posts have been published.
1. User signs up on the Patreon Shout website and automatically logs in.
2. After logging in, the user is prompted to link their Patreon account to their Patreon Shout account.
3. After linking accounts, the user is redirected to a Twitter-like feed of posts from the content creators they follow.

```mermaid
sequenceDiagram
    participant User
    participant PatreonShout
    participant Patreon

User ->>+ PatreonShout: Log in
PatreonShout -->>- User: Response with session cookie

User ->>+ Patreon: Allow OAuth authorization
activate Patreon
Patreon -->>+ PatreonShout: OAuth Response code
deactivate Patreon
PatreonShout ->>- Patreon: Request creator access token using Patreon API
activate Patreon
Patreon -->>+ PatreonShout: Response with creator access token
deactivate Patreon
PatreonShout -->>- User: Redirected to main feed of posts from creators they follow on Patreon
```


### Use Case 6
As a user, I want to be able to view and be notified of Patreon posts of my favorite creators on Discord, so I don’t have to use Patreon to learn about new posts and so I can be aware of the posts faster.
1. User finds the invite links to each Discord server of the content creators they follow.
2. After using the invite links to join every Discord server, the user will find the channel where Patreon notifications are output and will make sure to keep the channel unmuted for when notifications are sent out.
3. User will now receive notifications on Discord anytime there is a new Patreon post published by the creators who own the Discord server.

```mermaid
sequenceDiagram
    participant Patron
    participant Discord
    participant Creator
    participant Patreon
    participant PatreonShout


Patron ->>+ Discord: Uses Discord invite link
Discord -->>- Patron: Adds Patron to the discord server

Creator ->>+ Patreon: Publishes post to Patreon
Patreon ->>- PatreonShout: New Patron Post information
activate PatreonShout

Patron ->>+ Discord: Opens Discord channel

PatreonShout -->> Discord: Sends post to Discord channel via webhook
deactivate PatreonShout

Discord -->>- Patron: Views notification about new Patreon post
```


### Use Case 7
As a user, I want to be able to be notified of Patreon posts from my favorite creators on Instagram, so I don’t have to use Patreon to learn about new posts and so I can be aware of the posts faster.
1. User finds the Instagram accounts of each creator they follow.
2. User follows the content creator’s Instagram accounts.
3. User will now see an Instagram post on their Instagram feed anytime there is a new Patreon post published by the content creators they follow.
4. The user will see a picture and description uploaded by the creator notifying their followers about a new Patreon post, this Instagram post will not contain any information that a user would need a subscription to view.

```mermaid
sequenceDiagram
    participant Patron
    participant Instagram
    participant Creator
    participant Patreon
    participant PatreonShout


Patron ->>+ Instagram: Sends a follow request to a a content creator
Instagram -->>- Patron: Accepts the follow request

Creator ->>+ Patreon: Publishes post to Patreon
Patreon ->>- PatreonShout: New Patron Post information
activate PatreonShout

Patron ->>+ Instagram: Opens Instagram feed

PatreonShout -->> Instagram: Sends post to Instagram account via Instagram API
deactivate PatreonShout

Instagram -->>- Patron: Views notification about new Patreon post
```


### Use Case 8
As a user, I want to be able to be notified of Patreon posts from my favorite creators on Twitter, so I don’t have to use Patreon to learn about new posts and so I can be aware of the posts faster.
1. User finds the Twitter accounts of each creator they follow.
2. User follows the content creator’s Twitter accounts.
3. User will now see a Twitter post on their Twitter feed anytime there is a new Patreon post published by the content creators they follow.
4. The user will see a post uploaded by the creator notifying their followers about a new Patreon post, this Twitter post will not contain any information that a user would need a subscription to view.

```mermaid
sequenceDiagram
    participant Patron
    participant Twitter
    participant Creator
    participant Patreon
    participant PatreonShout


Patron ->>+ Twitter: Sends a follow request to a a content creator
Twitter -->>- Patron: Accepts the follow request

Creator ->>+ Patreon: Publishes post to Patreon
Patreon ->>- PatreonShout: New Patron Post information
activate PatreonShout

Patron ->>+ Twitter: Opens Twitter feed

PatreonShout -->> Twitter: Sends post to Twitter account via Twitter API
deactivate PatreonShout

Twitter -->>- Patron: Views notification about new Patreon post
```