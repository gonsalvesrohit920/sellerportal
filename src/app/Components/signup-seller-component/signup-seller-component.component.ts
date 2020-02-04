import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { SellerSignupDetailsService } from 'src/app/providers/seller-signup-details.service';
import { Router } from '@angular/router';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { element } from 'protractor';

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
  constructor(public service : SellerSignupDetailsService, private router: Router,private sellerservice:SellerServiceService) { }

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

    this.service.onSignupService(this.basicDetailsForm.get('name').value, this.basicDetailsForm.get('email').value, this.basicDetailsForm.get('password').value, this.contactDetailsForm.get('street').value, this.contactDetailsForm.get('city').value, this.contactDetailsForm.get('pincode').value, this.contactDetailsForm.get('phoneNo').value, this.documentsForm.get('panNo').value, this.documentsForm.get('gstInNo').value,this.selectedckeckboxlist).subscribe((details) => {
     console.log(this.selectedckeckboxlist)
      this.router.navigate(['/']);
    })
    
  }

  onPANSelected(event) {
    this.selectedPANFile = <File>event.target.files[0];
  }

  onGSTINSelected(event) {
    this.selectedGSTINFile = <File>event.target.files[0];
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
