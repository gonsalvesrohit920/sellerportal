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
  sellerData: object;
  applicationStatus1 :any
  value :string
  disable:boolean
  applicationStatus = this.cookieservice.get('applicationStatus');
  constructor(private cookieservice: CookieService,
              private sellerDataService: SellerDataService,
              private router: Router,
              private sellerService: SellerServiceService
              ) 
              {
                this.sellerId = this.cookieservice.get('id');
                console.log("id",this.sellerId)
                console.log(this.value)
                if(this.value!="Check Status"){
                this.value = "Check Status"
                this.disable = false
                }
               }
  async ngOnInit(){
    
    this.username = this.cookieservice.get('email');
    //this.sellerId = this.cookieservice.get('id')
    
    
    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/']);
    }     
    this.sellerDataService.currentData.subscribe(sellerdata => this.sellerData = sellerdata);
    this.keepCheckStatus(this.sellerId)
  }
    private keepCheckStatus(sellerId) {
      this.sellerService.checkStatus(sellerId).subscribe((respose)=>{
        this.applicationStatus1 = respose;
        if(this.applicationStatus1==="Accepted"){
          this.value = "Accpeted"
          this.disable=true
        }
        console.log( this.applicationStatus1)
     });
     } 
      onLogout() {
        this.cookieservice.deleteAll();
        this.router.navigate(['']);
      }
      Check(){
        this.sellerService.subjectRefresh.subscribe(()=>{
          this.keepCheckStatus(this.sellerId)
          console.log("checkkkkk",this.sellerId)
          });
         this.keepCheckStatus(this.sellerId)
      }
}
