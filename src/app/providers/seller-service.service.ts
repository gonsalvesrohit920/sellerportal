import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs/internal/Observable';
import { Subject } from 'rxjs';
import { tap } from 'rxjs/operators'
@Injectable({
  providedIn: 'root'
})
export class SellerServiceService {
  private USERNAME = 'email';
  private PASSWORD = 'password';
  private USER_PASSWORD = 'sellerPassword';
  private APPLICATION_STATUS = 'applicationStatus';
  
  private _subjectRefresh = new Subject<void>();
  get subjectRefresh(){
    return this._subjectRefresh
  }

  constructor(private http:HttpClient, private cookieService: CookieService) { }

  loginUsername(email,password){
    let userobject = {
      username:email,
      password:password

    }
     return this.http.post('sellerportal/seller/login',userobject)
  
  }
  
  getCategoryAttributes(categoryname){
    return this.http.get('sellerportal/seller/products/AddProductView/'+categoryname+'');
  }
  AddProduct(ProductDetails){
     return this.http.post('sellerportal/seller/products/AddProduct/',ProductDetails)
  }
  getSellerProductList(sellerId){
    return this.http.get("sellerportal/seller/products/GetProducts/"+sellerId)
  }
  updateProduct(product){
    return this.http.post("sellerportal/seller/product/UpdateProduct",product)
  }

  deleteProduct(productId){
    return this.http.delete("sellerportal/seller/product/DeleteProduct/"+productId)
  }
  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  checkSession(): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const username = this.cookieService.get(this.USERNAME);
      const password = this.cookieService.get(this.PASSWORD);
      let response: boolean;

      this.loginUsername(username, password).subscribe( data => {

        this.delay(500);

        console.log(data);

        if (!data.valueOf()['exists'] || !data.valueOf()['valid']) {
          this.cookieService.deleteAll();
          response = false;
        } else {

          this.saveLoginData(data);
          response = true;
        }
        resolve(response);
      });

    });

  }

  checkStatus():Observable<string>{
    return this.http.get("sellerportal/seller/product/checkstatus",{ responseType:'text'})
  }
  
  updateStatus():Observable<any>{
    return this.http.delete("sellerportal/seller/adminUpdate/1").pipe(tap(()=>{
      console.log("updating....")
        this._subjectRefresh.next();
    }));
  }
  saveLoginData(user){
    this.cookieService.set(this.USERNAME, user[this.USERNAME]);
    this.cookieService.set(this.PASSWORD, user[this.PASSWORD]);
    this.cookieService.set(this.APPLICATION_STATUS, user[this.APPLICATION_STATUS]);
  }
}
