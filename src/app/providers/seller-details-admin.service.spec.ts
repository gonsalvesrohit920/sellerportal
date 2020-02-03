import { TestBed } from '@angular/core/testing';

import { SellerDetailsAdminService } from './seller-details-admin.service';

describe('SellerDetailsAdminService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SellerDetailsAdminService = TestBed.get(SellerDetailsAdminService);
    expect(service).toBeTruthy();
  });
});
