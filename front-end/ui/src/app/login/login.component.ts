import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  onSubmit() {
    if (this.isFormValid()) {
      this.authService.login(this.username, this.password).subscribe(
        (response) => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('user', response.user);
          this.router.navigate(['/index']);
          console.log('Login successful!', response);
        },
        (error) => {
          this.snackBar.open('Usuário ou senha inválidos!', 'Fechar', {
            duration: 3000,
            verticalPosition: 'top',
            horizontalPosition: 'center',
            panelClass: ['error-snackbar']
          });
          console.error('Login failed!', error);
        }
      );
    }
  }

  isFormValid(): boolean {
    return this.username.trim() !== '' && this.password.trim() !== '';
  }
}
