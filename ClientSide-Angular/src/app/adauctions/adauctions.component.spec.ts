import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ADauctionsComponent } from './adauctions.component';

describe('ADauctionsComponent', () => {
  let component: ADauctionsComponent;
  let fixture: ComponentFixture<ADauctionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ADauctionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ADauctionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
