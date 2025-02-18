import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SaldoAtivoOutput, SaldoAtivoService } from './saldo-ativo.service';
import { ConfiguracaoAtivoInput, ConfiguracaoAtivoService } from '../configuracao-ativo/configuracao-ativo.service';

interface cdi {
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Component({
  selector: 'app-saldo-ativo',
  templateUrl: './saldo-ativo.component.html',
  styleUrls: ['./saldo-ativo.component.scss'],
})
export class SaldoAtivoComponent implements OnInit {

  saldos: SaldoAtivoOutput[] = [];

  displayedColumns: string[] = ['data', 'ativo', 'valor', 'rendimento', 'percentualRendimento', 'rendimentoTotal', 'percentualRendimentoTotal'];

  ngOnInit() {

  }

  constructor(private fb: FormBuilder, private saldoAtivoService: SaldoAtivoService) {

  }

  onPesquisar() {

    this.saldoAtivoService.getAll().subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
        this.saldos = response.map(saldo => ({
          ...saldo,
          percentualRendimento: saldo.valor ? (saldo.rendimento / saldo.valor) * 100 : 0
        }));
        this.saldos = response.map(saldo => ({
          ...saldo,
          percentualRendimentoTotal: saldo.valor ? (saldo.rendimentoTotal / saldo.valor) * 100 : 0
        }));
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );

  }

  getTotalSaldo(): number {
    return this.saldos?.reduce((total, item) => total + (item.valor || 0), 0) || 0;
  }  

}

