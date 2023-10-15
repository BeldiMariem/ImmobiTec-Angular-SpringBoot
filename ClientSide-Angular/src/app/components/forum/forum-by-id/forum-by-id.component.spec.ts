import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForumByIdComponent } from './forum-by-id.component';

describe('ForumByIdComponent', () => {
  let component: ForumByIdComponent;
  let fixture: ComponentFixture<ForumByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForumByIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForumByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
