import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DormsbackComponent } from './dormsback.component';

describe('DormsbackComponent', () => {
  let component: DormsbackComponent;
  let fixture: ComponentFixture<DormsbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DormsbackComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DormsbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
