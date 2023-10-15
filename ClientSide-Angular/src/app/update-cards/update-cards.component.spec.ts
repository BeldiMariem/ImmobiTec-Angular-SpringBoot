import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCardsComponent } from './update-cards.component';

describe('UpdateCardsComponent', () => {
  let component: UpdateCardsComponent;
  let fixture: ComponentFixture<UpdateCardsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCardsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
