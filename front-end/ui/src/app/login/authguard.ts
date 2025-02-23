import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const token = localStorage.getItem('token');

    debugger;

    // Permite acesso à página de cadastro sem autenticação
    if (state.url === '/register') {
      return true;
    }

    if (token) {
      return true; // Usuário autenticado pode acessar outras rotas
    } else {
      this.router.navigate(['/login']); // Redireciona para login se não estiver autenticado
      return false;
    }
  }
}
