import { Component } from '@angular/core';
import {
  FormsModule,
  FormControl,
  NgForm,
  FormGroupDirective,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserModule } from '@angular/platform-browser';
// import { AppRoutingModule } from '../../../../app.routes';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatInputModule, MatButtonModule, MatFormFieldModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

}
