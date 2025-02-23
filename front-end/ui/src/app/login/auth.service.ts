import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../environments/environment'; // Caminho correto

interface LoginResponse {
  token: string;
  user: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.apiUrl}/auth/login`; // Defina o URL da sua API
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
    // Tente recuperar o estado do login da sessão ou localStorage, por exemplo
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    this.isLoggedInSubject.next(isLoggedIn);
  }

  login(username: string, password: string): Observable<LoginResponse> {
    localStorage.setItem('isLoggedIn', 'true');
    this.isLoggedInSubject.next(true);
    const loginData = { login: username, password: password };
    return this.http.post<LoginResponse>(this.apiUrl, loginData);
  }

  // Método para deslogar o usuário
  logout(): void {
    localStorage.setItem('isLoggedIn', 'false');
    this.isLoggedInSubject.next(false);
  }

  // Método que retorna se o usuário está logado
  isLoggedIn() {
    return this.isLoggedInSubject.asObservable();
  }
}
