import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerServiceService {

  constructor(private http:HttpClient) { }

  loginUsername(email,password){
    let userobject = {
      username:email,
      password:password

    }
     return this.http.post('sellerportal/seller/login',userobject)
  }
}
