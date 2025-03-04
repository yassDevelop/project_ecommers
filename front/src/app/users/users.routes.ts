import { Routes } from "@angular/router";
import { RegisterComponent } from "./featues/register/register/register.component";
import { LoginComponent } from "./featues/login/login/login.component";

export const USERS_ROUTES: Routes = [
    {
        path: "login",
        component: LoginComponent,
    },
    {
        path: "register",
        component: RegisterComponent,
    }
];