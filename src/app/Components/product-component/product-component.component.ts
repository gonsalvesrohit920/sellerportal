import { Component, OnInit } from '@angular/core';
import {CookieService} from 'ngx-cookie-service';

import { Router } from '@angular/router';
import { SellerDataService } from 'src/app/providers/seller-data-service/seller-data.service';


@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css']
})
export class ProductComponentComponent implements OnInit {
  username: string;
  constructor(private cookieservice: CookieService, private sellerDataService:SellerDataService , private router: Router) { }

  sellerData: object;

  applicationStatus = this.cookieservice.get('applicationStatus');

  ngOnInit() {
    this.username = this.cookieservice.get('email');
    this.sellerDataService.currentData.subscribe(sellerdata => this.sellerData = sellerdata);
    }

  onLogout() {
    this.cookieservice.deleteAll();
    this.router.navigate(['']);
  }

}
