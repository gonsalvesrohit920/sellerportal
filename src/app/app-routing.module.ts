import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { SignupSellerComponentComponent } from './Components/signup-seller-component/signup-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';
import { SellerDetailsAdminComponent } from './Components/seller-details-admin/seller-details-admin.component';


const routes: Routes = [
  {path: 'signup', component: SignupSellerComponentComponent},
  {path: 'product', component: ProductComponentComponent},
  {path: '', component: LoginSellerComponentComponent},
  {path: 'admin', component: SellerDetailsAdminComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
