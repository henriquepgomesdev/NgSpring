import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss']
})
export class AppLayoutComponent implements OnInit {
  currentPageTitle: string = 'Seja bem vindo!';

  sistemaSelecionado: string = 'financeiro';

  username: string = ''

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Observar a navegação e atualizar o título da página
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.updatePageTitle();
    });

    // Recuperar o nome do usuário do localStorage (ou de outro lugar)
    const user = localStorage.getItem('user'); // Exemplo de como você pode pegar o nome do usuário
    debugger;
    if (user) {
      this.username = user; // Adaptar conforme o seu formato de armazenamento
    }
  }

  private updatePageTitle(): void {
    const route = this.router.url;
    console.log(route);
    
    // Atualize para incluir a rota "/index"
    if (route.includes('/configuracao-ativo')) {
      this.currentPageTitle = 'Configuração ativo';
    } else if (route.includes('/dashboard')) {
      this.currentPageTitle = 'Dashboard';
    } else if (route.includes('/selic')) {
      this.currentPageTitle = 'Selic';
    } else if (route.includes('/cdi')) {
      this.currentPageTitle = 'CDI';
    }  else if (route.includes('/calculadora')) {
      this.currentPageTitle = 'Calculadora';
    } else if (route.includes('/movimentacao')) {
      this.currentPageTitle = 'Movimentação';
    }  else if (route.includes('/saldo-ativo')) {
      this.currentPageTitle = 'Saldo por ativo';
    } else if (route.includes('/index')) {  // Adicionado para a rota '/index'
      this.currentPageTitle = 'Página Inicial';  // Você pode customizar o título conforme preferir
    } else {
      this.currentPageTitle = 'Seja bem vindo!';  // Título padrão caso não corresponda a nenhuma rota específica
    }
  }


  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }
}
