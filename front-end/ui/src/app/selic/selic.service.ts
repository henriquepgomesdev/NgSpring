import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface SelicInput {
  data: Date;
  valor: number;
}

@Injectable({
  providedIn: 'root'
})
export class SelicService {
  private apiUrl = `${environment.apiUrl}/selic`;

  constructor(private http: HttpClient) {}

  register(input: SelicInput): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, input);
  }

  getAll(): Observable<SelicInput[]> {
      return this.http.get<SelicInput[]>(`${this.apiUrl}/list`);
    }
}
