import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit() {
    if (this.isFormValid()) {
      this.authService.login(this.username, this.password).subscribe(
        (response) => {
          debugger;
          this.router.navigate(['/configuracao-ativo'])
          console.log('Login successful!', response);
        },
        (error) => {
          console.error('Login failed!', error);
        }
      );
    }
  }

  isFormValid(): boolean {
    return this.username.trim() !== '' && this.password.trim() !== '';
  }
}
