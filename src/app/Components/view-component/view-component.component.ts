// import { Component, OnInit, ApplicationRef, ChangeDetectorRef } from '@angular/core';
// import { FormGroup, FormControl } from '@angular/forms';
// import { SellerServiceService } from 'src/app/providers/seller-service.service';
// import { Router } from '@angular/router';
// import { HttpErrorResponse } from '@angular/common/http';
// import {MatSnackBar} from '@angular/material/snack-bar';
// import { CookieService } from 'ngx-cookie-service';
// import { FileDownloadService } from 'src/app/providers/file-download-service/file-download.service';
// import { DomSanitizer } from '@angular/platform-browser';

// @Component({
//   selector: 'app-view-component',
//   templateUrl: './view-component.component.html',
//   styleUrls: ['./view-component.component.css']
// })
// export class ViewComponentComponent implements OnInit {
//   forms=null;
//   fetched=false;
//   fg=new FormGroup({});
//   keys=[];
//   groups={};
//   public data:any;

//   hidden=true;
//   async edit(name, qty, price,details,f,counter){
//     await this.ngOnInit();
//     console.log(name.value, qty.value, price.value)
//     details.hidden=!details.hidden
//     let productId = f.get('productId').value;
//     let count = counter;
 
//     this.fetchProductimages(productId, count);
//   }


//   trackByFn(index: any, item: any) {
//       return index;
//   }

  
//   constructor(private sellerService:SellerServiceService, 
//               private router: Router,
//               private snackBar: MatSnackBar,
//               private cookieService: CookieService,
//               private fileDownloadService: FileDownloadService,
//               private sanitizer: DomSanitizer) { 
//     console.log("con")
//     this.fun();
//   }
//   async delete(form){
//     await this.ngOnInit();
//     this.sellerService.deleteProduct(form.get('productId').value).subscribe((response)=>{
//       console.log(response);
//       if(response==0){
//         console.log("Check your mail")
//         this.snackBar.open("Check your mail", "Ok",{
//           duration: 2000,
//         });
//       }
//       else{
//         console.log("Product deleted")
//         this.snackBar.open("Product deleted successfully", "Ok",{
//           duration: 2000,
//         });
//         window.location.reload();   
//       }
//       this.router.navigate(['/product'])
//     },(error:HttpErrorResponse)=>{
//       console.log(error.status)
//       })
//   }
//   update(form){
//     console.log(form.value)
//     let senddata={ }
//     senddata['name'] = form.get('name').value
//     senddata['decription'] = form.get('decription').value
//     senddata['quantity'] = form.get('quantity').value+form.get('addQuantity').value;
//     senddata['price'] = form.get('price').value
//     senddata['productId']=form.get('productId').value;
//     senddata['sellerId']=form.get('sellerId').value;
//     senddata['category']=form.get('category').value;
//     let answers={};
//     let keys=Object.keys(form.value)
//     keys.forEach(element => {
//       let ans = {};
//       if (element != 'name'
//       && element != 'decription'
//       && element != 'quantity'
//       && element != 'price'
//       && element != 'productId'
//       && element != 'sellerId'
//       && element != 'images'
//       && element != 'attributes'
//       && element != 'category' ) {
//        console.log('Element', element);

//        ans['catId'] = form.get('category').value;
//       //  ans['catAnswer']=form.get(element.catQuestion).value;
//        console.log(element, form.value[element]);
//        ans['catAnswer'] = form.value[element];
//        answers[element] = ans;
//       }
//     });
//     senddata['questionAnswers']=answers
//     console.log(answers)
//     console.log(senddata)
//     this.sellerService.updateProduct(senddata).subscribe((response)=>{
//         if(response==1){
//           this.snackBar.open("Product updated succesfully", "Ok",{
//             duration: 2000,
//           });
//           window.location.reload();
//         }
//         else if(response==0){
//           this.snackBar.open("Couldn't update the Product data", "Ok",{
//             duration: 2000,
//           });
//         }
//         this.router.navigate(['/product'])
//       },(error:HttpErrorResponse)=>{
//         if(error.status==406){
//           console.log("response error", error)
//           this.snackBar.open("Enter proper details", "Ok",{
//             duration: 2000,
//           });
//         }
//       }
//     )
    
