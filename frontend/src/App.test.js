import {render, screen, fireEvent} from '@testing-library/react';
import {Routes, Route, useLocation, BrowserRouter} from "react-router-dom";
import PatreonConnect from "./components/PatreonConnect";
import App from "./App";
import LoginPage from "./login_page/LoginPage";

describe(App, () => {

    it("Home and Login Component Imported Correctly", () => {
        const {} = render(<BrowserRouter><App /></BrowserRouter>);
    });

    // it("Login and Route to Main Page", () => {
    //     const {getByTestId} = render(<BrowserRouter><App /></BrowserRouter>);
    //     const userLogin = getByTestId("login-username");
    //     const userPass = getByTestId("login-password");
    //     const loginButton = getByTestId("login-button");
    //
    // });

});

