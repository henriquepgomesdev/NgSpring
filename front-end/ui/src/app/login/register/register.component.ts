import { Component } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

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

  constructor(
    private registerService: RegisterService,
    private snackBar: MatSnackBar,  
    private router: Router           
  ) {}

  onSubmit() {
    this.registerService.register(this.registerData).subscribe(
      response => {
        this.snackBar.open('Usuário registrado com sucesso!', 'Fechar', {
          duration: 5000,  // Mensagem fica visível por 3 segundos
          verticalPosition: 'top',
          horizontalPosition: 'center'
        });

        // Redireciona imediatamente para a tela de login
        this.router.navigate(['/login']);
      },
      error => {
        this.snackBar.open('Erro ao registrar usuário', 'Fechar', {
          duration: 5000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        });
        console.error('Erro ao registrar usuário', error);
      }
    );
  }
}
