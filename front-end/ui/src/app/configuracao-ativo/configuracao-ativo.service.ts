import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface ConfiguracaoAtivoInput {
  id: number;
  nome: string;
  rendimentoDia: number;
  rendimentoMes: number;
  somenteDiasUteis: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class ConfiguracaoAtivoService {
  private apiUrl = `${environment.apiUrl}/configuracao-ativo`;

  constructor(private http: HttpClient) { }

  register(input: ConfiguracaoAtivoInput): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, input);
  }

  getAll(): Observable<ConfiguracaoAtivoInput[]> {
    return this.http.get<ConfiguracaoAtivoInput[]>(`${this.apiUrl}/list`);
  }


  
}