//   }
//   async fun() {
//     let loadData = await this.loadsellerProduct();
//     if (loadData) {
//       console.log("data",this.data);

//     }
//   }
//   async ngOnInit() {
//     console.log("init");
//     // this.data.forEach((element)=>{
//     const session = await this.sellerService.checkSession();
//     console.log('Session:' +  session);
//     if (!session) {
//         this.router.navigate(['/']);
//       }

//   }
//    loadsellerProduct(): Promise<boolean> {
//     return new Promise((resolve, reject) => {
//       let response: boolean;
//       this.sellerService.getSellerProductList(this.cookieService.get('id')).subscribe((details: object[])  => {
//         console.log(details);
//         this.data = details.map(x => {
//           x['images'] = [];
//           return x;
//         });
//         response = true;
//         resolve(response);
//         this.showInUI();
//       });
//       // this.loginUsername(username, password).subscribe( data => {

//       //   this.delay(500);

//       //   console.log(data);

//       //   if (!data.valueOf()['exists'] || !data.valueOf()['valid']) {
//       //     this.cookieService.deleteAll();
//       //     response = false;
//       //   } else {

//       //     this.saveLoginData(data);
//       //     response = true;
//       //   }
//       //   resolve(response);
//       // });

//     });
//    }
//    showInUI() {
//       this.forms = [];
      

//       this.forms=[];
//       console.log(11, this.data)
//       this.data.forEach((element)=>{
//       this.fg=new FormGroup({});
//       console.log(555, this.keys)
//       this.keys=Object.keys(element);
//       this.keys.forEach((k)=>{
//         if(k!='questionAnswers'){
//           this.fg.addControl(k, new FormControl(element[k]))
//         }
//         else{
//           let dummy=Object.keys(element[k]);
//           dummy.forEach((spec)=>{
//             this.fg.addControl(spec,new FormControl(element[k][spec].catAnswer))
//           })
//         }
        
//       })
//       this.fg.addControl('addQuantity',new FormControl(''));
//       this.forms.push(this.fg)
//     })
//       this.fetched=true;
//    }

   
//    fetchProductimages(productId: number, index) {

//     this.data[index]['images'] = [];
//     this.fileDownloadService.getProductImages(productId).subscribe((res: []) =>{

//       console.log(res);

//       let filenames;

//       for (let image of res) {

//         this.data[index]['images'].push(this.sanitizer.bypassSecurityTrustUrl( image )) ;

//       }

//     });
//    }


