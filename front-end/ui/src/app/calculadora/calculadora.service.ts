import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CalculadoraInput {
  data: Date;
  idAtivo: number;
  [key: string]: any;
}

@Injectable({
  providedIn: 'root'
})
export class CalculadoraService {
  private apiUrl = 'http://localhost:8090/calculadora-rentabilidade';

  constructor(private http: HttpClient) {}

  getAll(calculadoraInput: CalculadoraInput): Observable<number> {
    // Crie os parâmetros da requisição a partir do objeto calculadoraInput
    let params = new HttpParams();
    for (const key in calculadoraInput) {
      if (calculadoraInput.hasOwnProperty(key)) {
        params = params.set(key, calculadoraInput[key]);
      }
    }

    // Retorne a requisição GET com os parâmetros
    return this.http.get<number>(this.apiUrl, { params: params });
  }
}
