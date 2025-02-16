import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CdiInput, CdiService } from './cdi.service';

interface cdi {
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Component({
  selector: 'app-cdi',
  templateUrl: './cdi.component.html',
  styleUrls: ['./cdi.component.scss'],
})
export class CdiComponent {
  cdiForm: FormGroup;

  cdis: CdiInput[] = [];
  filtroNome: string = '';

  displayedColumns: string[] = ['data', 'valor'];

  constructor(private fb: FormBuilder, private cdiService : CdiService) {
    this.cdiForm = this.fb.group({
      data: ['', Validators.required],
      valor: [0, [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit() {
    if (this.cdiForm.valid) {
      const cdiInput = this.cdiForm.value;
      this.cdiService.register(cdiInput).subscribe(
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

        const cdiInput = this.cdiForm.value;
        this.cdiService.getAll().subscribe(
          response => {
            console.log('Usuário registrado com sucesso!', response);
            this.cdis = response;
          },
          error => {
            console.error('Erro ao registrar usuário', error);
          }
        );

    }
}

