import { Component, OnInit } from '@angular/core';
import { SellerDetailsAdminService } from 'src/app/providers/seller-details-admin.service';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seller-details-admin',
  templateUrl: './seller-details-admin.component.html',
  styleUrls: ['./seller-details-admin.component.css']
})
export class SellerDetailsAdminComponent implements OnInit {

  details : any;

  constructor(private service : SellerDetailsAdminService,private sellerService:SellerServiceService,private router:Router) { }

  ngOnInit() {
    this.service.getSellerDetails().subscribe((details) => {
      this.details = details;
      console.log(details);
    })
  }
  onAccepted(sellerid){
    this.sellerService.updateStatus(sellerid).subscribe((details)=>{
      console.log(details)
    })
     this.ngOnInit()
  }
}
