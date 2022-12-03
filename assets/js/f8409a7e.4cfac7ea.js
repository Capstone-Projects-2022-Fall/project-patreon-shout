"use strict";(self.webpackChunktu_cis_4398_docs_template=self.webpackChunktu_cis_4398_docs_template||[]).push([[206],{3905:function(e,t,a){a.d(t,{Zo:function(){return p},kt:function(){return d}});var o=a(67294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function n(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);t&&(o=o.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,o)}return a}function i(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?n(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):n(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function s(e,t){if(null==e)return{};var a,o,r=function(e,t){if(null==e)return{};var a,o,r={},n=Object.keys(e);for(o=0;o<n.length;o++)a=n[o],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);for(o=0;o<n.length;o++)a=n[o],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var l=o.createContext({}),c=function(e){var t=o.useContext(l),a=t;return e&&(a="function"==typeof e?e(t):i(i({},t),e)),a},p=function(e){var t=c(e.components);return o.createElement(l.Provider,{value:t},e.children)},h={inlineCode:"code",wrapper:function(e){var t=e.children;return o.createElement(o.Fragment,{},t)}},u=o.forwardRef((function(e,t){var a=e.components,r=e.mdxType,n=e.originalType,l=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),u=c(a),d=r,f=u["".concat(l,".").concat(d)]||u[d]||h[d]||n;return a?o.createElement(f,i(i({ref:t},p),{},{components:a})):o.createElement(f,i({ref:t},p))}));function d(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var n=a.length,i=new Array(n);i[0]=u;var s={};for(var l in t)hasOwnProperty.call(t,l)&&(s[l]=t[l]);s.originalType=e,s.mdxType="string"==typeof e?e:r,i[1]=s;for(var c=2;c<n;c++)i[c]=a[c];return o.createElement.apply(null,i)}return o.createElement.apply(null,a)}u.displayName="MDXCreateElement"},69568:function(e,t,a){a.r(t),a.d(t,{assets:function(){return p},contentTitle:function(){return l},default:function(){return d},frontMatter:function(){return s},metadata:function(){return c},toc:function(){return h}});var o=a(83117),r=a(80102),n=(a(67294),a(3905)),i=["components"],s={sidebar_position:1},l="Project Abstract",c={unversionedId:"intro",id:"intro",title:"Project Abstract",description:"Patreon Shout is a web application that allows content creators to have their Patreon posts automatically sent to other social platforms. This feature makes posting new content simple and effective. Patreon Shout also caters to users who are not content creators, which we refer to as Patrons. With our main feed, Patrons are able to see and interact with the posts of the Patreon creators they follow with tools to sort and filter their feed the way they like. These features work in tandem to provide a way to improve creator outreach and user interaction.",source:"@site/docs/intro.mdx",sourceDirName:".",slug:"/intro",permalink:"/project-patreon-shout/docs/intro",draft:!1,editUrl:"https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/edit/main/documentation/docs/intro.mdx",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"docsSidebar",next:{title:"Requirements Specification",permalink:"/project-patreon-shout/docs/category/requirements-specification"}},p={},h=[{value:"Collaborators",id:"collaborators",level:2}],u={toc:h};function d(e){var t=e.components,a=(0,r.Z)(e,i);return(0,n.kt)("wrapper",(0,o.Z)({},u,a,{components:t,mdxType:"MDXLayout"}),(0,n.kt)("h1",{id:"project-abstract"},"Project Abstract"),(0,n.kt)("p",null,"Patreon Shout is a web application that allows content creators to have their Patreon posts automatically sent to other social platforms. This feature makes posting new content simple and effective. Patreon Shout also caters to users who are not content creators, which we refer to as Patrons. With our main feed, Patrons are able to see and interact with the posts of the Patreon creators they follow with tools to sort and filter their feed the way they like. These features work in tandem to provide a way to improve creator outreach and user interaction."),(0,n.kt)("h1",{id:"high-level-requirement"},"High Level Requirement"),(0,n.kt)("p",null,"From a content creator standpoint, the web application will be given a Patreon creator token and a choice of which social platforms they want their Patreon posts sent to. Then the creator will provide all information and do all actions needed to have the web application send posts to a social platform. Afterwards, each time a Patreon post is created, the post will be sent to the indicated social platform(s).\nFrom a patron standpoint, users will be able to sign up on the website and keep track of all the Patreon creators they follow in one place. The users will also be able to search through and filter their creator posts shown to them to find specific posts."),(0,n.kt)("h1",{id:"conceptual-design"},"Conceptual Design"),(0,n.kt)("p",null,"Patreon Shout will consist of a frontend web application and a Java-based backend. The frontend will be made using the React Javascript library while the backend will be split into three different applications/services, each with their own unique purpose(s). The first application will be a MySQL database that will hold all of the profile information, Patreon post data, and any creator tokens that need to be saved. The second part will be the backend that services the web application for both the creators and users. For creators, it will acquire their campaign\u2019s posts by making HTTP GET requests through the Patreon API with a supplied creator token. These acquired posts will then be saved into the MySQL database, and immediately published to other social platforms. For the patrons (the non-creator users), their desired Patreon creators (e.g., creators they currently have a pledge towards, or creators they\u2019ve manually added through the web interface) posts will be sent to the Patreon Shout web application. Handling anything that all non-creator users will directly interface with, such as filtering and searching posts."),(0,n.kt)("p",null,"When a Patreon creator page is loaded, it will read all creator posts and save all post data that has not yet been added to the MySQL database. When it comes to the application backend, it will be developed in Java. This gives the benefits of having the application be platform and architecture agnostic. It will utilize the Spring Boot framework. This will allow the application to take the shape of a RESTful API, have useful features when talking to the MySQL database, and to assist in dependency injection. Spring Boot uses an approach that gives control of dependency injection to itself which allows for loose coupling and management of components."),(0,n.kt)("h1",{id:"background"},"Background"),(0,n.kt)("p",null,"This software application will utilize several frameworks and APIs:  "),(0,n.kt)("ul",null,(0,n.kt)("li",{parentName:"ul"},"Spring Boot \u2013 ",(0,n.kt)("a",{parentName:"li",href:"https://spring.io/projects/spring-boot"},"https://spring.io/projects/spring-boot"),"  "),(0,n.kt)("li",{parentName:"ul"},"React JS \u2013 ",(0,n.kt)("a",{parentName:"li",href:"https://reactjs.org/"},"https://reactjs.org/"))),(0,n.kt)("p",null,"The application will utilize the frameworks listed to give the creators and patrons a thought out and easy to use web interface that will require minimal clicks to complete a desired task."),(0,n.kt)("p",null,"The inspiration for this project came from a previous project in CIS 3296 - Software Design. This previous project was known as Patreon Discord Announcer, or PDA.\nThis was a Discord bot that allowed users to invite the bot to their server and have it post live Patreon updates from their favorite creators.\nThe Github page for this project can be found ",(0,n.kt)("a",{parentName:"p",href:"https://github.com/apsawicki/patreon-discord-announcer/tree/version2"},"here"),". Patreon Shout pulls some key ideas from PDA like allowing users to see live post updates.\nHowever, we decided the Discord bot idea wouldn\u2019t have mass appeal. Our target audience being someone who used both Discord and Patreon and needing our service."),(0,n.kt)("p",null,"The patron side of our application will be inspired by ",(0,n.kt)("a",{parentName:"p",href:"https://twitter.com/?lang=en"},"Twitter\u2019s")," feed/timeline. A timeline is a list of updates from users you are following that can consist of search results or lists that a user has curated. This idea of a timeline is what we want for Patreon Shout. This allows us to create a central place for users to see all their followed content creators. We believe this will help Patreon grow as a platform."),(0,n.kt)("p",null,"There are a lot of examples of sites that use React, which is our choice for the frontend of our application. Some of these include ",(0,n.kt)("a",{parentName:"p",href:"https://www.instagram.com/"},"Instagram"),", ",(0,n.kt)("a",{parentName:"p",href:"https://www.airbnb.com/"},"AirBnb"),", and ",(0,n.kt)("a",{parentName:"p",href:"https://www.facebook.com/"},"Facebook"),". These sites use React because it is lightweight, developer friendly, and intuitive."),(0,n.kt)("p",null,"The reason we chose Spring Boot is because it allows us to easily manage connections to our frontend and database. Spring Boot also allows us to easily create RESTful API endpoints. An example of a successful web application built with Spring Boot would be ",(0,n.kt)("a",{parentName:"p",href:"https://www.udemy.com/"},"Udemy"),"."),(0,n.kt)("p",null,"An interesting project that shows why Patreon is a good candidate for a web application like this would be ",(0,n.kt)("a",{parentName:"p",href:"https://graphtreon.com/patreon-stats"},"Graphtreon"),". This shows how Patreon\u2019s popularity is growing and the creator side of our application could catalyze this growth."),(0,n.kt)("h1",{id:"required-resources"},"Required Resources"),(0,n.kt)("p",null,"The required resources for developing this project would be a Java IDE with Maven support such as IntelliJ, Java JDK 8, a MySQL database, an internet connection to download dependencies, and a host such as AWS to continuously run the software. AWS hosting may require payment to run the application. To acquire posts from a creator, we will need a creator Patreon token. Also, in order to output posts to other social platforms, we will need the creator\u2019s tokens from other social platforms. Recommended hardware is preferably any system with a CPU that has at least two threads, and at least 8GB of RAM."),(0,n.kt)("h2",{id:"collaborators"},"Collaborators"),(0,n.kt)("table",null,(0,n.kt)("tr",null,(0,n.kt)("td",{align:"center"},(0,n.kt)("a",{href:"https://github.com/Jsmalriat"},(0,n.kt)("img",{src:"https://avatars.githubusercontent.com/u/53412105?s=100&v=4"}),(0,n.kt)("br",null),(0,n.kt)("sub",null,(0,n.kt)("b",null,"Jonah Malriat")))),(0,n.kt)("td",{align:"center"},(0,n.kt)("a",{href:"https://github.com/AyserJamshidi"},(0,n.kt)("img",{src:"https://avatars.githubusercontent.com/u/1430409?s=100&v=4"}),(0,n.kt)("br",null),(0,n.kt)("sub",null,(0,n.kt)("b",null,"Ayser Jamshidi")))),(0,n.kt)("td",{align:"center"},(0,n.kt)("a",{href:"https://github.com/apsawicki"},(0,n.kt)("img",{src:"https://avatars.githubusercontent.com/u/29709311?s=100&v=4"}),(0,n.kt)("br",null),(0,n.kt)("sub",null,(0,n.kt)("b",null,"Alex Sawicki")))),(0,n.kt)("td",{align:"center"},(0,n.kt)("a",{href:"https://github.com/csaputro"},(0,n.kt)("img",{src:"https://avatars.githubusercontent.com/u/89764190?s=100&v=4"}),(0,n.kt)("br",null),(0,n.kt)("sub",null,(0,n.kt)("b",null,"Chris Saputro")))))))}d.isMDXComponent=!0}}]);