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
    this.sellerDataService.currentData.subscribe(sellerdata => this.sellerData = sellerdata);
    this.sellerService.checkStatus().subscribe((respose)=>{
       this.applicationStatus1 = respose;
    });
    }

  onLogout() {
    this.cookieservice.deleteAll();
    this.router.navigate(['']);
  }

}
