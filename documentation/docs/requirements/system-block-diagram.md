---
sidebar_position: 2
---

# System Block Diagram

![PatreonShout_SystemBlockDiagram drawio](https://user-images.githubusercontent.com/53412105/189986549-71c0a162-3704-4213-b7de-9193a35a71cf.png)

Figure 1. High level design of the Patreon Shout application

Figure 1 depicts the high-level design of the application from the perspective of patrons and creators. From the content creator’s perspective, they decide to use Patreon Shout to forward their Patreon posts to other platforms, so that their outreach to future potential patrons is greater. To do this they signup to Patreon Shout by logging into the application through Patreon. Then they select which social platforms they wish to output their posts to and complete all tasks required to output to the chosen social platforms. Afterwards, a post parser will use Patreon’s API to collect the creator’s posts on Patreon and save them in the MySQL database. Those posts will subsequently be given to our Java backend and be output to the creator specified social platforms. Then, from a patron perspective, they will be able to see these posts on the specified social platforms. Furthermore, a patron will also be able to view their following content creators on the Patreon Shout website. To do this, they will initially follow the same procedure as the content creators do by signing up to Patreon Shout through Patreon. Then Patreon Shout will automatically get the creators that the patron follows and will parse the posts of those creators at the post parser. Again, the posts will be saved to the database, and then brought to the Java backend where instead of outputting to social platforms, it will output posts to the Patreon Shout website for the patron to view.
