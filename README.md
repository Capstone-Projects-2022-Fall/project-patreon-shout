# Patreon Shout
[![Report Issue on Jira](https://img.shields.io/badge/Report%20Issues-Jira-0052CC?style=flat&logo=jira-software)](https://temple-cis-projects-in-cs.atlassian.net/jira/software/c/projects/PS/issues)
[![Deploy Docs](https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/actions/workflows/deploy.yml/badge.svg)](https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/actions/workflows/deploy.yml)
[![Documentation Website Link](https://img.shields.io/badge/-Documentation%20Website-brightgreen)](https://capstone-projects-2022-fall.github.io/project-patreon-shout/)


# Project Abstract

Patreon Shout is a web application that allows content creators to have their Patreon posts automatically sent to other social platforms. This feature makes posting new content simple and effective. Patreon Shout also caters to users who are not content creators, which we refer to as Patrons. With our main feed, Patrons are able to see and interact with the posts of the Patreon creators they follow with tools to sort and filter their feed the way they like. These features work in tandem to provide a way to improve creator outreach and user interaction.

# High Level Requirement

From a content creator standpoint, the web application will be given a Patreon creator token and a choice of which social platforms they want their Patreon posts sent to. Then the creator will provide all information and do all actions needed to have the web application send posts to a social platform. Afterwards, each time a Patreon post is created, the post will be sent to the indicated social platform(s). 
From a patron standpoint, users will be able to sign up on the website and keep track of all the Patreon creators they follow in one place. The users will also be able to search through and filter their creator posts shown to them to find specific posts.

# Conceptual Design

Patreon Shout will consist of a frontend web application and a Java-based backend. The frontend will be made using Bootstrap and the React Javascript library while the backend will be split into three different applications/services, each with their own unique purpose(s). 
The first application will be a MySQL database that will hold all of the profile information, Patreon post data, and any creator tokens that need to be saved.
The second part will be the backend that services the web application for both the creators and users. For creators, it will acquire their campaign’s posts by making HTTP GET requests through the Patreon API with a supplied creator token.  These acquired posts will then be saved into the MySQL database, and immediately published to other social platforms. For the patrons (the non-creator users), their desired Patreon creators (e.g., creators they currently have a pledge towards, or creators they’ve manually added through the web interface) posts will be sent to the Patreon Shout web application. Handling anything that all non-creator users will directly interface with, such as filtering and searching posts.

When a Patreon creator page is loaded, it will read all creator posts and save all post data that has not yet been added to the MySQL database.
When it comes to the application backend, it will be developed in Java. This gives the benefits of having the application be platform and architecture agnostic. It will utilize the Spring Boot framework. This will allow the application to take the shape of a RESTful API, have useful features when talking to the MySQL database, and to assist in dependency injection. Spring Boot uses an approach that gives control of dependency injection to itself which allows for loose coupling and management of components.

# Background

This software application will utilize several frameworks and APIs:  
- Spring Boot – https://spring.io/projects/spring-boot  
- React Bootstrap – https://react-bootstrap.github.io  
  
The application will utilize the frameworks listed to give the creators and patrons a thought out and easy to use web interface that will require minimal clicks to complete a desired task.

The inspiration for this project came from a previous project in CIS 3296 - Software Design. This previous project was known as Patreon Discord Announcer, or PDA. This was a Discord bot that allowed users to invite the bot to their server and have it post live Patreon updates from their favorite creators. The Github page for this project can be found [here](https://github.com/apsawicki/patreon-discord-announcer/tree/version2). Patreon Shout pulls some key ideas from PDA like allowing users to see live post updates. However, we decided the Discord bot idea wouldn’t have mass appeal. Our target audience being someone who used both Discord and Patreon and needing our service.

The patron side of our application will be inspired by [Twitter’s](https://twitter.com/?lang=en) feed/timeline. A timeline is a list of updates from users you are following that can consist of search results or lists that a user has curated. This idea of a timeline is what we want for Patreon Shout. This allows us to create a central place for users to see all their followed content creators, and recommend new creators based on what they like. We believe this will help Patreon grow as a platform.

There are a lot of examples of sites that use React, which is our choice for the frontend of our application. Some of these include [Instagram](https://www.instagram.com/), [AirBnb](https://www.airbnb.com/), and [Facebook](https://www.facebook.com/). These sites use React because it is lightweight, developer friendly, and intuitive.

The reason we chose Spring Boot is because it allows us to easily manage connections to our frontend and database. Spring Boot also allows us to easily create RESTful API endpoints. An example of a successful web application built with Spring Boot would be [Udemy](https://www.udemy.com/).

An interesting project that shows why Patreon is a good candidate for a web application like this would be [Graphtreon](https://graphtreon.com/patreon-stats). This shows how Patreon’s popularity is growing and the creator side of our application could catalyze this growth.

# Required Resources

The required resources for developing this project would be a Java IDE with Maven support such as IntelliJ, Java JDK 8, Firefox on the host system, a MySQL database, an internet connection to download dependencies, and a host such as AWS to continuously run the software. AWS hosting may require payment to run the application. To acquire posts from a creator, we will need a creator Patreon token. Also, in order to output posts to other social platforms, we will need the creator’s tokens from other social platforms. Recommended hardware is preferably any system with a CPU that has at least two threads, and at least 8GB of RAM.


## Collaborators

[//]: # ( readme: collaborators -start )
<table>
<tr>
    <td align="center">
        <a href="https://github.com/Jsmalriat">
            <img src="https://avatars.githubusercontent.com/u/53412105?s=100&v=4"/>
            <br />
            <sub><b>Jonah Malriat</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/AyserJamshidi">
            <img src="https://avatars.githubusercontent.com/u/1430409?s=100&v=4"/>
            <br />
            <sub><b>Ayser Jamshidi</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/apsawicki">
            <img src="https://avatars.githubusercontent.com/u/29709311?s=100&v=4"/>
            <br />
            <sub><b>Alex Sawicki</b></sub>
        </a>
    </td>
    <td align="center">
        <a href="https://github.com/csaputro">
            <img src="https://avatars.githubusercontent.com/u/89764190?s=100&v=4"/>
            <br />
            <sub><b>Chris Saputro</b></sub>
        </a>
    </td>
    
</tr>
</table>

[//]: # ( readme: collaborators -end )
