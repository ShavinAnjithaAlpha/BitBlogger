import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/auth/components/login/login.component';
import { SigninComponent } from './features/auth/components/signin/signin.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
        title: "Login Page"
    },
    {
        path: "register",
        component: SigninComponent,
        title: "Signin Page"
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
