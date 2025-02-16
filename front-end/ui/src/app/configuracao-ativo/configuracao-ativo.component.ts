import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfiguracaoAtivoService } from './configuracao-ativo.service';

interface ConfiguracaoAtivo {
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Component({
  selector: 'app-configuracao-ativo',
  templateUrl: './configuracao-ativo.component.html',
  styleUrls: ['./configuracao-ativo.component.scss'],
})
export class ConfiguracaoAtivoComponent {
  configuracaoAtivoForm: FormGroup;

  ativos: ConfiguracaoAtivo[] = [];
  filtroNome: string = '';

  displayedColumns: string[] = ['nome', 'rendimentoDia', 'rendimentoMes', 'somenteDiasUteis'];

  constructor(private fb: FormBuilder, private configuracaoAtivoService : ConfiguracaoAtivoService) {
    this.configuracaoAtivoForm = this.fb.group({
      nome: ['', Validators.required],
      rendimentoDia: [0, [Validators.required, Validators.min(0)]],
      rendimentoMes: [0, [Validators.required, Validators.min(0)]],
      somenteDiasUteis: [false],
    });
  }

  onSubmit() {
    if (this.configuracaoAtivoForm.valid) {
      const configuracaoAtivoInput = this.configuracaoAtivoForm.value;
      this.configuracaoAtivoService.register(configuracaoAtivoInput).subscribe(
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

        const configuracaoAtivoInput = this.configuracaoAtivoForm.value;
        this.configuracaoAtivoService.getAll().subscribe(
          response => {
            console.log('Usuário registrado com sucesso!', response);
            this.ativos = response;
          },
          error => {
            console.error('Erro ao registrar usuário', error);
          }
        );

    }

  ativosFiltrados() {
    return this.ativos.filter(ativo =>
      ativo.nome.toLowerCase().includes(this.filtroNome.toLowerCase())
    );
  }
}

