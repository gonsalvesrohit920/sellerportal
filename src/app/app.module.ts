import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';
import {CookieService} from 'ngx-cookie-service'
import { SellerServiceService } from './providers/seller-service.service';
import { HttpClientModule } from '@angular/common/http';
import { AddProductsComponent } from './Components/add-products/add-products.component';
import { MatTabsModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSelectModule } from '@angular/material/select'
import {FormsModule} from '@angular/forms'
import {MatGridListModule} from '@angular/material/grid-list'
import { MatFormFieldModule, MatInputModule } from '@angular/material';
import { ViewComponentComponent } from './Components/view-component/view-component.component';
import { SellerDataService } from './providers/seller-data-service/seller-data.service';;
import { MatSliderModule } from '@angular/material/slider';
import {MatButtonModule, MatToolbarModule} from '@angular/material';
@NgModule({
  declarations: [
    AppComponent,
    LoginSellerComponentComponent,
    ProductComponentComponent,
    AddProductsComponent,
    ViewComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatSelectModule,
    FormsModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatToolbarModule
  ],
  providers: [CookieService,SellerServiceService,SellerDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