//    getDetailsById(productId: number) {}
// }
import { Component, OnInit, ApplicationRef, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import {MatSnackBar} from '@angular/material/snack-bar';
import { CookieService } from 'ngx-cookie-service';
import { FileDownloadService } from 'src/app/providers/file-download-service/file-download.service';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
selector: 'app-view-component',
templateUrl: './view-component.component.html',
styleUrls: ['./view-component.component.css']
})
export class ViewComponentComponent implements OnInit {
forms=null;
fetched=false;
fg=new FormGroup({});
keys=[];
groups={};
public data:any;

hidden=true;
async edit(name, qty, price,details,f,counter){

console.log(name.value, qty.value, price.value)
details.hidden=!details.hidden
let productId = f.get('productId').value;
let count = counter;

this.fetchProductimages(productId, count);
}


trackByFn(index: any, item: any) {
return index;
}


constructor(private sellerService:SellerServiceService,
private router: Router,
private snackBar: MatSnackBar,
private cookieService: CookieService,
private fileDownloadService: FileDownloadService,
private sanitizer: DomSanitizer) {
console.log("con")

}
async delete(form){
await this.ngOnInit();
this.sellerService.deleteProduct(form.get('productId').value).subscribe((response)=>{
console.log(response);
if(response==0){
console.log("Check your mail")
this.snackBar.open("Check your mail", "Ok",{
duration: 2000,
});
}
else{
console.log("Product deleted")
this.snackBar.open("Product deleted successfully", "Ok",{
duration: 2000,
});
this.ngOnInit();
}
this.router.navigate(['/product'])
},(error:HttpErrorResponse)=>{
console.log(error.status)
})
}
update(form){
console.log(form.value)
let senddata={ }
senddata['name'] = form.get('name').value
senddata['decription'] = form.get('decription').value
senddata['quantity'] = Number(form.get('quantity').value)+Number(form.get('addQuantity').value);
senddata['price'] = form.get('price').value
senddata['productId']=form.get('productId').value;
senddata['sellerId']=form.get('sellerId').value;
senddata['category']=form.get('category').value;
let answers={};
let keys=Object.keys(form.value)
keys.forEach(element => {
let ans = {};
if (element != 'name'
&& element != 'decription'
&& element != 'quantity'
&& element != 'price'
&& element != 'productId'
&& element != 'sellerId'
&& element != 'images'
&& element != 'attributes'
&& element != 'category' ) {
console.log('Element', element);

ans['catId'] = form.get('category').value;
// ans['catAnswer']=form.get(element.catQuestion).value;
console.log(element, form.value[element]);
ans['catAnswer'] = form.value[element];
answers[element] = ans;
}
});
senddata['questionAnswers']=answers
console.log(answers)
console.log(senddata)
this.sellerService.updateProduct(senddata).subscribe((response)=>{
if(response==1){
this.snackBar.open("Product updated succesfully", "Ok",{
duration: 2000,
});
this.ngOnInit();
}
else if(response==0){
this.snackBar.open("Couldn't update the Product data", "Ok",{
duration: 2000,
});
}
this.router.navigate(['/product'])
},(error:HttpErrorResponse)=>{
if(error.status==406){
console.log("response error", error)
this.snackBar.open("Enter proper details", "Ok",{
duration: 2000,
});
}
}
)

}
async fun() {
let loadData = await this.loadsellerProduct();
if (loadData) {
console.log("data",this.data);

}
}
async ngOnInit() {
console.log("init");
// this.data.forEach((element)=>{
const session = await this.sellerService.checkSession();
console.log('Session:' + session);
if (!session) {
this.router.navigate(['/']);
}
else{
this.fun();
}
}
loadsellerProduct(): Promise<boolean> {
return new Promise((resolve, reject) => {
let response: boolean;
this.sellerService.getSellerProductList(this.cookieService.get('id')).subscribe((details: object[]) => {
console.log(details);
this.data = details.map(x => {
x['images'] = [];
return x;
});
response = true;
resolve(response);
this.showInUI();
});
// this.loginUsername(username, password).subscribe( data => {

// this.delay(500);

// console.log(data);

// if (!data.valueOf()['exists'] || !data.valueOf()['valid']) {
// this.cookieService.deleteAll();
// response = false;
// } else {

// this.saveLoginData(data);
// response = true;
// }
// resolve(response);
// });

});
}
showInUI() {
this.forms = [];


this.forms=[];
console.log(11, this.data)
this.data.forEach((element)=>{
this.fg=new FormGroup({});
console.log(555, this.keys)
this.keys=Object.keys(element);
this.keys.forEach((k)=>{
if(k!='questionAnswers'){
this.fg.addControl(k, new FormControl(element[k]))
}
else{
let dummy=Object.keys(element[k]);
dummy.forEach((spec)=>{
this.fg.addControl(spec,new FormControl(element[k][spec].catAnswer))
})
}

})
this.fg.addControl('addQuantity',new FormControl(''));
this.forms.push(this.fg)
})
this.fetched=true;
}


fetchProductimages(productId: number, index) {

this.data[index]['images'] = [];
this.fileDownloadService.getProductImages(productId).subscribe((res: []) =>{

console.log(res);

let filenames;

for (let image of res) {

this.data[index]['images'].push(this.sanitizer.bypassSecurityTrustUrl( image )) ;

}

});
}
getDetailsById(productId: number) {}
}