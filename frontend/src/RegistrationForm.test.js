import {render, screen, fireEvent} from '@testing-library/react';
import {Routes, Route, useLocation, BrowserRouter} from "react-router-dom";
import PatreonConnect from "./components/PatreonConnect";
import LoginForm from "./login_page/FormContainers";
import useToken from "./services/useToken";
import React, {useState} from "react";

describe(LoginForm, () => {

    it("Login Page Form Container Renders Correctly", () => {
        const {} = render(<BrowserRouter><LoginForm setToken={() => {console.log("hello")}}/></BrowserRouter>);
    });
});

