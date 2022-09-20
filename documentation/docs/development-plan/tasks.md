---
sidebar_position: 2
---

# Tasks

Listed below are sequential tasks with story points based upon our group's estimate of their difficulty. In reality, the
time and effort required by these tasks may differ as these are only estimates. When creating these estimates, we have
taken into account our current experience with React as well as all other potential obstacles to try to make these as
accurate as possible.

1. **Create backend & database communication**
    - Create database on AWS (~2)
    - Implement CRUD operations to talk with Database(~2)
2. **Create pipeline between Patreon and a social platform**
    - Get Patreon posts using Patreon API(~2)
    - Figure out how to output to Discord using a webhook(~1)
    - Output a Patreon post to Discord(~2)
3. **Create a web server and layout for UI**
    - Create a domain name on hostinger.com (~1)
    - Host a web server on hostinger.com (~1)
    - Create a development environment to interact with the webserver (~1)
    - Create Home page prototype (~2)
    - Create Main feed component prototype (~2)
    - Create user settings component prototype (~2)
    - Add creator settings section (~1)
    - Add creator setting - link Patreon (~1)
    - Add creator setting - link Discord (~1)
    - Add creator setting - link Twitter (~1)
    - Add creator setting - link Instagram (~1)
    - Add patron settings section (~1)
    - Add patron setting - Change password (~1)
    - Add patron setting - Change main feed (~1)
    - Add patron setting - Change profile (~1)
4. Add creator guide
    - Create guide page prototype (~2)
    - Create guide - linking Patreon (~1)
    - Create guide - linking Discord (~1)
    - Create guide - create Discord webhook (~1)
    - Create guide - linking Instagram (~1)
    - Create guide - linking Twitter (~1)
5. Integrate Patreon
    - Create OAuth login (~3)
    - Save creator token to database (~1)
    - Save creator posts to database when published (~1)
    - Populate main feed for user (~1)
    - Make sure patrons who haven’t paid for a creator can’t see their private posts(~3)
6. Integrate Discord
    - Save discord webhook URL to database (~1)
    - Send creator post to given webhook URL when a post is created (~2)
7. Integrate Twitter
    - Create OAuth login (~3)
    - Save and get Twitter message template from creator (~2)
    - Send creator post to Twitter when published (~1)
8. Integrate Instagram
    - Create OAuth login (~3)
    - Save and get Instagram post template from creator(~2)
    - Send creator post to Instagram when published (~2)
9. Create contact page
    - Create contact page prototype (~2)
    - Create a contact form that asks for the user's name, contact email, and a description of why they are contacting
      us. (~1)
    - Implement email redirecting to help@patreonshout.com for contact form (~2)
10. Implement user feed tools
    - Create layout for filter section on main feed (~2)
    - Implement category post filtering (~2)
    - Implement filtering through search (~2)
    - Create button for “Lists” section (~1)
    - Create Lists page prototype (~2)
    - Implement List creation layout (~1)
    - Implement List creation functionality (~2)
    - Implements List swap for main feed -- must work with filtering (~3)
11. Create recommendation tool
    - Collect user patron campaigns and followers (~1)
    - Utilize user data (from above) to create at most 5 categories of interest (~2)
    - Return top creators from previously created categories (~1)
