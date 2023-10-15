import { TestBed } from '@angular/core/testing';

import { PostVoteService } from './post-vote.service';

describe('PostVoteService', () => {
  let service: PostVoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PostVoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
