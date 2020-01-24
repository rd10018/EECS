import { TestBed } from '@angular/core/testing';

import { BackendDetailsService } from './backend-details.service';

describe('BackendDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BackendDetailsService = TestBed.get(BackendDetailsService);
    expect(service).toBeTruthy();
  });
});
