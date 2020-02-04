import { Component, OnInit } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';

import { Router } from '@angular/router';
import { SellerDataService } from 'src/app/providers/seller-data-service/seller-data.service';
import { SellerServiceService } from 'src/app/providers/seller-service.service';


@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css']
})
export class ProductComponentComponent implements OnInit {
  username: string;
  sellerId:string
  sellerData: object;
  applicationStatus1 :any
  applicationStatus = this.cookieservice.get('applicationStatus');
  constructor(private cookieservice: CookieService,
              private sellerDataService: SellerDataService,
              private router: Router,
              private sellerService: SellerServiceService
              ) 
              {
                this.sellerId = this.cookieservice.get('id');
                console.log("id",this.sellerId)
               }
  async ngOnInit(){
    this.username = this.cookieservice.get('email');
    //this.sellerId = this.cookieservice.get('id')

    
    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/']);
    }
    
    this.sellerService.subjectRefresh.subscribe(()=>{
      this.keepCheckStatus(this.sellerId)
      console.log("checkkkkk",this.sellerId)
      });
     this.keepCheckStatus(this.sellerId)
     
     this.sellerDataService.currentData.subscribe(sellerdata => this.sellerData = sellerdata);

  }

    private keepCheckStatus(sellerId) {
      this.sellerService.checkStatus(sellerId).subscribe((respose)=>{
        this.applicationStatus1 = respose;
        console.log( this.applicationStatus1)
     });
     } 
      onLogout() {
        this.cookieservice.deleteAll();
        this.router.navigate(['']);
      }
}
