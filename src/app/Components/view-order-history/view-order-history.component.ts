import { Component, OnInit } from '@angular/core';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import {CookieService} from 'ngx-cookie-service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-view-order-history',
  templateUrl: './view-order-history.component.html',
  styleUrls: ['./view-order-history.component.css']
})
export class ViewOrderHistoryComponent implements OnInit {
  orderHistory = {}
  constructor(
    private cookieservice: CookieService,
    private sellerService: SellerServiceService,
    private router: Router) { 
      this.orderHistory = null;
    sellerService.getOrdersOfSeller(this.cookieservice.get('id')).subscribe((response: {}) => {
      console.log(response);
      this.orderHistory = response;
    });
  }

  async ngOnInit() {

    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
        this.router.navigate(['/']);
      }
  }

}
