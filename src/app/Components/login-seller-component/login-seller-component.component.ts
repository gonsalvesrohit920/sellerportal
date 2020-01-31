import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormControlName, Validators, EmailValidator } from '@angular/forms';
import {CookieService} from 'ngx-cookie-service'
import { Router } from '@angular/router';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
@Component({
  selector: 'app-login-seller-component',
  templateUrl: './login-seller-component.component.html',
  styleUrls: ['./login-seller-component.component.css']
})
export class LoginSellerComponentComponent implements OnInit {
  constructor(private cookieservice:CookieService,private router:Router,private sellerservice:SellerServiceService){
  }

  ngOnInit() {
  }
  Email:string;
  password:string;
  RegisterForm = new FormGroup({
    email:new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });
  
  onLogin(pagename:string){
    this.cookieservice.set('email',this.RegisterForm.get('email').value)
    this.cookieservice.set('password',this.RegisterForm.get('password').value)
    this.sellerservice.loginUsername(this.RegisterForm.get('email').value,this.RegisterForm.get('password').value).subscribe((data)=>{
      console.log(data)
    })
    this.router.navigate([`${pagename}`]);
  }
}
