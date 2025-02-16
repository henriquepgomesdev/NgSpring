import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SelicInput, SelicService } from './selic.service';

interface selic {
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Component({
  selector: 'app-selic',
  templateUrl: './selic.component.html',
  styleUrls: ['./selic.component.scss'],
})
export class SelicComponent {
  selicForm: FormGroup;

  selics: SelicInput[] = [];
  filtroNome: string = '';

  displayedColumns: string[] = ['data', 'valor'];

  constructor(private fb: FormBuilder, private selicService : SelicService) {
    this.selicForm = this.fb.group({
      data: ['', Validators.required],
      valor: [0, [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit() {
    if (this.selicForm.valid) {
      const selicInput = this.selicForm.value;
      this.selicService.register(selicInput).subscribe(
        response => {
          console.log('Usuário registrado com sucesso!', response);
        },
        error => {
          console.error('Erro ao registrar usuário', error);
        }
      );
    }
  }

  onPesquisar() {

        const selicInput = this.selicForm.value;
        this.selicService.getAll().subscribe(
          response => {
            console.log('Usuário registrado com sucesso!', response);
            this.selics = response;
          },
          error => {
            console.error('Erro ao registrar usuário', error);
          }
        );

    }
}

