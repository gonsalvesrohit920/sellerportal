import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs/internal/Observable';
import { Subject } from 'rxjs';
import { tap } from 'rxjs/operators'
import { Router } from '@angular/router';

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
  
  private _subjectRefresh = new Subject<void>();
  get subjectRefresh(){
    return this._subjectRefresh
  }

  userobject = {
    username: '',
    password: '',
    validate: false,
  };

  private defaultPostURL = 'sellerportal/seller/login';
  private defaultAdminPostUrl = '/sellerportal/admin/login' ;
  private defaultSessionValidationURL = 'sellerportal/seller/validate_session';
  private defaultAdminSessionValidationURL = 'sellerportal/admin/validate_session';
  constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) { }

  loginUsername(email, password, postURL = this.defaultPostURL) {

    this.userobject.username = email;
    this.userobject.password = password;

    console.log(this.userobject);
    return this.http.post(postURL, this.userobject);
  }

  getCategoryAttributes(categoryname) {
    return this.http.get('sellerportal/seller/products/AddProductView/' + categoryname + '');
  }
  AddProduct(ProductDetails) {
     return this.http.post('sellerportal/seller/products/AddProduct/', ProductDetails);
  }
  getSellerProductList(sellerId){
    return this.http.get("sellerportal/seller/products/GetProducts/"+sellerId)
  }
  updateProduct(product){
    return this.http.post("sellerportal/seller/product/UpdateProduct",product)
  }
  getOrdersOfSeller(sellerId){
    return this.http.get("sellerportal/order/GetOrder/"+sellerId)
  }
  deleteProduct(productId){
    return this.http.delete("sellerportal/seller/product/DeleteProduct/"+productId)
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
        this.defaultSessionValidationURL,
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

  checkStatus(sellerId):Observable<string>{
    return this.http.get("sellerportal/seller/product/checkstatus/"+sellerId,{ responseType:'text'})
  }
  
  updateStatus(SellerId):Observable<any>{
    return this.http.delete("sellerportal/seller/adminUpdate/"+SellerId).pipe(tap(()=>{
      console.log("updating....")
        this._subjectRefresh.next();
    }));
  }


}
