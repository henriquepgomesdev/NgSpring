import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface CdiInput {
  data: Date;
  valor: number;
}

@Injectable({
  providedIn: 'root'
})
export class CdiService {
  private apiUrl = 'http://localhost:8090/cdi';

  constructor(private http: HttpClient) {}

  register(input: CdiInput): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, input);
  }

  getAll(): Observable<CdiInput[]> {
      return this.http.get<CdiInput[]>(`${this.apiUrl}/list`);
    }
}
