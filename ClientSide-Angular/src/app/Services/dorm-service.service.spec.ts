import { TestBed } from '@angular/core/testing';

import { DormServiceService } from './dorm-service.service';

describe('DormServiceService', () => {
  let service: DormServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DormServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
