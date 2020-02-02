import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { element } from 'protractor';


@Component({
  selector: 'app-view-component',
  templateUrl: './view-component.component.html',
  styleUrls: ['./view-component.component.css']
})
export class ViewComponentComponent implements OnInit {
  forms=[];
  fg=new FormGroup({});
  keys=[];
  groups={};
  data=[
    {
      "name":"Java",
      "price":"555",
      "quantityAvailable":"200",
      "genre":"Study Material",
      "author":"Herbt Schieldt",
      "pages":"1032"
      },
      {
        "name":"abcd",
        "price":"2323",
        "quantityAvailable":"223",
        "ram":"6GB",
        "processor":"SnapDragon",
        "storage":"64GB",
        "camera":"10MP"        
    }
  ];
  hidden=true;
  toggle(name, qty, price, details){
    console.log(name.value, qty.value, price.value)
    console.log(details.hidden)
    details.hidden=!details.hidden
    
    
  }
  trackByFn(index:any, item:any){
      return index;
  }
  constructor() { 
    console.log(this.data);
  }
  update(form){
    console.log(form.value)
    
  }
  ngOnInit() {
    this.data.forEach((element)=>{
      console.log(element)
      this.fg=new FormGroup({});
      this.keys=Object.keys(element);
      //this.fg.addControl()
      this.keys.forEach((k)=>{
        this.fg.addControl(k,new FormControl(element[k]))
      })
      console.log(this.fg)
      
      this.forms.push(this.fg)
    })
    console.log(this.forms)
  }

}
