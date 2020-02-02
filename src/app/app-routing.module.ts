import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';
import { AddProductsComponent } from './Components/add-products/add-products.component';
import { ViewComponentComponent } from './Components/view-component/view-component.component';
import { SignupSellerComponentComponent } from './Components/signup-seller-component/signup-seller-component.component';


const routes: Routes = [
  {path: 'signup', component: SignupSellerComponentComponent},
  {path: 'product', component: ProductComponentComponent},
  {path: '', component: LoginSellerComponentComponent},
  {path: 'product/AddProduct', component: AddProductsComponent},
  {path: 'view', component: ViewComponentComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
