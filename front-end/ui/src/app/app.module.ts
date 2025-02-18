import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './login/register/register.component';
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppLayoutComponent } from './app-layout/app-layout.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ConfiguracaoAtivoComponent } from './configuracao-ativo/configuracao-ativo.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatCardModule } from '@angular/material/card';
import { SelicComponent } from './selic/selic.component';
import { CdiComponent } from './cdi/cdi.component';
import { CalculadoraComponent } from './calculadora/calculadora.component';
import { MovimentacaoComponent } from './movimentacao/movimentacao.component';
import { MatSelectModule } from '@angular/material/select';
import { SaldoAtivoComponent } from './saldo-ativo/saldo-ativo.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    AppLayoutComponent,
    ConfiguracaoAtivoComponent,
    SelicComponent,
    CdiComponent,
    CalculadoraComponent,
    MovimentacaoComponent,
    SaldoAtivoComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatSidenavModule,  // Importa o módulo do mat-sidenav
    MatListModule,     // Importa o módulo do mat-nav-list
    MatButtonModule,
    MatSelectModule,
    MatTabsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatButtonModule,
    MatCardModule,
    RouterModule.forRoot(routes)
  ],
  bootstrap: [AppComponent],
  providers: [
    provideAnimationsAsync()
  ]
})
export class AppModule { }
