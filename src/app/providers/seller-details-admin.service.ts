import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerDetailsAdminService {

  constructor(private http : HttpClient) { }

  getSellerDetails(){
    return this.http.get("sellerportal/admin");
  }
  sendmail(sendobj){
    return this.http.post('sellerportal/admin/reject',sendobj)
  }
}
