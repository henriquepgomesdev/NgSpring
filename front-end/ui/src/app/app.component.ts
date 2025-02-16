import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ui';

  username: string = '';
  password: string = '';

  onSubmit() {
    if (this.isFormValid()) {
      console.log('Login successful!');
    }
  }

  isFormValid(): boolean {
    return this.username.trim() !== '' && this.password.trim() !== '';
  }
}
