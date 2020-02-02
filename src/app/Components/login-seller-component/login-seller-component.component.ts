import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormControlName, Validators, EmailValidator } from '@angular/forms';
import {CookieService} from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { SellerServiceService } from 'src/app/providers/seller-service.service';

import { from } from 'rxjs';
import { SellerDataService } from 'src/app/providers/seller-data-service/seller-data.service';
@Component({
  selector: 'app-login-seller-component',
  templateUrl: './login-seller-component.component.html',
  styleUrls: ['./login-seller-component.component.css']
})
export class LoginSellerComponentComponent implements OnInit {
  constructor(private cookieservice: CookieService, private router: Router,
              private sellerservice: SellerServiceService, private sellerDataService: SellerDataService) {

  }

  errorMessage: string;
  Email: string;
  password: string;
  RegisterForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });



  sellerData: object;


  async ngOnInit() {
    this.sellerDataService.currentData.subscribe(sellerdata => this.sellerData = sellerdata);
    let session = await this.sellerservice.checkSession();
    console.log(session);
    if (session) {
      this.router.navigate(['product']);
    }
  }

  submit() {
    this.sellerDataService.changeData(this.sellerData);
  }

  onLogin(pagename: string) {
    let bool = false;

    this.sellerservice.loginUsername(this.RegisterForm.get('email').value, this.RegisterForm.get('password').value).subscribe((data) => {
      console.log(data.valueOf());
      bool = true;
      console.log(bool + '');

      this.sellerData = data;

      if (!data.valueOf()['exists']) {
          this.errorMessage = 'User Does Not Exist';

      } else if (!data.valueOf()['valid']) {
        this.errorMessage = 'Please Check Your Password';
      } else {

        this.router.navigate([`${pagename}`]);
        this.cookieservice.set('email', this.RegisterForm.get('email').value);
		    this.cookieservice.set('password', this.RegisterForm.get('password').value);
		this.cookieservice.set('applicationStatus',this.sellerData['applicationStatus']);
        console.log(this.RegisterForm.get('email').value);
		
      }



    });
  }


  revalidateLogin(){
    let username = this.cookieservice.get('email');
    let password = this.cookieservice.get('password');
  }
}
