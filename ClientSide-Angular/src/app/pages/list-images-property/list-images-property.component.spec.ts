import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListImagesPropertyComponent } from './list-images-property.component';

describe('ListImagesPropertyComponent', () => {
  let component: ListImagesPropertyComponent;
  let fixture: ComponentFixture<ListImagesPropertyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListImagesPropertyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListImagesPropertyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
