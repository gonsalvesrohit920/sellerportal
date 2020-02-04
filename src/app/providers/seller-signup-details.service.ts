import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerSignupDetailsService {

  constructor(public http : HttpClient) { }

  onSignupService(name, email, password, street, city, pincode, phoneNo, panNo, gstInNo){
    let sellerObj = {
      'name' : name,
      'email' : email,
      'password' : password,
      'contact' : {
        'street' : street,
        'city' : city,
        'pincode' : pincode,
        'phoneNo' : phoneNo
      },
      'documents' : {
        'panNo' : panNo,
        'panImageType' : "jpg",
        'gstInNo' : gstInNo,
        'gstInImageType' : ".jpg"
      }
    }
    console.log(sellerObj);
    return this.http.post("/sellerportal/seller/signup", sellerObj);
  }
}
