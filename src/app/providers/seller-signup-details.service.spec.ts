import { TestBed } from '@angular/core/testing';

import { SellerSignupDetailsService } from './seller-signup-details.service';

describe('SellerSignupDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SellerSignupDetailsService = TestBed.get(SellerSignupDetailsService);
    expect(service).toBeTruthy();
  });
});
