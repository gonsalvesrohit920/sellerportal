import { TestBed } from '@angular/core/testing';

import { SellerServiceService } from './seller-service.service';

describe('SellerServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SellerServiceService = TestBed.get(SellerServiceService);
    expect(service).toBeTruthy();
  });
});
