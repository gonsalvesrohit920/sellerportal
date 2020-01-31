import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { SignupSellerComponentComponent } from './Components/signup-seller-component/signup-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';
import {CookieService} from 'ngx-cookie-service'
import { SellerServiceService } from './providers/seller-service.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginSellerComponentComponent,
    SignupSellerComponentComponent,
    ProductComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [CookieService,SellerServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
