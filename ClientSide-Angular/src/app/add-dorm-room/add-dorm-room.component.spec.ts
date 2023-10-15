import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDormRoomComponent } from './add-dorm-room.component';

describe('AddDormRoomComponent', () => {
  let component: AddDormRoomComponent;
  let fixture: ComponentFixture<AddDormRoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddDormRoomComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddDormRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
