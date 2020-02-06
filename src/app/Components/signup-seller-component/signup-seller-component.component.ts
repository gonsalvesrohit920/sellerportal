import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl, FormBuilder } from '@angular/forms';
import { SellerSignupDetailsService } from 'src/app/providers/seller-signup-details.service';
import { Router } from '@angular/router';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { element } from 'protractor';
import { Seller } from 'src/app/Pojos/Pojos';
import { createViewChild } from '@angular/compiler/src/core';

@Component({
selector: 'app-signup-seller-component',
templateUrl: './signup-seller-component.component.html',
styleUrls: ['./signup-seller-component.component.css'],
providers: [SellerSignupDetailsService]
})
export class SignupSellerComponentComponent implements OnInit {

details : any
formInvalid=false;
selectedGSTINFile: File = null;
selectedPANFile: File = null;
userExists=false;
types = []
currentTab=0;
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


emailExists = false;

ngOnInit() {
this.types = [ {value:'Books',viewValue:'Books'},
{value:'Mobile',viewValue:'Mobile'},
{value:'Laptops',viewValue:'Laptops'},
{value:'Shoes',viewValue:'Shoes'} ];
}

basicDetailsForm = new FormGroup({
name:new FormControl('',),
email: new FormControl('',),
password : new FormControl('', ),
confirmPassword : new FormControl('',),
});

contactDetailsForm = new FormGroup({
street : new FormControl(),
city : new FormControl(),
pincode : new FormControl('', [ Validators.pattern('[0-9]{6}')]),
phoneNo : new FormControl('',[
Validators.pattern('[0-9]{10}')]),
});

documentsForm = new FormGroup({
panNo : new FormControl(null , [ Validators.pattern('[A-Z]{5}[0-9]{4}[A-Z]{1}')]),
gstInNo : new FormControl(null, [Validators.pattern('[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[A-Z]{2}')]),
});
checkAtleastOneCategorySelected(){
let categoryKeys=Object.keys(this.selectedckeckbox);
let categoriesValid=false;
console.log(categoryKeys);
categoryKeys.forEach((element)=>{
console.log(this.selectedckeckbox[element])
if(this.selectedckeckbox[element]==1){
categoriesValid=true;
}
})
return categoriesValid;
}
onSignUp() {
if(this.basicDetailsForm.valid&&
this.contactDetailsForm.valid&&
this.documentsForm.valid&&
this.basicDetailsForm.get('password').value==this.basicDetailsForm.get('confirmPassword').value&&
this.uploadForm.get('gstinImage').value.size!=undefined&&
this.uploadForm.get('panImage').value.size!=undefined&&
this.checkAtleastOneCategorySelected())
{
let keys=Object.keys(this.selectedckeckbox);
this.formInvalid=false;
this.userExists=false;
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
this.emailExists = details.exists;

console.log(this.emailExists);
if(!this.emailExists){
this.service.uploadSignupImageService(details.id,
this.uploadForm.get('panImage').value,
this.uploadForm.get('gstinImage').value).subscribe((res: Uint8Array) => {
console.log("Signed Up Images:" + res);
this.router.navigate(['/']);

});
}
else{
this.userExists=true;
}
});
}
else{
this.formInvalid=true;
}
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