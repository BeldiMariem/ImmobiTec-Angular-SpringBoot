import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfPropertiesComponent } from './list-properties.component';

describe('ListPropertiesComponent', () => {
  let component: ListOfPropertiesComponent;
  let fixture: ComponentFixture<ListOfPropertiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListOfPropertiesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListOfPropertiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
