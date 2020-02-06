import { Component, OnInit } from '@angular/core';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { KeyValuePipe } from '@angular/common';
import { FormGroup, FormControl, FormControlName, Validators, EmailValidator } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductComponentComponent } from '../product-component/product-component.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import {FileUploader} from "ng2-file-upload";
import { CookieService } from 'ngx-cookie-service';
import { ProductImage } from '../../Pojos/Pojos';
import { FileUploadService } from '../../providers/file-upload-service/file-upload.service';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.css']
})
export class AddProductsComponent implements OnInit {

  constructor(private sellerService:SellerServiceService, private router: Router,private snackBar: MatSnackBar,
              private cookieservice: CookieService,
              private uploadService: FileUploadService,) { }
  types = []
  enterAllDetails=false;
  data :any
  selectedType: string;
  CategoryQuestions = [];
  CategoryObject = {};
  ProductForm: FormGroup;
  seletedCategory: any;
  uploadForm: FormGroup;
  public uploader: FileUploader = new FileUploader({
    isHTML5: true
  });
  group = {};
  sid: string;
  
  async ngOnInit() {
    // this.types = [ {value:'Books',viewValue:'Books'},
    //                 {value:'Mobile',viewValue:'Mobiles'},
    //                 {value:'Laptops',viewValue:'Laptops'},
    //                 {value:'Shoes',viewValue:'Shoes'}      ];

    
    

    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/']);
    } else {
       this.sellerService.getCategroy(this.cookieservice.get('id')).subscribe((data) => {
        this.seletedCategory = data;
        console.log(this.seletedCategory);
      });

    }
  }

  GetType(ob) {
    console.log(ob.value);
    this.sellerService.getCategoryAttributes(ob.value).subscribe((attributes) => {
      this.data = attributes;
      console.log(this.data);
      this.data.forEach(element => {
        this.CategoryQuestions.push(element.catQuestion);
      });

      console.log(this.CategoryQuestions);
      this.group["Product Name"] = new FormControl('');
      this.group["Product Description"] = new FormControl('');
      this.group["Quantity"] = new FormControl('');
      this.group["Price"] = new FormControl('');
      this.CategoryQuestions.forEach(element => {
        this.group[element] = new FormControl('');
      });
      this.group["document"] = new FormControl('');
      this.ProductForm = new FormGroup(this.group);
    });
    this.CategoryQuestions = [];
  }


  constructImageMetaData(): ProductImage[]{
    let images: ProductImage[];
    images = this.uploader.queue.map( x => {
      let data = new ProductImage();
      data.imageType = x._file.name.split('.')[1];

      return data;
    });

    return images;
  }

  async onSubmit() {

    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/']);
    } else {


    if (this.ProductForm.valid){
      this.snackBar.open("Adding Product to database","",{
        duration: 2000,
      });
      console.log("qs",this.data)
      console.log(this.ProductForm.value)
      let senddata = {}
      let specdata = []
      senddata['name'] = this.ProductForm.get('Product Name').value
      senddata['decription'] = this.ProductForm.get('Product Description').value
      senddata['quantity'] = this.ProductForm.get('Quantity').value
      senddata['price'] = this.ProductForm.get('Price').value
      senddata['category'] = this.data[0].catId
      senddata['sellerId']= this.cookieservice.get('id');
      senddata['images'] = this.constructImageMetaData();
      console.log(senddata)
      let answers=[];
      this.data.forEach(element => {
        let ans={};
        ans['catqId']=element.catqId;
        ans['catId']=element.catId;
        ans['catAnswer']=this.ProductForm.get(element.catQuestion).value;
        answers.push(ans);      
      });
      senddata['attributes']=answers
      console.log(answers)
      console.log(senddata)

      this.sellerService.AddProduct(senddata).subscribe((response:[])=>{        
        console.log(response)
        if(response.length==0){
          console.log("Product not added") 
          this.snackBar.open("Unable to add product", "Ok",{
            duration: 2000,
          });      
        }
        else{

          this.uploadService.uploadProductImages('/sellerportal/seller/product/addProductImages', 
          response,
          this.uploader.queue.map( x => x._file)).subscribe((response) => {
            console.log(response);
            this.router.navigate(['/product/']);
        }

       );
          console.log("Product added successfully")
          this.snackBar.open("Product added succesfully", "Ok",{
            duration: 2000,
          });
          window.location.reload();
        }
      },(error:HttpErrorResponse)=>{
        if(error.status==406){
          console.log("response error", error)
          this.snackBar.open("Enter proper details", "Ok",{
            duration: 2000,
          });
        }
      })
    }
    else{

      this.enterAllDetails=true;
      this.snackBar.open("Enter all details", "Ok",{
        duration: 2000,
      });
    }
 
 
  }
 
  }


  onupload(){
  this.imageUpload(); 
  }

  imageUpload(){
    let f=1;
    for (let i = 0; i < this.uploader.queue.length ; i++) {
        if(i<=5){
        let fileItem = this.uploader.queue[i]._file;
        console.log(fileItem.type)
        if(fileItem.type!="jpg"){
                if(fileItem.size > 10000000){
                  alert("Each File should be less than 10 MB of size.");
                  f=0
                }
              }
        else{
          f=0
          this.snackBar.open("File Type Should Be JPG","ok",{
            duration:2000
          })
        }
      }else{
        f=0
        this.snackBar.open("Upload Limit Crossed(Max 5 Images)","ok",{
          duration:2000
        })
      }
      }
    for (let j = 0; j < this.uploader.queue.length; j++) {
        let data = new FormData();
        let fileItem = this.uploader.queue[j]._file;
        console.log(fileItem.name);
        data.append('file', fileItem);
        data.append('fileSeq', 'seq' + j);
        data.append( 'dataType', this.uploadForm.controls.type.value);
      //   CREATE THIS IN SERVICE
      //  this.uploadFile(data).subscribe(data => alert(data.message));
      //  uploadFile(data: FormData): Observable {
      //   return this.http.post('http://localhost:8080/upload', data);
      }
    this.uploader.clearQueue();

      
      // for (let j = 0; j < this.uploader.queue.length; j++) {
      //   let data = new FormData();
      //   let fileItem = this.uploader.queue[j]._file;
      //   console.log(fileItem.name);
      //   data.append('file', fileItem);
      //   data.append('fileSeq', 'seq'+j);
      //   data.append( 'dataType', this.uploadForm.controls.type.value);
      // //   CREATE THIS IN SERVICE
      // //  this.uploadFile(data).subscribe(data => alert(data.message));
      // //  uploadFile(data: FormData): Observable {
      // //   return this.http.post('http://localhost:8080/upload', data);
      // }
    return f;
      }

}
