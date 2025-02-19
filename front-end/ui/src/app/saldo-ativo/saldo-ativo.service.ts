import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';


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
  private apiUrl = `${environment.apiUrl}/saldo-ativo`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<SaldoAtivoOutput[]> {
    return this.http.get<SaldoAtivoOutput[]>(`${this.apiUrl}`);
  }

}
