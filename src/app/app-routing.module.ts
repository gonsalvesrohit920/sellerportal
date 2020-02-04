import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';
import { AddProductsComponent } from './Components/add-products/add-products.component';
import { ViewComponentComponent } from './Components/view-component/view-component.component';
import { SignupSellerComponentComponent } from './Components/signup-seller-component/signup-seller-component.component';
import { AdminLoginComponent } from './Components/admin-login/admin-login.component';
import { FileUploadComponent } from './Components/file-upload/file-upload.component';
import { SellerDetailsAdminComponent } from './Components/seller-details-admin/seller-details-admin.component';


const routes: Routes = [
  {path: 'signup', component: SignupSellerComponentComponent},
  {path: 'fileupload', component: FileUploadComponent},
  {path: 'product', component: ProductComponentComponent},
  {path: '', component: LoginSellerComponentComponent},
  {path: 'product/AddProduct', component: AddProductsComponent},
  {path: 'view', component: ViewComponentComponent},
  {path: 'admin/login', component: AdminLoginComponent},
  {path: 'admin', component: SellerDetailsAdminComponent},
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
