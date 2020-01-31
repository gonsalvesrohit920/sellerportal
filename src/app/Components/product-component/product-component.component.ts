import { Component, OnInit } from '@angular/core';
import {CookieService} from 'ngx-cookie-service'

@Component({
  selector: 'app-product-component',
  templateUrl: './product-component.component.html',
  styleUrls: ['./product-component.component.css']
})
export class ProductComponentComponent implements OnInit {
  username:string
  constructor(private cookieservice:CookieService) { }

  ngOnInit() {
    this.username=this.cookieservice.get('email')
  }

}
