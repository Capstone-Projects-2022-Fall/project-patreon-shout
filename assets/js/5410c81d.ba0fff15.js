"use strict";(self.webpackChunktu_cis_4398_docs_template=self.webpackChunktu_cis_4398_docs_template||[]).push([[654],{3905:function(e,t,a){a.d(t,{Zo:function(){return u},kt:function(){return d}});var n=a(67294);function r(e,t,a){return t in e?Object.defineProperty(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}function i(e,t){var a=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),a.push.apply(a,n)}return a}function o(e){for(var t=1;t<arguments.length;t++){var a=null!=arguments[t]?arguments[t]:{};t%2?i(Object(a),!0).forEach((function(t){r(e,t,a[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(a)):i(Object(a)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(a,t))}))}return e}function l(e,t){if(null==e)return{};var a,n,r=function(e,t){if(null==e)return{};var a,n,r={},i=Object.keys(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||(r[a]=e[a]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(n=0;n<i.length;n++)a=i[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(r[a]=e[a])}return r}var s=n.createContext({}),p=function(e){var t=n.useContext(s),a=t;return e&&(a="function"==typeof e?e(t):o(o({},t),e)),a},u=function(e){var t=p(e.components);return n.createElement(s.Provider,{value:t},e.children)},m={inlineCode:"code",wrapper:function(e){var t=e.children;return n.createElement(n.Fragment,{},t)}},c=n.forwardRef((function(e,t){var a=e.components,r=e.mdxType,i=e.originalType,s=e.parentName,u=l(e,["components","mdxType","originalType","parentName"]),c=p(a),d=r,k=c["".concat(s,".").concat(d)]||c[d]||m[d]||i;return a?n.createElement(k,o(o({ref:t},u),{},{components:a})):n.createElement(k,o({ref:t},u))}));function d(e,t){var a=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=a.length,o=new Array(i);o[0]=c;var l={};for(var s in t)hasOwnProperty.call(t,s)&&(l[s]=t[s]);l.originalType=e,l.mdxType="string"==typeof e?e:r,o[1]=l;for(var p=2;p<i;p++)o[p]=a[p];return n.createElement.apply(null,o)}return n.createElement.apply(null,a)}c.displayName="MDXCreateElement"},63144:function(e,t,a){a.r(t),a.d(t,{assets:function(){return u},contentTitle:function(){return s},default:function(){return d},frontMatter:function(){return l},metadata:function(){return p},toc:function(){return m}});var n=a(83117),r=a(80102),i=(a(67294),a(3905)),o=["components"],l={sidebar_position:1},s="Activities",p={unversionedId:"development-plan/activities",id:"development-plan/activities",title:"Activities",description:"Requirements Gathering",source:"@site/docs/development-plan/activities.md",sourceDirName:"development-plan",slug:"/development-plan/activities",permalink:"/project-patreon-shout/docs/development-plan/activities",draft:!1,editUrl:"https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/edit/main/documentation/docs/development-plan/activities.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"docsSidebar",previous:{title:"Software Development Plan",permalink:"/project-patreon-shout/docs/category/software-development-plan"},next:{title:"Tasks",permalink:"/project-patreon-shout/docs/development-plan/tasks"}},u={},m=[{value:"Requirements Gathering",id:"requirements-gathering",level:3},{value:"Top-Level Design",id:"top-level-design",level:3},{value:"Detailed Design",id:"detailed-design",level:3},{value:"Testing",id:"testing",level:3}],c={toc:m};function d(e){var t=e.components,a=(0,r.Z)(e,o);return(0,i.kt)("wrapper",(0,n.Z)({},c,a,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"activities"},"Activities"),(0,i.kt)("h3",{id:"requirements-gathering"},"Requirements Gathering"),(0,i.kt)("p",null,"In order to fulfill the requirements proposed later in this document, our team plans to analyze the needs of content\ncreators and patrons of Patreon and use the findings to actualize the specific points into our application. The previous\nversion of our application showed us what things we did well and what things needed improvement on both the software we\ncreated and the idea behind the software. We also are in direct contact with a content creator who has expressed his\nconcerns about Patreon and cross-posting to drive our ideas on what details are needed in our application for content\ncreators. Furthermore, we are in direct contact with active patrons of content creators on Patreon who have expressed\ntheir concerns about Patreon and their content viewing experience that drive our ideas on what details are needed in our\napplication for patrons. The information we gathered will allow us to build prototypes of our application and the steps\nwe will take to complete this are listed below."),(0,i.kt)("h3",{id:"top-level-design"},"Top-Level Design"),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},"Create backend & database communication"),(0,i.kt)("li",{parentName:"ol"},"Create pipeline between Patreon and a social platform"),(0,i.kt)("li",{parentName:"ol"},"Create web server and layout for UI"),(0,i.kt)("li",{parentName:"ol"},"Implement user settings"),(0,i.kt)("li",{parentName:"ol"},"Add creator guide"),(0,i.kt)("li",{parentName:"ol"},"Integrate Patreon"),(0,i.kt)("li",{parentName:"ol"},"Integrate Discord"),(0,i.kt)("li",{parentName:"ol"},"Integrate Twitter"),(0,i.kt)("li",{parentName:"ol"},"Integrate Instagram"),(0,i.kt)("li",{parentName:"ol"},"Database management"),(0,i.kt)("li",{parentName:"ol"},"Create contact page"),(0,i.kt)("li",{parentName:"ol"},"Implement user feed tools"),(0,i.kt)("li",{parentName:"ol"},"Create recommendation engine"),(0,i.kt)("li",{parentName:"ol"},"Testing")),(0,i.kt)("h3",{id:"detailed-design"},"Detailed Design"),(0,i.kt)("ol",null,(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Create backend & database communication"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Create MySQL database",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Database will utilize AWS RDS to host a MySQL 8 instance."),(0,i.kt)("li",{parentName:"ul"},"Database will be used to store usernames, passwords, creator tokens, following creators, patreon posts, social\nplatform tokens, webhooks, and patron lists\nCRUD operations to communicate with database"))),(0,i.kt)("li",{parentName:"ul"},"CRUD operations to communicate with database"))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Create pipeline between Patreon and a social platform"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Get posts from Patreon API",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Call the Patreon API and save the information received in the database"))),(0,i.kt)("li",{parentName:"ul"},"Send posts to Discord using webhooks",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Send the posts from the database to Discord using a webhook"))))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Create web server and layout for UI"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Web server",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Web server will utilize hostinger.com for the following:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Domain name"),(0,i.kt)("li",{parentName:"ul"},"Web hosting"))))),(0,i.kt)("li",{parentName:"ul"},"Home Page",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Main Feed"),(0,i.kt)("li",{parentName:"ul"},"User settings",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Creator settings",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"This page will let the creator link Patreon to social platforms",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Requires the user to provide a Patreon creator token"))),(0,i.kt)("li",{parentName:"ul"},"Social platform selection includes the following:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Discord"),(0,i.kt)("li",{parentName:"ul"},"Twitter"),(0,i.kt)("li",{parentName:"ul"},"Instagram"))))),(0,i.kt)("li",{parentName:"ul"},"Patron settings",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"This page will let the patron do the following:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Change password"),(0,i.kt)("li",{parentName:"ul"},"Change main feed settings"),(0,i.kt)("li",{parentName:"ul"},"Change profile settings"))))))),(0,i.kt)("li",{parentName:"ul"},"Sign-in page",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"User can provide login details to proceed to the website"),(0,i.kt)("li",{parentName:"ul"},"Information will be validated in the database before approving sign-in"))),(0,i.kt)("li",{parentName:"ul"},"Registration page",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"User can provide account details to create an account"),(0,i.kt)("li",{parentName:"ul"},"Validation from email"))),(0,i.kt)("li",{parentName:"ul"},"Secure database password storage",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Upon sign-in information will be transferred from client(user) to server via TLS 1.3 encryption"),(0,i.kt)("li",{parentName:"ul"},"Password will be encrypted immediately and won\u2019t be stored without encryption"))))))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Add creator guide"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Website will have guides for the creator to help them link Patreon to social platforms"),(0,i.kt)("li",{parentName:"ul"},"These guides will include the following:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"How to get a Patreon creator token"),(0,i.kt)("li",{parentName:"ul"},"How to generate a Discord webhook"),(0,i.kt)("li",{parentName:"ul"},"How to provide Patreon Shout with authorization to post on their account"))))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Integrate Patreon"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"These things will happen when a user provides a Patreon creator token:",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Creator token is verified"),(0,i.kt)("li",{parentName:"ul"},"Creator token is used to get user information",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Following creators is saved and set onto their Patreon Shout profile"))),(0,i.kt)("li",{parentName:"ul"},"Social integration selection menu is shown to the user to allow their Patreon posts to be automatically sent\nto other social platforms"))))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Integrate Discord"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"User creates and provides a Discord webhook via a guide provided"),(0,i.kt)("li",{parentName:"ul"},"Whenever a Patreon post is published, a Discord message is sent using the webhook providing the post details"))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Integrate Twitter"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"User authenticates and approves authorization via login dialog"),(0,i.kt)("li",{parentName:"ul"},"User provides a template for the message they want to be sent on their Twitter account"),(0,i.kt)("li",{parentName:"ul"},"Whenever a Patreon post is created, a tweet will be published on the user\u2019s Twitter account providing the post\ndetails"))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Integrate Instagram"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"User authenticates and approves permissions via login dialog"),(0,i.kt)("li",{parentName:"ul"},"User provides images they want to send and a caption for the image they provide"),(0,i.kt)("li",{parentName:"ul"},"Whenever a Patreon post is created, the images and caption will be published onto their Instagram account\nproviding the post details"))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Create contact page"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"User can do the following on the contact page",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Allow the user to report bugs"),(0,i.kt)("li",{parentName:"ul"},"Submit user contact information as well as a paragraph of text explaining the reason for contact"))))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Implement user feed tools"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Search bar",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"This feature lets the user search for posts from their following creators at the top of the screen"))),(0,i.kt)("li",{parentName:"ul"},"Post filtering",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"This feature lets the user filter through following creator\u2019s posts to find specific posts"))),(0,i.kt)("li",{parentName:"ul"},"Creator lists",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Replace the main feed with a list of specified creator\u2019s posts"),(0,i.kt)("li",{parentName:"ul"},"There will be a create a list option that will let you search through creators to add to the list"))))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Create recommendation tool"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Tool will take keywords from posts and compare them to find content users might be interested in"))),(0,i.kt)("li",{parentName:"ol"},(0,i.kt)("strong",{parentName:"li"},"Testing"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Tests will be done most sprints in different phases. Types of testing are explained below")))),(0,i.kt)("h3",{id:"testing"},"Testing"),(0,i.kt)("p",null,"We will be using 3 different types of testing for PatreonShout. These include unit testing, integration testing and\nacceptance testing."),(0,i.kt)("ins",null,"Unit:"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"Our team will utilize JUnit testing framework to implement unit tests for our Java code and Jest to implement React\nunit tests. There will be a test for each critical component to ensure proper functionality.")),(0,i.kt)("ins",null,"Integration:"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"Integration testing will be done using Mockito to mock API endpoints in Java to ensure the application properly\nhandles this communication. Jest will be used alongside React to mock objects and ensure smooth user flow.")),(0,i.kt)("ins",null,"Acceptance:"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"Use Case Acceptance Testing will be performed to ensure that certain features are functional and match the users\u2019\nexpectations.")))}d.isMDXComponent=!0}}]);