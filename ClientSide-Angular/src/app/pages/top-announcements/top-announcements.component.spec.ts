import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TopAnnouncementsComponent } from './top-announcements.component';

describe('TopAnnouncementsComponent', () => {
  let component: TopAnnouncementsComponent;
  let fixture: ComponentFixture<TopAnnouncementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TopAnnouncementsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TopAnnouncementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
