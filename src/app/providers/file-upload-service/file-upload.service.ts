import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  constructor(private http: HttpClient) { }



  public uploadImage(url: string, image){

  }


  public uploadProductImages(url: string = '/sellerportal/seller/product/addProductImages', imageIds: number[] , image: File[]) {

    const formData = new FormData();

    for(let i= 0; i < imageIds.length; i++){
        formData.append('' + imageIds[i], image[i]);
    }


    return this.http.post(url, formData);

  }
}
