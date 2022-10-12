---
sidebar_position: 1
---
# Unit tests
Our team will utilize JUnit testing framework to implement unit tests for our Java code and Jest to implement React unit tests. There will be a test for each critical component to ensure proper functionality
### Back-end
- PostsRepository
	- testPutPostAndGetAllPosts()
		- Test: See if posts are put into the database
		- Result: Pass if getting all the posts in the database returns each post added
	- testGetPost()
-  Test: See if post can be retrieved from the database using the post url
		Result: Pass if the post put into the database is returned with all matching data
	- testUpdatePost()
		Test: See if a post can be updated with new information
		Result: Pass if the post in the database is updated with new information
	- testRemovePost()
		Test: See if a post can be removed from the database using the post url
		Result: Pass if the size of all posts in the database is 0 after removing the only post
- DiscordSender
	- testClose()
		Test: See if the WebhookClient closes by setting a time limit on the test
		Result: Pass if the test doesn’t throw an error after a set amount of time
	- testSetImage()
		Test: See if the WebhookEmbedBuilder holds an image
		Result: Pass if the embed has an image
	- testAddField()
		Test: See if the WebhookEmbedBuilder holds a field with a title and value
		Result: Pass if the embed has a title and value
	- testSetDescription()
		Test: See if the WebhookEmbedBuilder holds a description
		Result: Pass if the embed has a description
	- testSetTitle()
		Test: See if the WebhookEmbedBuilder holds a title
		Result: Pass if the embed has a title
	- testSetColor()
		Test: See if the WebhookEmbedBuilder holds a color value
		Result: Pass if the embed has a color
	- testSend()
		Test: See if the WebhookClient sends an embed to Discord
		Result: Pass if Discord gets sent the embed holding data
- CustomPatreonAPI
	- testFetchPosts()
		Test: See if http request sent to Patreon returns Patreon posts with given campaignId
		Result: Pass if we get post data from http request
	- testFetchFollowingCampaigns( )
		Test: See if http request sent to Patreon returns the Patreon campaigns the user follows with given access_token
		Result: Pass if we get campaign data from the http request
	- testFetchUser()
		Test: See if http request sent to Patreon returns the Patreon user
		Result: Pass if we get the user data from the http request
- PatreonInfoSvc
	- testPutPatreonInfoSuccess()
		Test: Send valid patreon info PUT request to the patreon info endpoint
		Result: Pass if patreon info is added to the database
	- testPutPatreonInfoFailure()
		Test: Send invalid patreon info PUT request to the patreon info endpoint
		Result: Pass if patreon info isn’t added to the database
	- testGetPatreonInfoSuccess()
		Test: Send valid patreon info GET request to the patreon info endpoint
		Result: Pass if information received is valid
	- testGetPatreonInfoFailure()
		Test: Send invalid patreon info GET request to the patreon info endpoint
		Result: Pass if information received isn’t valid
- WebAccountSvc
	- testLoginSuccess()
		Test: Send valid login credentials to the login endpoint
		Result: Assert receiving an HTTP response code of 200 (OK)  and a login token
	- testLoginFailure()
		Test: Send invalid login credentials to the login endpoint
		Result: Assert receiving an HTTP response code of 401 (Unauthorized)
	- testRegisterSuccess()
		Test: Send registration credentials with legal characters in the username
		Result: Assert receiving an HTTP response code of 200 (OK)
	- testRegisterFailure()
		Test: Send registration credentials with illegal characters in the username
		Result: Assert receiving an HTTP response code of 422 (Unprocessable Entity)
	- testIntegrationSuccess()
		Test: Send integration details with a WebAccount ID that exists 
		Result: Assert receiving an HTTP response code of 200 (OK)
	- testIntegrationFailure()
		Test: Send integration details with a WebAccount ID that does not exist
        Result: Assert receiving an HTTP response code of 409 (Conflict)

### Front-end

- index.js
    - testWebsiteRenders()
        - Test: Correct rendering of the website
        - Result: Pass if website correctly renders without errors

