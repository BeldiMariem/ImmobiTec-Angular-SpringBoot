import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadImageSComponent } from './upload-image.component';

describe('UploadImageComponent', () => {
  let component: UploadImageSComponent;
  let fixture: ComponentFixture<UploadImageSComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadImageSComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UploadImageSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
