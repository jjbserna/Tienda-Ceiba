import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrearPrendaComponent } from './crear-prenda.component';

describe('CrearPrendaComponent', () => {
  let component: CrearPrendaComponent;
  let fixture: ComponentFixture<CrearPrendaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrearPrendaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrearPrendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
