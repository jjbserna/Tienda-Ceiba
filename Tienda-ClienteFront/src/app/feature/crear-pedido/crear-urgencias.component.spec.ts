import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearUrgenciasComponent } from './crear-urgencias.component';

describe('CreateUrgenciasComponent', () => {
  let component: CrearUrgenciasComponent;
  let fixture: ComponentFixture<CrearUrgenciasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearUrgenciasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearUrgenciasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
