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
  str:string
  email:string
  sellerId:string

  private image : any;
  private readonly imageType : string = 'data:image/JPG;base64,';
  private displayimage = false;

  constructor(private service: SellerDetailsAdminService,
    private sellerService: SellerServiceService,
    private fileDownloadService: FileDownloadService,
    private sanitizer: DomSanitizer,
    private router: Router) { }

  async ngOnInit() {

    const session = await this.sellerService.checkSession(true);
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/admin/login']);
    }
    else{
    this.service.getSellerDetails().subscribe((details) => {
      this.details = details;
      console.log(details);


    });
  
  }
  }

  async getPanImage(id,im){
    
    const session = await this.sellerService.checkSession(true);
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/admin/login']);
    } else {

    this.fileDownloadService.getImage('/sellerportal/seller/getPanImage/' + id).subscribe((data) => {
      this.image = this.sanitizer.bypassSecurityTrustUrl(this.imageType + data['content']);
      this.displayimage = true;
      im.hidden =!im.hidden
    }); }



  }

  async getGstinImage(id,im){
    const session = await this.sellerService.checkSession(true);
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/admin/login']);
    } else {

    this.fileDownloadService.getImage('/sellerportal/seller/getGstinImage/' + id).subscribe((data) => {
      this.image = this.sanitizer.bypassSecurityTrustUrl(this.imageType + data['content']);
      this.displayimage = true;
      im.hidden = !im.hidden
    }); }
  }


  async onAccepted(sellerid){

    const session = await this.sellerService.checkSession(true);
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/admin/login']);
    } else {
    this.sellerService.updateStatus(sellerid).subscribe((details)=>{
      console.log(details);
      this.ngOnInit()
    });
     
  } }

  onReject(sellerId,detail,email){
    this.email = email
    this.sellerId = sellerId
    detail.hidden = !detail.hidden
    this.sendmsg()
}
sendmsg(){
  console.log(this.email)
  console.log(this.str)
  
    let sendobj= {
      'email' : this.email,
      'password'  : this.str,
      'id':this.sellerId
    }
    this.service.sendmail(sendobj).subscribe((response)=>{
      console.log(response)
    })   
}
}