- App.js
    - testTokenIsCreated()
        - Test: Ensure token is created after successful login
        - Result: Pass if token is not empty
    - testRoutesAreValid()
        - Test: Correct routing between pages
        - Result: Pass if the website will route to all pages
    - testImportHomeIsNotNull()
        - Test: Ensure home component is correctly imported
        - Result: Pass if home component is found
    - testImportLoginIsNotNull()
        - Test: Ensure login component is correctly imported
        - Result: Pass if login component is found

- Login.js
    - testLoginPageRenders()
        - Test: Correct rendering of login page
        - Result: Pass if login page renders
    - testRoutingToLoginPage()
        - Test: Correct routing to login page
        - Result: Pass if routed to the login page

- FormContainers.js
    - testFormContainerRenders()
        - Test: Correct rendering of FormContainers components
        - Result: Pass if FormContainers components render
    - testTabPanelRenders()
        - Test: Correct rendering of the TabPanel component
        - Result: Pass if TabPanel component renders
    - testLoginWindowRenders()
        - Test: Correct rendering of the login window
        - Result: Pass if login window component renders
    - testRegistrationWindowRenders()
        - Test: Correct rendering of the registration window
        - Result: Pass if registration window component renders

- LoginForm.js
    - testTitleComponentRenders()
        - Test: Correct rendering of login form title component
        - Result: Pass if login form title component renders
    - testUsernameTextboxRenders()
        - Test: Correct rendering of login form username text box
        - Result: Pass if Username text box component renders
    - testPasswordTextboxRenders()
        - Test: Correct rendering of login form password text box
        - Result: Pass if login form password text box renders
    - testLoginButtonRenders()
        - Test: Correct rendering of login form login button
        - Result: Pass if login form login button renders
    - testLoginButtonFunctional()
        - Test: Correct functionality of login form login button
        - Result: Pass if we can receive the text from the login form login button after clicking submit
    - testLogoRenders()
        - Test: Correct rendering of login form logo
        - Result: Pass if login form logo renders
    - testInvalidLoginEntry()
        - Test: Invalid login entry submission
        - Result: Pass if we don’t allow the user to continue and related error message shows
    - testNullLoginEntry()
        - Test: No information login entry submission
        - Result: Pass if we don’t allow the user to continue and related error message shows

- RegistrationForm.js
    - testTitleComponentRenders()
        - Test: Correct rendering of registration form title component
        - Result: Pass if registration form title component renders
    - testEmailTextboxRenders()
        - Test: Correct rendering of registration form email text box
        - Result: Pass if email text box renders correctly
    - testUsernameTextboxRenders()
        - Test: Correct rendering of registration form username text box
        - Result: Pass if username text box renders correctly
    - testPasswordTextboxRenders()
        - Test: Correct rendering of registration form password text box
        - Result: Pass if password text box renders correctly
    - testInvalidUsernameEntry()
        - Test: Invalid login entry submission
        - Result: Pass if we don’t allow the user to continue and related error message shows
    - testInvalidEmailEntry()
        - Test: Invalid email entry submission
        - Result: Pass if we don’t allow the user to continue and related error message shows
    - testInvalidFullnameEntry()
        - Test: Invalid fullname entry submission
        - Result: Pass if we don’t allow the user to continue and related error message shows
    - testInvalidPasswordEntry()
        - Test: Invalid password entry submission
        - Result: Pass if we don’t allow the user to continue and related error message shows
    - testNullTextboxEntry()
        - Test: No information is passed to the textbox
        - Result: Pass if we don’t allow the user to continue and related error message shows
    - testRegitrationButtonFunctional()
        - Test: Correct functionality of registration form register button
        - Result: Pass if we can receive the text information from the registration form after clicking submit

- Home.js
    - testHomePageRenders()
        - Test: Correct rendering of home page
        - Result: Pass if the home page component renders
    - testRoutingToHomePage()
        - Test: Correct routing to home page
        - Result: Pass if routed to the home page

- Sidebar.js
    - testSidebarComponentRenders()
        - Test: Correct rendering of sidebar
        - Result: Pass if the sidebar component renders correctly
    - testSidebarOptionsRender()
        - Test: Correct rendering of sidebar options
        - Result: Pass if the sidebar options within the sidebar component render correctly

