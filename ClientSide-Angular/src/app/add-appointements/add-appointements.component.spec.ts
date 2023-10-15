import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAppointementsComponent } from './add-appointements.component';

describe('AddAppointementsComponent', () => {
  let component: AddAppointementsComponent;
  let fixture: ComponentFixture<AddAppointementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddAppointementsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddAppointementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
