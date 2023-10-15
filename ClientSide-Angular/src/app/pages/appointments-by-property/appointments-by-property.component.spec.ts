import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentsByPropertyComponent } from './appointments-by-property.component';

describe('AppointmentsByPropertyComponent', () => {
  let component: AppointmentsByPropertyComponent;
  let fixture: ComponentFixture<AppointmentsByPropertyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentsByPropertyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentsByPropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
