import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfiguracaoAtivoInput, ConfiguracaoAtivoService } from '../configuracao-ativo/configuracao-ativo.service';
import { CalculadoraService } from './calculadora.service';

interface selic {
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Component({
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.scss'],
})
export class CalculadoraComponent implements OnInit {

  configuracoesAtivo: ConfiguracaoAtivoInput[] = [];

  calculadoraForm: FormGroup;
  cdi: number = 0; // CDI anual (%)
  selic: number = 0; // SELIC anual (%)
  rentabilidadeCDI: number = 0; // SELIC anual (%)
  valorInvestido: number = 0; // Valor do investimento (R$)
  rentabilidadeDiaria: number = 0; // Rentabilidade diária (R$)

  valorFinal: number = 0;

  ngOnInit() {

  }

  constructor(private fb: FormBuilder, private configuracaoAtivoService: ConfiguracaoAtivoService, private calculadoraService: CalculadoraService) {
    this.calculadoraForm = this.fb.group({
      data: ['', Validators.required]
    });
  }

  onPesquisar() {
    const calculadoraInput = this.calculadoraForm.value;
    this.calculadoraService.getAll(calculadoraInput).subscribe(
      response => {
        console.log('Usuário registrado com sucesso!', response);
        this.valorFinal = response;
      },
      error => {
        console.error('Erro ao registrar usuário', error);
      }
    );
  }

}


