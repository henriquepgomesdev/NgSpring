import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


export interface SaldoAtivoOutput {
  ativo: string;
  valor: number;
  rendimento: number;
  percentualRendimento?: number;
  rendimentoTotal: number;
  percentualRendimentoTotal?: number;
}

@Injectable({
  providedIn: 'root'
})
export class SaldoAtivoService {
  private apiUrl = 'http://localhost:8090/saldo-ativo';

  constructor(private http: HttpClient) { }

  getAll(): Observable<SaldoAtivoOutput[]> {
    return this.http.get<SaldoAtivoOutput[]>(`${this.apiUrl}`);
  }

}
