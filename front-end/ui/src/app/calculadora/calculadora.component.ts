import { Component } from '@angular/core';

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
export class CalculadoraComponent {
  cdi: number = 0; // CDI anual (%)
  selic: number = 0; // SELIC anual (%)
  rentabilidadeCDI: number = 0; // SELIC anual (%)
  valorInvestido: number = 0; // Valor do investimento (R$)
  rentabilidadeDiaria: number = 0; // Rentabilidade diária (R$)

  valorFinal: number = 0;

  calcularRentabilidade(): void {
     const cdiDiario = this.cdi / 100 / 252;

     const ajusteSelic = this.selic / 100 / 252;

      // Rentabilidade aplicada sobre o CDI diário
      this.rentabilidadeDiaria = this.valorInvestido * (cdiDiario * (this.rentabilidadeCDI / 100) + ajusteSelic * 0.1);

      this.valorFinal = this.rentabilidadeDiaria + this.valorInvestido;

  }

}


