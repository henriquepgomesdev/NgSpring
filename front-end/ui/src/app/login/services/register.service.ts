import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiUrl = 'http://localhost:8090/auth/register'; // URL da sua API de registro

  constructor(private http: HttpClient) { }

  register(registerData: any): Observable<any> {
    return this.http.post(this.apiUrl, registerData);
  }
}
