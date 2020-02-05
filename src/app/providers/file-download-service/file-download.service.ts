import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class FileDownloadService {

  constructor(private httpClient: HttpClient) { }


  

  getImage(url = '/sellerportal/seller/getPanImage/13'): Observable<any> {
    return this.httpClient.get(url);
  }


  getProductImages(id: number, url = '/sellerportal/seller/product/getProductImages/', ){
    return this.httpClient.get(url + id);
  }


  

}
