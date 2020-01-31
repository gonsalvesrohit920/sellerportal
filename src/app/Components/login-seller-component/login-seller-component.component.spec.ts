import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginSellerComponentComponent } from './login-seller-component.component';

describe('LoginSellerComponentComponent', () => {
  let component: LoginSellerComponentComponent;
  let fixture: ComponentFixture<LoginSellerComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginSellerComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginSellerComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
