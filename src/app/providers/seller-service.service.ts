import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerServiceService {

  constructor(private http: HttpClient) { }

  loginUsername(email, password) {
    const userobject = {
      username: email,
      password

    };
    return this.http.post('sellerportal/seller/login', userobject);
  }
}
