---
sidebar_position: 1
---

# System Overview

## Project Abstract

This document proposes a web application that allows content creators to have their Patreon posts automatically sent to other social platforms. Non-content creator users of this web application will also be able to see the posts of the Patreon creators they follow. This will improve creator outreach to users of the web application by showing recommended creators to follow.

## Conceptual Design

Patreon Shout will consist of a frontend web application and a Java-based backend. The frontend will be made using Bootstrap and the React Javascript library while the backend will be split into three different applications/services, each with their own unique purpose(s). 
The first application will be a MySQL database that will hold all of the profile information, Patreon post data, and any creator tokens that need to be saved.
The second part will be the backend that services the web application for both the creators and users. For creators, it will acquire their campaign’s posts by making HTTP GET requests through the Patreon API with a supplied creator token.  These acquired posts will then be saved into the MySQL database, and immediately published to other social platforms. For the patrons (the non-creator users), their desired Patreon creators (e.g., creators they currently have a pledge towards, or creators they’ve manually added through the web interface) posts will be sent to the Patreon Shout web application. Handling anything that all non-creator users will directly interface with, such as filtering and searching posts.

The final part of the backend will be the web-scraping of Patreon creator posts. This is required to get and serve post data from creators who have not supplied their creator token to Patreon Shout. It will utilize the Selenium WebDriver to load Patreon creator pages in an automated headless Firefox browser and will run at scheduled times. When a Patreon creator page is loaded, it will read all creator posts and save all post data that has not yet been added to the MySQL database.
When it comes to the application backend, it will be developed in Java. This gives the benefits of having the application be platform and architecture agnostic. It will utilize the Spring Boot framework. This will allow the application to take the shape of a RESTful API, have useful features when talking to the MySQL database, and to assist in dependency injection. Spring Boot uses an approach that gives control of dependency injection to itself which allows for loose coupling and management of components.

## Background

This software application will utilize several frameworks and APIs:  
- Selenium framework – https://github.com/SeleniumHQ/selenium  
- Spring Boot – https://spring.io/projects/spring-boot  
- React Bootstrap – https://react-bootstrap.github.io  
  
The application will utilize the frameworks listed to give the creators and patrons a thought out and easy to use web interface that will require minimal clicks to complete a desired task.

The inspiration for this project came from a previous project in CIS 3296 - Software Design. This previous project was known as Patreon Discord Announcer, or PDA. This was a Discord bot that allowed users to invite the bot to their server and have it post live Patreon updates from their favorite creators. The Github page for this project can be found [here](https://github.com/apsawicki/patreon-discord-announcer/tree/version2). Patreon Shout pulls some key ideas from PDA like allowing users to see live post updates. However, we decided the Discord bot idea wouldn’t have mass appeal. Our target audience being someone who used both Discord and Patreon and needing our service.

The patron side of our application will be inspired by [Twitter’s](https://twitter.com/?lang=en) feed/timeline. A timeline is a list of updates from users you are following that can consist of search results or lists that a user has curated. This idea of a timeline is what we want for Patreon Shout. This allows us to create a central place for users to see all their followed content creators, and recommend new creators based on what they like. We believe this will help Patreon grow as a platform.

There are a lot of examples of sites that use React, which is our choice for the frontend of our application. Some of these include [Instagram](https://www.instagram.com/), [AirBnb](https://www.airbnb.com/), and [Facebook](https://www.facebook.com/). These sites use React because it is lightweight, developer friendly, and intuitive.

The reason we chose Spring Boot is because it allows us to easily manage connections to our frontend and database. Spring Boot also allows us to easily create RESTful API endpoints. An example of a successful web application built with Spring Boot would be [Udemy](https://www.udemy.com/).

An interesting project that shows why Patreon is a good candidate for a web application like this would be [Graphtreon](https://graphtreon.com/patreon-stats). This shows how Patreon’s popularity is growing and the creator side of our application could catalyze this growth.
