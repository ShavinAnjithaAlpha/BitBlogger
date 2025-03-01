import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './features/auth/components/login/login.component';
import { SigninComponent } from './features/auth/components/signin/signin.component';
import { HomeComponent } from './features/home/components/home/home.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { TagPageComponent } from './features/tag-page/tag-page.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: "Home Page"
  },
  {
    path: 'login',
    component: LoginComponent,
    title: "Login Page"
  },
  {
    path: "register",
    component: SigninComponent,
    title: "Signin Page"
  },
  {
    path: "tags",
    component: TagPageComponent,
    title: "Tags"
  }
];

@NgModule({
  imports: [BrowserModule,
    RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [],
})
export class AppRoutingModule { }
