import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDormRoomsComponent } from './list-dorm-rooms.component';

describe('ListDormRoomsComponent', () => {
  let component: ListDormRoomsComponent;
  let fixture: ComponentFixture<ListDormRoomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListDormRoomsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListDormRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
