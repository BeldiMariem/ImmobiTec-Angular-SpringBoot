import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListServiceFrontComponent } from './list-service-front.component';

describe('ListServiceFrontComponent', () => {
  let component: ListServiceFrontComponent;
  let fixture: ComponentFixture<ListServiceFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListServiceFrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListServiceFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
