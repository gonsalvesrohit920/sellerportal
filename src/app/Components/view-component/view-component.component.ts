import { Component, OnInit, ApplicationRef, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { element, promise } from 'protractor';
import { SellerServiceService } from 'src/app/providers/seller-service.service';
import { resolve } from 'url';


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
  toggle(name, qty, price,details,f){
    console.log(name.value, qty.value, price.value)
     details.hidden=!details.hidden
    
  }
  trackByFn(index:any, item:any){
      return index;
  }
  constructor(private sellerService:SellerServiceService) { 
    console.log("con")
    this.fun();
  }
  delete(form){
    this.sellerService.deleteProduct(form.get('productId').value).subscribe((respone)=>{
      console.log(respone);
    })
  }
  update(form){
    console.log(form.value)
    let senddata={
      
    }
    senddata['name'] = form.get('name').value
    senddata['decription'] = form.get('decription').value
    senddata['quantity'] = form.get('quantity').value
    senddata['price'] = form.get('price').value
    senddata['productId']=form.get('productId').value;
    senddata['sellerId']=form.get('sellerId').value;
    senddata['category']=form.get('category').value;
    let answers={};
    let keys=Object.keys(form.value)
    keys.forEach(element => {
      let ans={};
      if(element!='name' && element!='decription' && element!='quantity' && element!='price'  && element!='productId'  && element!='sellerId' && element!='images'  && element!='attributes'  && element!='category' ){
       console.log("Element",element)
       
        ans['catId']=form.get('category').value;
      //  ans['catAnswer']=form.get(element.catQuestion).value;
      console.log(element,form.value[element])
      ans['catAnswer']=form.value[element]
      answers[element]=ans;   
      } 
    });
    senddata['questionAnswers']=answers
    console.log(answers)
    console.log(senddata)
    this.sellerService.updateProduct(senddata).subscribe((response)=>{
      console.log(response)
    })
    
  }
  async fun(){
    let loadData = await this.loadsellerProduct()
    if(loadData){
      console.log(this.data)
      
    }
  }
  async ngOnInit() {
    console.log("init")
    // this.data.forEach((element)=>{
    //   console.log(element)
    //   this.fg=new FormGroup({});
    //   this.keys=Object.keys(element);
    //   //this.fg.addControl()
    //   this.keys.forEach((k)=>{
    //     this.fg.addControl(k,new FormControl(element[k]))
    //   })
    //   console.log(this.fg)
      
    //   this.forms.push(this.fg)
    // })
    // console.log(this.forms)
    
  }
   loadsellerProduct(): Promise<boolean>{
    return new Promise((resolve, reject) => {
      let response: boolean;
      this.sellerService.getSellerProductList(1).subscribe(details=>{
        console.log(details)
        this.data =details
        response=true
        resolve(response)
        this.showInUI();
      })
      // this.loginUsername(username, password).subscribe( data => {

      //   this.delay(500);

      //   console.log(data);

      //   if (!data.valueOf()['exists'] || !data.valueOf()['valid']) {
      //     this.cookieService.deleteAll();
      //     response = false;
      //   } else {

      //     this.saveLoginData(data);
      //     response = true;
      //   }
      //   resolve(response);
      // });

    });
   }
   showInUI(){
      this.forms = [];
      

     this.forms=[];
     console.log(11, this.data)
     this.data.forEach((element)=>{
      this.fg=new FormGroup({});
      console.log(555, this.keys)
      this.keys=Object.keys(element);
      this.keys.forEach((k)=>{
        if(k!='questionAnswers')
        this.fg.addControl(k, new FormControl(element[k]))
        else{
          let dummy=Object.keys(element[k]);
          dummy.forEach((spec)=>{
            this.fg.addControl(spec,new FormControl(element[k][spec].catAnswer))
          })
        }
        
      })
      this.forms.push(this.fg)
    })
    this.fetched=true;
   }
}
