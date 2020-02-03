import { Component, OnInit } from '@angular/core';
import { SellerDetailsAdminService } from 'src/app/providers/seller-details-admin.service';

@Component({
  selector: 'app-seller-details-admin',
  templateUrl: './seller-details-admin.component.html',
  styleUrls: ['./seller-details-admin.component.css']
})
export class SellerDetailsAdminComponent implements OnInit {

  details : any;

  constructor(private service : SellerDetailsAdminService) { }

  ngOnInit() {
    this.service.getSellerDetails().subscribe((details) => {
      this.details = details;
      console.log(details);
    })
  }

}
