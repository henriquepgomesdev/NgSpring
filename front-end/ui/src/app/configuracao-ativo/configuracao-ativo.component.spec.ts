import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiguracaoAtivoComponent } from './configuracao-ativo.component';

describe('ConfiguracaoAtivoComponent', () => {
  let component: ConfiguracaoAtivoComponent;
  let fixture: ComponentFixture<ConfiguracaoAtivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfiguracaoAtivoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConfiguracaoAtivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
