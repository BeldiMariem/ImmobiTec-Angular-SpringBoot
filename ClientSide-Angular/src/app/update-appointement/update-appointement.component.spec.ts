import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAppointementComponent } from './update-appointement.component';

describe('UpdateAppointementComponent', () => {
  let component: UpdateAppointementComponent;
  let fixture: ComponentFixture<UpdateAppointementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateAppointementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateAppointementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
