import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDormRoomComponent } from './update-dorm-room.component';

describe('UpdateDormRoomComponent', () => {
  let component: UpdateDormRoomComponent;
  let fixture: ComponentFixture<UpdateDormRoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateDormRoomComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateDormRoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
