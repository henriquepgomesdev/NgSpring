import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RegisterService } from '../services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerData = {
    login: '',
    password: ''
  };

  constructor(private registerService: RegisterService) { }

  onSubmit() {
    this.registerService.register(this.registerData).subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );
  }
}
