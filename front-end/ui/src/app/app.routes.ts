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
import { MovimentacaoComponent } from './movimentacao/movimentacao.component';
import { SaldoAtivoComponent } from './saldo-ativo/saldo-ativo.component';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';


export const routes: Routes = [
  { path: 'login', component: LoginComponent }, // Rota do login
  { path: 'register', component: RegisterComponent },
  {
    path: '',
    canActivate: [AuthGuard], // Protege as rotas abaixo
    component: AppLayoutComponent,
    children: [
      { path: '', component: IndexComponent },  // Rota principal
      { path: 'configuracao-ativo', component: ConfiguracaoAtivoComponent },
      //{ path: 'cdi', component: CdiComponent },
      //{ path: 'selic', component: SelicComponent },
      { path: 'movimentacao', component: MovimentacaoComponent },
      { path: 'calculadora', component: CalculadoraComponent },
      { path: 'saldo-ativo', component: SaldoAtivoComponent },
      { path: '**', redirectTo: '' } // Redireciona para a rota principal se não encontrar
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
