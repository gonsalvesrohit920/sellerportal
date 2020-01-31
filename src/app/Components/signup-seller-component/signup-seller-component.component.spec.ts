import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupSellerComponentComponent } from './signup-seller-component.component';

describe('SignupSellerComponentComponent', () => {
  let component: SignupSellerComponentComponent;
  let fixture: ComponentFixture<SignupSellerComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupSellerComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupSellerComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
