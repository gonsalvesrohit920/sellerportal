import { TestBed } from '@angular/core/testing';

import { SellerDataService } from './seller-data.service';

describe('SellerDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SellerDataService = TestBed.get(SellerDataService);
    expect(service).toBeTruthy();
  });
});
