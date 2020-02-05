import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { SellerSignupDetailsService } from 'src/app/providers/seller-signup-details.service';
import { Router } from '@angular/router';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { element } from 'protractor';
import { Seller } from 'src/app/Pojos/Pojos';

@Component({
  selector: 'app-signup-seller-component',
  templateUrl: './signup-seller-component.component.html',
  styleUrls: ['./signup-seller-component.component.css'],
  providers: [SellerSignupDetailsService]
})
export class SignupSellerComponentComponent implements OnInit {

  details : any

  selectedGSTINFile: File = null;
  selectedPANFile: File = null;
  types = []
  selectedckeckbox={'Books':0,
  'Mobile':0,
'Laptops':0,
  'Shoes':0}
  selectedckeckboxlist = []
  constructor(public service : SellerSignupDetailsService, private router: Router,private sellerservice:SellerServiceService,private formBuilder: FormBuilder) {
    this.uploadForm = this.formBuilder.group({
      profile: [''],
      panImage: [''],
      gstinImage: [''],
    });
   }


   uploadForm: FormGroup;
   panUploadForm: FormGroup;
 
   gstinUploadForm: FormGroup;
 

  ngOnInit() {
    this.types = [ {value:'Books',viewValue:'Books'},
                    {value:'Mobile',viewValue:'Mobile'},
                    {value:'Laptops',viewValue:'Laptops'},
                    {value:'Shoes',viewValue:'Shoes'}      ];
  }

  basicDetailsForm = new FormGroup({
    name:new FormControl('Shashank',Validators.required),
    email: new FormControl('s@g.com',Validators.email),
    password : new FormControl('abc', Validators.required),
    confirmPassword : new FormControl('abc',Validators.required),
  });

  contactDetailsForm = new FormGroup({
    street : new FormControl('abc', Validators.required),
    city : new FormControl('abc', Validators.required),    
    pincode : new FormControl('560083', [Validators.required, Validators.pattern('[0-9]{6}')]),
    phoneNo : new FormControl('2378698765', [Validators.required, 
      Validators.pattern('[0-9]{10}')]),
  });

  documentsForm = new FormGroup({
    panNo : new FormControl('BAIPJ4788D', [Validators.required, Validators.pattern('[A-Z]{5}[0-9]{4}[A-Z]{1}')]),
    gstInNo : new FormControl('29BAIPJ4788D1ZC', [Validators.required, /*Validators.pattern('[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[A-Z]{2}')*/]),
  });

  onSignUp() {
    let keys=Object.keys(this.selectedckeckbox);
    keys.forEach((element)=>{
      if(this.selectedckeckbox[element]=='1')
      this.selectedckeckboxlist.push(element)
    })

    this.service.onSignupService(this.basicDetailsForm.get('name').value,
     this.basicDetailsForm.get('email').value, 
     this.basicDetailsForm.get('password').value, 
     this.contactDetailsForm.get('street').value, 
     this.contactDetailsForm.get('city').value, 
     this.contactDetailsForm.get('pincode').value, 
     this.contactDetailsForm.get('phoneNo').value, 
     this.documentsForm.get('panNo').value, 
     this.documentsForm.get('gstInNo').value,
     this.selectedckeckboxlist).subscribe((details: Seller) => {
     console.log(this.selectedckeckboxlist)
     console.log('Seller ID:' + details.id);
     this.service.uploadSignupImageService(details.id,
     this.uploadForm.get('panImage').value,
     this.uploadForm.get('gstinImage').value).subscribe((res: Uint8Array) => {
       console.log(res);
       this.router.navigate(['/']);

     });
    })
    
  }

  onPANSelected(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0] as File;
      console.log(file.name);
      this.selectedPANFile = file;
      this.uploadForm.get('panImage').setValue(file);
    }
  }

  onGSTINSelected(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0] as File;
      console.log(file.name);
      this.selectedGSTINFile = file;
      this.uploadForm.get('gstinImage').setValue(file);
    }

  }

  GetType(value,ob){
    if(ob.checked==true){
         this.selectedckeckbox[value]=1
    
    }
    else{
      this.selectedckeckbox[value] =0;
    }
    console.log(this.selectedckeckbox)
    
        
  }
}
