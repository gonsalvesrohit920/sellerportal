import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { SignupSellerComponentComponent } from './Components/signup-seller-component/signup-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';


const routes: Routes = [
  {path:"signup",component:SignupSellerComponentComponent},
  {path:"product",component:ProductComponentComponent},
  {path:"",component:LoginSellerComponentComponent}  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
