import { NgModule } from "@angular/core";
import { LoginComponent } from "./components/login/login.component";
import { RegisterComponent } from "./components/register/register.component";
import { CommonModule } from "@angular/common";
import { AuthRoutingModule } from "./auth-routing.module";


@NgModule({
    imports: [
        LoginComponent,
        RegisterComponent,
        CommonModule,
        AuthRoutingModule
    ]
})
export class AuthModule { }