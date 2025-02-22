import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';


export interface SaldoAtivoOutput {
  ativo: string;
  valorBruto: number;
  rendimentoBruto: number;
  percentualRendimentoBruto?: number;
  valorLiquido: number;
  rendimentoLiquido: number;
  percentualRendimentoLiquido?: number;
  rendimentoTotal: number;
  percentualRendimentoTotal?: number;
}

@Injectable({
  providedIn: 'root'
})
export class SaldoAtivoService {
  private apiUrl = `${environment.apiUrl}/saldo-ativo`;

  constructor(private http: HttpClient) { }

  getAll(dataInicial: string, dataFinal: string): Observable<SaldoAtivoOutput[]> {
    return this.http.get<SaldoAtivoOutput[]>(`${this.apiUrl}?dataInicial=${dataInicial}&dataFinal=${dataFinal}`);
  }
}
