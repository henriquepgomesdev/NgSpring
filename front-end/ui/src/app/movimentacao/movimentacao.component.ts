import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MovimentacaoOutput, MovimentacaoService } from './movimentacao.service';
import { ConfiguracaoAtivoInput, ConfiguracaoAtivoService } from '../configuracao-ativo/configuracao-ativo.service';

interface cdi {
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Component({
  selector: 'app-movimentacao',
  templateUrl: './movimentacao.component.html',
  styleUrls: ['./movimentacao.component.scss'],
})
export class MovimentacaoComponent implements OnInit {
  movimentacaoForm: FormGroup;

  movimentacoes: MovimentacaoOutput[] = [];
  configuracoesAtivo: ConfiguracaoAtivoInput[] = [];
  tiposMovimentacao: any[] = [];
  filtroNome: string = '';

  displayedColumns: string[] = ['data', 'valor', 'ativo', 'tipoMovimentacao'];

  ngOnInit() {
    this.carregarConfiguracoesAtivo();
    this.carregarTiposMovimentacao();
  }

  constructor(private fb: FormBuilder, private movimentacaoService: MovimentacaoService, private configuracaoAtivoService: ConfiguracaoAtivoService) {
    this.movimentacaoForm = this.fb.group({
      data: ['', Validators.required],
      valor: [0, [Validators.required, Validators.min(0)]],
      idAtivo: ['', Validators.required],
      tipoMovimentacao: ['', Validators.required]
    });
  }

  carregarConfiguracoesAtivo() {
    this.configuracaoAtivoService.getAll().subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
        this.configuracoesAtivo = response;
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );
  }

  carregarTiposMovimentacao() {
    this.movimentacaoService.getAllTiposMovimentacao().subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
        this.tiposMovimentacao = response;
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );
  }

  onSubmit() {
    if (this.movimentacaoForm.valid) {
      const cdiInput = this.movimentacaoForm.value;
      this.movimentacaoService.register(cdiInput).subscribe(
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

    this.movimentacaoService.getAll().subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
        debugger;
        this.movimentacoes = response;
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );

  }
}

