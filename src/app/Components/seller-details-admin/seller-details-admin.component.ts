import { Component, OnInit } from '@angular/core';
import { SellerDetailsAdminService } from 'src/app/providers/seller-details-admin.service';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { FileDownloadService } from '../../providers/file-download-service/file-download.service';

@Component({
  selector: 'app-seller-details-admin',
  templateUrl: './seller-details-admin.component.html',
  styleUrls: ['./seller-details-admin.component.css']
})
export class SellerDetailsAdminComponent implements OnInit {

  details : any;

  private image : any;
  private readonly imageType : string = 'data:image/JPG;base64,';
  private displayimage = false;

  constructor(private service: SellerDetailsAdminService,
    private sellerService: SellerServiceService,
    private fileDownloadService: FileDownloadService,
    private sanitizer: DomSanitizer,
    private router: Router) { }

  ngOnInit() {
    this.service.getSellerDetails().subscribe((details) => {
      this.details = details;
      console.log(details);

      
    })
  }

  getPanImage(id){

    this.fileDownloadService.getImage('/sellerportal/seller/getPanImage/' + id).subscribe((data) => {
      this.image = this.sanitizer.bypassSecurityTrustUrl(this.imageType + data['content']);
      this.displayimage = true;
    });

  

  }

  getGstinImage(id){

    this.fileDownloadService.getImage('/sellerportal/seller/getGstinImage/' + id).subscribe((data) => {
      this.image = this.sanitizer.bypassSecurityTrustUrl(this.imageType + data['content']);
      this.displayimage = true;
    });
  }


  onAccepted(sellerid){
    this.sellerService.updateStatus(sellerid).subscribe((details)=>{
      console.log(details)
    })
     this.ngOnInit()
  }
}
