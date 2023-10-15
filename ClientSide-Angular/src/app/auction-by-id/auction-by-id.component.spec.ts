import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuctionByIdComponent } from './auction-by-id.component';

describe('AuctionByIdComponent', () => {
  let component: AuctionByIdComponent;
  let fixture: ComponentFixture<AuctionByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuctionByIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuctionByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
