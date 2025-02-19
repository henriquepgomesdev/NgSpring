import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss']
})
export class AppLayoutComponent implements OnInit {
  currentPageTitle: string = 'Yasmin eu te amo <3';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Observar a navegação e atualizar o título da página
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.updatePageTitle();
    });
  }

  private updatePageTitle(): void {
    const route = this.router.url;
    console.log(route);
    this.currentPageTitle = 'Yasmin eu te amo <3';
//     if (route.includes('/configuracao-ativo')) {
//       this.currentPageTitle = 'Configuração ativo';
//     } else if (route.includes('/dashboard')) {
//       this.currentPageTitle = 'Dashboard';
//     } else if (route.includes('/selic')) {
//       this.currentPageTitle = 'Selic';
//     } else if (route.includes('/cdi')) {
//       this.currentPageTitle = 'CDI';
//     }  else if (route.includes('/calculadora')) {
//       this.currentPageTitle = 'Calculadora';
//     } else if (route.includes('/movimentacao')) {
//       this.currentPageTitle = 'Movimentacao';
//     }  else if (route.includes('/saldo-ativo')) {
//       this.currentPageTitle = 'Saldo por ativo';
//     }
    // Adicione mais condições conforme necessário para outras páginas
  }
}
