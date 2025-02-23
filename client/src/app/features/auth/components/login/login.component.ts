import { Component, inject, Inject, Input } from '@angular/core';
import {
  FormsModule,
  FormControl,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserModule } from '@angular/platform-browser';
import { MatSnackBar } from '@angular/material/snack-bar';
import Credentials from '../../models/credentials';
import { AuthService } from '../../services/auth.service';
import { RouterLink, RouterOutlet } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatInputModule, MatButtonModule, MatFormFieldModule, ReactiveFormsModule, RouterOutlet, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  @Input() isSignin: boolean = false;
  authService: AuthService = inject(AuthService);

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  passwordFormControl = new FormControl('', [Validators.required]);

  constructor(private _snackBar: MatSnackBar) { }

  shorErrorMessage(message: string): void {
    this._snackBar.open(message, 'Close', { duration: 5000 });
  }

  onSubmit(): void {
    const credentials: Credentials = {
      email: this.emailFormControl.value,
      password: this.passwordFormControl.value
    }

    this.isSignin = true;
    this.authService.login(credentials).subscribe(
      (response: any) => {
        console.log(response);
        this.isSignin = false;

      },
      (error: any) => {
        console.log(error);
        this.isSignin = false;
        this.shorErrorMessage('Invalid credentials');
        // clear the email and password fields
        this.emailFormControl.setValue('');
        this.passwordFormControl.setValue('');

      }
    );

  }
}