- CreatorSettings.js
    - testCreatorSettingsRenders()
        - Test: Correct rendering of creator settings
        - Result: Pass if the creator settings component is rendered
    - testRoutingToCreatorSettingsPage()
        - Test: Correct routing to creator settings
        - Result: Pass if routed to creator settings 
    - testLinkPatreonButtonRenders()
        - Test: Correct rendering of “Connect Patreon” button
        - Result: Pass if the “Connect Patreon” button is rendered
    - testLinkPatreonButtonFunctional()
        - Test: Correct functionality for “Connect Patreon” button
        - Result: Pass if the “Connect Patreon” button successfully routes the user to the Patreon OAuth website
    - testUserSettingsRenders()
        - Test: Correct rendering of user settings
        - Result: Pass if the user settings component is rendered

- PatronSettings.js
    - testPatronSettingsRenders()
        - Test: Correct rendering of patron settings
        - Result: Pass if the patron settings component is rendered
    - testRoutingToPatronSettingsPage()
        - Test: Correct routing to patron settings
        - Result: Pass if routed to patron settings
    - testChangeUsernameTextbox()
        - Test: Correct functionality for change username text box
        - Result: Pass if username text box information is received after clicking submit
    - testChangeEmailTextbox()
        - Test: Correct functionality for change email text box
        - Result: Pass if email text box information is received after clicking submit
    - testChangePasswordTextbox()
        - Test: Correct functionality for change password text box
        - Result: Pass if password text box information is received after clicking submit

- Contact.js
    - testContactPageRenders()
        - Test: Correct rendering of contact page
        - Result: Pass if contact page is rendered
    - testFeedbackTextboxRenders()
        - Test: Correct rendering of contact page feedback text box
        - Result: Pass if feedback text box is rendered
    - testSubmitFeedbackButtonRenders()
        - Test: Correct rendering of contact page submit feedback button
        - Result: Pass if submit feedback button is rendered
    - testSubmitFeedbackButtonFunctional()
        - Test: Correct functionality of contact page submit feedback button
        - Result: Pass if feedback information is received after clicking submit

- Feed.js
    - testFeedComponentRenders()
        - Test: Correct rendering of feed component
        - Result: Pass if feed component is rendered
    - testFeedPopulatesWithPosts()
        - Test: Feed is populated with post components
        - Result: Pass if posts are filled into the feed component

- SearchBar.js
    - testSearchBarComponentRenders()
        - Test: Correct rendering of search bar component
        - Result: Pass if search bar component renders
    - testSearchButtonRenders()
        - Test: Correct rendering of search bar button
        - Result: Pass if search button component renders correctly 
    - testSearchButtonFunctional()
        - Test: Correct functionality of search bar button
        - Result: Pass if search bar filters posts in the feed component when text is added
    - testSearchFunctional()
        - Test: Correct functionality of search bar
        - Result: Pass if search functionality correctly finds instances of string searched

- FilterComponent.js
    - testFilterComponentRenders()
        - Test: Correct rendering of filter component
        - Result: Pass if filter component renders
    - testFilterDropDownFunctional()
        - Test: Correct functionality of filter component drop down
        - Result: Pass if filter component drops down with more information when clicked
    - testFilterOptionsFunctional()
        - Test: Correct functionality of filter component filtering options
        - Result: Pass if filter component filters the feed component when options are selected

- Lists.js
    - testListComponentRenders()
        - Test: Correct rendering of list component
        - Result: Pass if list component renders
    - testAddToListRendersOnPosts()
        - Test: Correct rendering of add to list button on each post
        - Result: Pass if add to list button renders correctly
    - testAddNewListButtonRenders()
        - Test: Correct rendering of “Add New List”
        - Result: Pass if “Add New List” button renders
    - testChangeListButtonRenders()
        - Test: Correct rendering of “Change List”
        - Result: Pass if “Change List” button renders
    - testRoutingToListPage()
        - Test: Correct routing to lists page
        - Result: Pass if routed to lists page

- RecommendBar.js
    - testReccomendBarRenders()
        - Test: Correct rendering of recommend bar component
        - Result: Pass if recommend sidebar renders correctly
    - testReccomendBarPopulates()
        - Test: Correct functionality for recommend bar component
        - Result: Pass if recommended bar populates with content creators

- SidebarOption.js
    - testSidebarOptionsRender()
        - Test: Correct rendering of sidebar options
        - Result: Pass if sidebar options correctly render in the sidebar

