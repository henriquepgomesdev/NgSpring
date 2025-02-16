import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface SelicInput {
  data: Date;
  valor: number;
}

@Injectable({
  providedIn: 'root'
})
export class SelicService {
  private apiUrl = 'http://localhost:8090/selic';

  constructor(private http: HttpClient) {}

  register(input: SelicInput): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, input);
  }

  getAll(): Observable<SelicInput[]> {
      return this.http.get<SelicInput[]>(`${this.apiUrl}/list`);
    }
}
