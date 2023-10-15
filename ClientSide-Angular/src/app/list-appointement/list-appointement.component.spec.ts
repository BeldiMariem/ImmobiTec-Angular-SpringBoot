import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAppointementComponent } from './list-appointement.component';

describe('ListAppointementComponent', () => {
  let component: ListAppointementComponent;
  let fixture: ComponentFixture<ListAppointementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListAppointementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListAppointementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
