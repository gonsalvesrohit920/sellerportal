import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class SellerServiceService {
  
  private USERNAME = 'email';
  private PASSWORD = 'password';
  private USER_PASSWORD = 'sellerPassword';
  private APPLICATION_STATUS = 'applicationStatus';
  private ID = 'id';
  private IS_ADMIN = 'isAdmin';

  userobject = {
    username: '',
    password: ''
  };

  private defaultPostURL = 'sellerportal/seller/login';
  private defaultAdminPostUrl = '/sellerportal/admin/login' ;
  constructor(private http: HttpClient, private cookieService: CookieService) { }

  loginUsername(email, password, postURL = this.defaultPostURL) {
    this.userobject.username = email;
    this.userobject.password = password;
    return this.http.post(postURL, this.userobject);
  }

  getCategoryAttributes(categoryname) {
    return this.http.get('sellerportal/seller/products/AddProductView/' + categoryname + '');
  }
  AddProduct(ProductDetails) {
     return this.http.post('sellerportal/seller/products/AddProduct/', ProductDetails);
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  checkSession(isAdmin = false): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const username = this.cookieService.get(this.USERNAME);
      const password = this.cookieService.get(this.PASSWORD);
      let response: boolean;

      // Used to login User and Admin on the postURL
      this.loginUsername(username,
        password,
        isAdmin ?
        this.defaultAdminPostUrl :
        this.defaultPostURL
        ).subscribe( data => {

        this.delay(500);

        console.log(data);

        if (!data.valueOf()['exists'] || !data.valueOf()['valid']) {
          this.cookieService.deleteAll();
          response = false;
        } else {

          this.saveLoginData(data, isAdmin);
          response = true;

        }
        resolve(response);
      });

    });

  }


  saveLoginData(user, isAdmin = false) {
    this.cookieService.set(this.ID, user[this.ID] + '');
    this.cookieService.set(this.USERNAME, user[this.USERNAME]);
    this.cookieService.set(this.PASSWORD, user[this.PASSWORD]);
    this.cookieService.set(this.APPLICATION_STATUS, user[this.APPLICATION_STATUS]);

    if (isAdmin) {
      this.cookieService.set(this.IS_ADMIN, isAdmin + '');
    }
  }
}
