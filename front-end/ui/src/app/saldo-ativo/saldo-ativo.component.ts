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

  dataInicial: string = '';
  dataFinal: string = '';
  saldos: SaldoAtivoOutput[] = [];

  displayedColumns: string[] = ['data', 'ativo', 'valorBruto', 'rendimentoBruto', 'percentualRendimentoBruto', 'valorLiquido', 'rendimentoLiquido', 'percentualRendimentoLiquido'];

  ngOnInit() {

  }

  constructor(private fb: FormBuilder, private saldoAtivoService: SaldoAtivoService) {

  }

  onPesquisar() {

    this.saldoAtivoService.getAll(this.dataInicial, this.dataFinal).subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
        this.saldos = response.map(saldo => ({
          ...saldo,
          percentualRendimento: saldo.valorBruto ? (saldo.rendimentoBruto / saldo.valorBruto) * 100 : 0
        }));
        this.saldos = response.map(saldo => ({
          ...saldo,
          percentualRendimentoTotal: saldo.valorBruto ? (saldo.rendimentoTotal / saldo.valorBruto) * 100 : 0
        }));
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );

  }

  getTotalSaldo(): number {
    return this.saldos?.reduce((total, item) => total + (item.valorBruto || 0), 0) || 0;
  } 
  
  getTotalRendimentoSaldo(): number {
    return this.saldos?.reduce((total, item) => total + (item.rendimentoLiquido || 0), 0) || 0;
  }  

}

