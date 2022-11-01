"use strict";(self.webpackChunktu_cis_4398_docs_template=self.webpackChunktu_cis_4398_docs_template||[]).push([[1270],{3905:function(e,t,n){n.d(t,{Zo:function(){return p},kt:function(){return c}});var a=n(67294);function r(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function i(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function l(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?i(Object(n),!0).forEach((function(t){r(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):i(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function s(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},i=Object.keys(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var o=a.createContext({}),u=function(e){var t=a.useContext(o),n=t;return e&&(n="function"==typeof e?e(t):l(l({},t),e)),n},p=function(e){var t=u(e.components);return a.createElement(o.Provider,{value:t},e.children)},m={inlineCode:"code",wrapper:function(e){var t=e.children;return a.createElement(a.Fragment,{},t)}},d=a.forwardRef((function(e,t){var n=e.components,r=e.mdxType,i=e.originalType,o=e.parentName,p=s(e,["components","mdxType","originalType","parentName"]),d=u(n),c=r,k=d["".concat(o,".").concat(c)]||d[c]||m[c]||i;return n?a.createElement(k,l(l({ref:t},p),{},{components:n})):a.createElement(k,l({ref:t},p))}));function c(e,t){var n=arguments,r=t&&t.mdxType;if("string"==typeof e||r){var i=n.length,l=new Array(i);l[0]=d;var s={};for(var o in t)hasOwnProperty.call(t,o)&&(s[o]=t[o]);s.originalType=e,s.mdxType="string"==typeof e?e:r,l[1]=s;for(var u=2;u<i;u++)l[u]=n[u];return a.createElement.apply(null,l)}return a.createElement.apply(null,n)}d.displayName="MDXCreateElement"},10770:function(e,t,n){n.r(t),n.d(t,{assets:function(){return p},contentTitle:function(){return o},default:function(){return c},frontMatter:function(){return s},metadata:function(){return u},toc:function(){return m}});var a=n(83117),r=n(80102),i=(n(67294),n(3905)),l=["components"],s={sidebar_position:1},o="Unit tests",u={unversionedId:"testing/unit-testing",id:"testing/unit-testing",title:"Unit tests",description:"Our team will utilize JUnit testing framework to implement unit tests for our Java code and Jest to implement React unit tests. There will be a test for each critical component to ensure proper functionality",source:"@site/docs/testing/unit-testing.md",sourceDirName:"testing",slug:"/testing/unit-testing",permalink:"/project-patreon-shout/docs/testing/unit-testing",draft:!1,editUrl:"https://github.com/Capstone-Projects-2022-Fall/project-patreon-shout/edit/main/documentation/docs/testing/unit-testing.md",tags:[],version:"current",sidebarPosition:1,frontMatter:{sidebar_position:1},sidebar:"docsSidebar",previous:{title:"Test Procedures",permalink:"/project-patreon-shout/docs/category/test-procedures"},next:{title:"Integration tests",permalink:"/project-patreon-shout/docs/testing/integration-testing"}},p={},m=[{value:"Back-end",id:"back-end",level:3},{value:"Front-end",id:"front-end",level:3}],d={toc:m};function c(e){var t=e.components,n=(0,r.Z)(e,l);return(0,i.kt)("wrapper",(0,a.Z)({},d,n,{components:t,mdxType:"MDXLayout"}),(0,i.kt)("h1",{id:"unit-tests"},"Unit tests"),(0,i.kt)("p",null,"Our team will utilize JUnit testing framework to implement unit tests for our Java code and Jest to implement React unit tests. There will be a test for each critical component to ensure proper functionality"),(0,i.kt)("h3",{id:"back-end"},"Back-end"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},"PostsRepository",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testPutPostAndGetAllPosts()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: See if posts are put into the database"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if getting all the posts in the database returns each post added"))),(0,i.kt)("li",{parentName:"ul"},"testGetPost()"))),(0,i.kt)("li",{parentName:"ul"},"Test: See if post can be retrieved from the database using the post url\nResult: Pass if the post put into the database is returned with all matching data",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testUpdatePost()\nTest: See if a post can be updated with new information\nResult: Pass if the post in the database is updated with new information"),(0,i.kt)("li",{parentName:"ul"},"testRemovePost()\nTest: See if a post can be removed from the database using the post url\nResult: Pass if the size of all posts in the database is 0 after removing the only post"))),(0,i.kt)("li",{parentName:"ul"},"DiscordSender",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testClose()\nTest: See if the WebhookClient closes by setting a time limit on the test\nResult: Pass if the test doesn\u2019t throw an error after a set amount of time"),(0,i.kt)("li",{parentName:"ul"},"testSetImage()\nTest: See if the WebhookEmbedBuilder holds an image\nResult: Pass if the embed has an image"),(0,i.kt)("li",{parentName:"ul"},"testAddField()\nTest: See if the WebhookEmbedBuilder holds a field with a title and value\nResult: Pass if the embed has a title and value"),(0,i.kt)("li",{parentName:"ul"},"testSetDescription()\nTest: See if the WebhookEmbedBuilder holds a description\nResult: Pass if the embed has a description"),(0,i.kt)("li",{parentName:"ul"},"testSetTitle()\nTest: See if the WebhookEmbedBuilder holds a title\nResult: Pass if the embed has a title"),(0,i.kt)("li",{parentName:"ul"},"testSetColor()\nTest: See if the WebhookEmbedBuilder holds a color value\nResult: Pass if the embed has a color"),(0,i.kt)("li",{parentName:"ul"},"testSend()\nTest: See if the WebhookClient sends an embed to Discord\nResult: Pass if Discord gets sent the embed holding data"))),(0,i.kt)("li",{parentName:"ul"},"CustomPatreonAPI",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testFetchPosts()\nTest: See if http request sent to Patreon returns Patreon posts with given campaignId\nResult: Pass if we get post data from http request"),(0,i.kt)("li",{parentName:"ul"},"testFetchFollowingCampaigns( )\nTest: See if http request sent to Patreon returns the Patreon campaigns the user follows with given access_token\nResult: Pass if we get campaign data from the http request"),(0,i.kt)("li",{parentName:"ul"},"testFetchUser()\nTest: See if http request sent to Patreon returns the Patreon user\nResult: Pass if we get the user data from the http request"))),(0,i.kt)("li",{parentName:"ul"},"PatreonInfoSvc",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testPutPatreonInfoSuccess()\nTest: Send valid patreon info PUT request to the patreon info endpoint\nResult: Pass if patreon info is added to the database"),(0,i.kt)("li",{parentName:"ul"},"testPutPatreonInfoFailure()\nTest: Send invalid patreon info PUT request to the patreon info endpoint\nResult: Pass if patreon info isn\u2019t added to the database"),(0,i.kt)("li",{parentName:"ul"},"testGetPatreonInfoSuccess()\nTest: Send valid patreon info GET request to the patreon info endpoint\nResult: Pass if information received is valid"),(0,i.kt)("li",{parentName:"ul"},"testGetPatreonInfoFailure()\nTest: Send invalid patreon info GET request to the patreon info endpoint\nResult: Pass if information received isn\u2019t valid"))),(0,i.kt)("li",{parentName:"ul"},"WebAccountSvc",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testLoginSuccess()\nTest: Send valid login credentials to the login endpoint\nResult: Assert receiving an HTTP response code of 200 (OK)  and a login token"),(0,i.kt)("li",{parentName:"ul"},"testLoginFailure()\nTest: Send invalid login credentials to the login endpoint\nResult: Assert receiving an HTTP response code of 401 (Unauthorized)"),(0,i.kt)("li",{parentName:"ul"},"testRegisterSuccess()\nTest: Send registration credentials with legal characters in the username\nResult: Assert receiving an HTTP response code of 200 (OK)"),(0,i.kt)("li",{parentName:"ul"},"testRegisterFailure()\nTest: Send registration credentials with illegal characters in the username\nResult: Assert receiving an HTTP response code of 422 (Unprocessable Entity)"),(0,i.kt)("li",{parentName:"ul"},"testIntegrationSuccess()\nTest: Send integration details with a WebAccount ID that exists\nResult: Assert receiving an HTTP response code of 200 (OK)"),(0,i.kt)("li",{parentName:"ul"},"testIntegrationFailure()\nTest: Send integration details with a WebAccount ID that does not exist\nResult: Assert receiving an HTTP response code of 409 (Conflict)")))),(0,i.kt)("h3",{id:"front-end"},"Front-end"),(0,i.kt)("ul",null,(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"index.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testWebsiteRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of the website"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if website correctly renders without errors"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"App.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testTokenIsCreated()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Ensure token is created after successful login"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if token is not empty"))),(0,i.kt)("li",{parentName:"ul"},"testRoutesAreValid()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct routing between pages"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the website will route to all pages"))),(0,i.kt)("li",{parentName:"ul"},"testImportHomeIsNotNull()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Ensure home component is correctly imported"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if home component is found"))),(0,i.kt)("li",{parentName:"ul"},"testImportLoginIsNotNull()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Ensure login component is correctly imported"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login component is found"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"Login.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testLoginPageRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of login page"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login page renders"))),(0,i.kt)("li",{parentName:"ul"},"testRoutingToLoginPage()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct routing to login page"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if routed to the login page"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"FormContainers.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testFormContainerRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of FormContainers components"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if FormContainers components render"))),(0,i.kt)("li",{parentName:"ul"},"testTabPanelRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of the TabPanel component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if TabPanel component renders"))),(0,i.kt)("li",{parentName:"ul"},"testLoginWindowRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of the login window"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login window component renders"))),(0,i.kt)("li",{parentName:"ul"},"testRegistrationWindowRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of the registration window"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if registration window component renders"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"LoginForm.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testTitleComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of login form title component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login form title component renders"))),(0,i.kt)("li",{parentName:"ul"},"testUsernameTextboxRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of login form username text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if Username text box component renders"))),(0,i.kt)("li",{parentName:"ul"},"testPasswordTextboxRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of login form password text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login form password text box renders"))),(0,i.kt)("li",{parentName:"ul"},"testLoginButtonRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of login form login button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login form login button renders"))),(0,i.kt)("li",{parentName:"ul"},"testLoginButtonFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of login form login button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we can receive the text from the login form login button after clicking submit"))),(0,i.kt)("li",{parentName:"ul"},"testLogoRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of login form logo"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if login form logo renders"))),(0,i.kt)("li",{parentName:"ul"},"testInvalidLoginEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Invalid login entry submission"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))),(0,i.kt)("li",{parentName:"ul"},"testNullLoginEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: No information login entry submission"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"RegistrationForm.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testTitleComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of registration form title component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if registration form title component renders"))),(0,i.kt)("li",{parentName:"ul"},"testEmailTextboxRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of registration form email text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if email text box renders correctly"))),(0,i.kt)("li",{parentName:"ul"},"testUsernameTextboxRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of registration form username text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if username text box renders correctly"))),(0,i.kt)("li",{parentName:"ul"},"testPasswordTextboxRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of registration form password text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if password text box renders correctly"))),(0,i.kt)("li",{parentName:"ul"},"testInvalidUsernameEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Invalid login entry submission"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))),(0,i.kt)("li",{parentName:"ul"},"testInvalidEmailEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Invalid email entry submission"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))),(0,i.kt)("li",{parentName:"ul"},"testInvalidFullnameEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Invalid fullname entry submission"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))),(0,i.kt)("li",{parentName:"ul"},"testInvalidPasswordEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Invalid password entry submission"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))),(0,i.kt)("li",{parentName:"ul"},"testNullTextboxEntry()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: No information is passed to the textbox"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we don\u2019t allow the user to continue and related error message shows"))),(0,i.kt)("li",{parentName:"ul"},"testRegitrationButtonFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of registration form register button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if we can receive the text information from the registration form after clicking submit"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"Home.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testHomePageRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of home page"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the home page component renders"))),(0,i.kt)("li",{parentName:"ul"},"testRoutingToHomePage()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct routing to home page"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if routed to the home page"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"Sidebar.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testSidebarComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of sidebar"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the sidebar component renders correctly"))),(0,i.kt)("li",{parentName:"ul"},"testSidebarOptionsRender()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of sidebar options"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the sidebar options within the sidebar component render correctly"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"CreatorSettings.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testCreatorSettingsRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of creator settings"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the creator settings component is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testRoutingToCreatorSettingsPage()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct routing to creator settings"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if routed to creator settings "))),(0,i.kt)("li",{parentName:"ul"},"testLinkPatreonButtonRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of \u201cConnect Patreon\u201d button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the \u201cConnect Patreon\u201d button is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testLinkPatreonButtonFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality for \u201cConnect Patreon\u201d button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the \u201cConnect Patreon\u201d button successfully routes the user to the Patreon OAuth website"))),(0,i.kt)("li",{parentName:"ul"},"testUserSettingsRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of user settings"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the user settings component is rendered"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"PatronSettings.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testPatronSettingsRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of patron settings"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if the patron settings component is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testRoutingToPatronSettingsPage()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct routing to patron settings"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if routed to patron settings"))),(0,i.kt)("li",{parentName:"ul"},"testChangeUsernameTextbox()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality for change username text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if username text box information is received after clicking submit"))),(0,i.kt)("li",{parentName:"ul"},"testChangeEmailTextbox()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality for change email text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if email text box information is received after clicking submit"))),(0,i.kt)("li",{parentName:"ul"},"testChangePasswordTextbox()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality for change password text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if password text box information is received after clicking submit"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"Contact.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testContactPageRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of contact page"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if contact page is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testFeedbackTextboxRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of contact page feedback text box"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if feedback text box is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testSubmitFeedbackButtonRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of contact page submit feedback button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if submit feedback button is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testSubmitFeedbackButtonFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of contact page submit feedback button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if feedback information is received after clicking submit"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"Feed.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testFeedComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of feed component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if feed component is rendered"))),(0,i.kt)("li",{parentName:"ul"},"testFeedPopulatesWithPosts()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Feed is populated with post components"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if posts are filled into the feed component"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"SearchBar.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testSearchBarComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of search bar component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if search bar component renders"))),(0,i.kt)("li",{parentName:"ul"},"testSearchButtonRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of search bar button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if search button component renders correctly "))),(0,i.kt)("li",{parentName:"ul"},"testSearchButtonFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of search bar button"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if search bar filters posts in the feed component when text is added"))),(0,i.kt)("li",{parentName:"ul"},"testSearchFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of search bar"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if search functionality correctly finds instances of string searched"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"FilterComponent.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testFilterComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of filter component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if filter component renders"))),(0,i.kt)("li",{parentName:"ul"},"testFilterDropDownFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of filter component drop down"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if filter component drops down with more information when clicked"))),(0,i.kt)("li",{parentName:"ul"},"testFilterOptionsFunctional()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality of filter component filtering options"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if filter component filters the feed component when options are selected"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"Lists.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testListComponentRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of list component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if list component renders"))),(0,i.kt)("li",{parentName:"ul"},"testAddToListRendersOnPosts()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of add to list button on each post"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if add to list button renders correctly"))),(0,i.kt)("li",{parentName:"ul"},"testAddNewListButtonRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of \u201cAdd New List\u201d"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if \u201cAdd New List\u201d button renders"))),(0,i.kt)("li",{parentName:"ul"},"testChangeListButtonRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of \u201cChange List\u201d"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if \u201cChange List\u201d button renders"))),(0,i.kt)("li",{parentName:"ul"},"testRoutingToListPage()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct routing to lists page"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if routed to lists page"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"RecommendBar.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testReccomendBarRenders()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of recommend bar component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if recommend sidebar renders correctly"))),(0,i.kt)("li",{parentName:"ul"},"testReccomendBarPopulates()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct functionality for recommend bar component"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if recommended bar populates with content creators"))))),(0,i.kt)("li",{parentName:"ul"},(0,i.kt)("p",{parentName:"li"},"SidebarOption.js"),(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"testSidebarOptionsRender()",(0,i.kt)("ul",{parentName:"li"},(0,i.kt)("li",{parentName:"ul"},"Test: Correct rendering of sidebar options"),(0,i.kt)("li",{parentName:"ul"},"Result: Pass if sidebar options correctly render in the sidebar")))))))}c.isMDXComponent=!0}}]);