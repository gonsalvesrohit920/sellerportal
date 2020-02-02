import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  constructor(cookieService: CookieService, router: Router, ) { }

  public postURL = 'sellerportal/admin/sellers';

  public route = 'admin/sellers';

  async ngOnInit() {
    
  }

}
