import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface MovimentacaoInput {
  data: Date;
  valor: number;
  idAtivo: number;
}

export interface MovimentacaoOutput {
  data: Date;
  valor: number;
  ativo: string;
  tipoMovimentacao: string;
}

@Injectable({
  providedIn: 'root'
})
export class MovimentacaoService {
  private apiUrl = `${environment.apiUrl}/movimentacao`;

  constructor(private http: HttpClient) { }

  register(input: MovimentacaoInput): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, input);
  }

  getAll(): Observable<MovimentacaoOutput[]> {
    return this.http.get<MovimentacaoOutput[]>(`${this.apiUrl}/list`);
  }

  getAllTiposMovimentacao(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/tipos-movimentacao`);
  }
}
