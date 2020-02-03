import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SellerServiceService {

  private USERNAME = 'email';
  private PASSWORD = 'password';
  private USER_PASSWORD = 'sellerPassword';
  private APPLICATION_STATUS = 'applicationStatus';

  constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) { }


  loginUsername(email, password) {
    const userobject = {
      username: email,
      password

    };
    return this.http.post('sellerportal/seller/login', userobject);
  }


  checkSession(){

    const username = this.cookieService.get(this.USERNAME);
    const password = this.cookieService.get(this.PASSWORD);
    this.loginUsername(username, password).subscribe( data => {
      if(!data.valueOf()['exists'] || data.valueOf['valid']){
        this.cookieService.deleteAll();
        this.router.navigate(['/seller/login']);
      } else {
        this.saveLoginData(data);
        return true;
      }
    });


  }


  saveLoginData(user){
    this.cookieService.set(this.USERNAME, user[this.USERNAME]);
    this.cookieService.set(this.PASSWORD, user[this.USER_PASSWORD]);
    this.cookieService.set(this.APPLICATION_STATUS, user[this.APPLICATION_STATUS]);
  }
}
