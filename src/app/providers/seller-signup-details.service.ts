import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SellerSignupDetailsService {

  constructor(public http: HttpClient) { }

  onSignupService(name, email, password, street, city, pincode, phoneNo, panNo, gstInNo, category,panImageType = 'JPG',gstInImageType = 'JPG') {
    const sellerObj = {
      name,
      email,
      password,
      contact : {
        street,
        city,
        pincode,
        phoneNo
      },
      documents : {
        panNo,
        panImageType,
        gstInNo,
        gstInImageType
      },
      category
    };

    const blobSeller = new Blob([JSON.stringify(sellerObj)], {
      type: 'application/json',
    });



    console.log(sellerObj);
    return this.http.post('/sellerportal/seller/signup', sellerObj);
  }

  uploadSignupImageService(sellerId, panImage: any = null, gstImage: any = null) {
    const formData = new FormData();
    formData.append('sellerId', sellerId);
    formData.append('panImage', panImage);
    formData.append('gstImage', gstImage);
    return this.http.post('/sellerportal/seller/signup/images/' + sellerId, formData);
  }
}
