import { Component, OnInit, Output, Input } from '@angular/core';
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
  tabIndex=1;
  username: string;
  sellerId:string
  constructor(private cookieservice: CookieService,
              private sellerDataService: SellerDataService,
              private router: Router,
              private sellerService: SellerServiceService
              ) { }

  sellerData: object;
  applicationStatus1 :any
  applicationStatus = this.cookieservice.get('applicationStatus');

  async ngOnInit() {
    
    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/']);
    }
    this.username = this.cookieservice.get('email');
    this.sellerId = this.cookieservice.get('id')
    this.sellerDataService.currentData.subscribe(sellerdata => this.sellerData = sellerdata);
    this.sellerService.checkStatus(this.sellerId).subscribe((respose)=>{
       this.applicationStatus1 = respose;
    });

    this.sellerService.subjectRefresh.subscribe(()=>{
      this.keepCheckStatus(this.sellerId)
      });
  this.keepCheckStatus(this.sellerId)
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
