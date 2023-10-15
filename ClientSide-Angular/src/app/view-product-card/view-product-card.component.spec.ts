import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductCardComponent } from './view-product-card.component';

describe('ViewProductCardComponent', () => {
  let component: ViewProductCardComponent;
  let fixture: ComponentFixture<ViewProductCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewProductCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewProductCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
