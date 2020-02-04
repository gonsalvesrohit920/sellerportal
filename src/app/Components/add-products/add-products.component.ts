import { Component, OnInit } from '@angular/core';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { KeyValuePipe } from '@angular/common';
import { FormGroup, FormControl, FormControlName, Validators, EmailValidator } from '@angular/forms';
import { element } from 'protractor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.css']
})
export class AddProductsComponent implements OnInit {

  constructor(private sellerService:SellerServiceService, private router: Router) { }
  types = []
  data :any
  selectedType: string;
  CategoryQuestions=[]
  CategoryObject= {}
  ProductForm:FormGroup;
group={} 
  async ngOnInit() {
    this.types = [ {value:'Books',viewValue:'Books'},
                    {value:'Mobile',viewValue:'Mobiles'},
                    {value:'Laptops',viewValue:'Laptops'},
                    {value:'Shoes',viewValue:'Shoes'}      ];
    const session = await this.sellerService.checkSession();
    console.log('Session:' +  session);
    if (!session) {
      this.router.navigate(['/']);
    }
  }
 
  GetType(ob) {
    console.log(ob.value);
      this.sellerService.getCategoryAttributes(ob.value).subscribe((attributes)=>{
      this.data =attributes;
      console.log(this.data)
      this.data.forEach(element => {
        this.CategoryQuestions.push(element.catQuestion)
      }); 
      
      console.log(this.CategoryQuestions)
      this.group["Product Name"] =new FormControl('');
      this.group["Product Description"] =new FormControl('');
      this.group["Quantity"] =new FormControl('');
      this.group["Price"] =new FormControl('');
      this.CategoryQuestions.forEach(element=>{
        this.group[element] =new FormControl('');
      });
      this.ProductForm = new FormGroup(this.group);
    });
    this.CategoryQuestions=[]
  }

  onSubmit() {
    console.log("qs",this.data)
    console.log(this.ProductForm.value)
    let senddata = {}
    let specdata = []
    senddata['name'] = this.ProductForm.get('Product Name').value
    senddata['decription'] = this.ProductForm.get('Product Description').value
    senddata['quantity'] = this.ProductForm.get('Quantity').value
    senddata['price'] = this.ProductForm.get('Price').value
    senddata['category'] = this.data[0].catId
    senddata['sellerId']=1
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

    this.sellerService.AddProduct(senddata).subscribe((response)=>{
      console.log(response)
      this.router.navigate(['/product/'])
    })
  }
}
