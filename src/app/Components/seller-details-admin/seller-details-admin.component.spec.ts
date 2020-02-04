import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerDetailsAdminComponent } from './seller-details-admin.component';

describe('SellerDetailsAdminComponent', () => {
  let component: SellerDetailsAdminComponent;
  let fixture: ComponentFixture<SellerDetailsAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellerDetailsAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerDetailsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
