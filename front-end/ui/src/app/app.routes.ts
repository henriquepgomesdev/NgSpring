import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './login/register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { SettingsComponent } from './settings/settings.component';
import { AppLayoutComponent } from './app-layout/app-layout.component';
import { AuthGuard } from './login/authguard';
import { ConfiguracaoAtivoComponent } from './configuracao-ativo/configuracao-ativo.component';
import { CdiComponent } from './cdi/cdi.component';
import { SelicComponent } from './selic/selic.component';
import { CalculadoraComponent } from './calculadora/calculadora.component';


export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '', // Aqui é onde o layout protegido será exibido
    component: AppLayoutComponent,
    canActivate: [AuthGuard], // Aplica o AuthGuard na rota
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'settings', component: SettingsComponent },
      { path: 'configuracao-ativo', component: ConfiguracaoAtivoComponent },
      { path: 'cdi', component: CdiComponent },
      { path: 'selic', component: SelicComponent },
      { path: 'calculadora', component: CalculadoraComponent },
    ]
  },
  { path: '**', redirectTo: '/login' } // Redireciona para login para rotas não encontradas
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
