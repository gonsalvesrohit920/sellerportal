import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginSellerComponentComponent } from './Components/login-seller-component/login-seller-component.component';
import { ProductComponentComponent } from './Components/product-component/product-component.component';
import {CookieService} from 'ngx-cookie-service';
import { SellerServiceService } from './providers/seller-service.service';
import { HttpClientModule } from '@angular/common/http';
import { SellerComponent } from './Components/seller/seller.component';
import { AdminLoginComponent } from './Components/admin-login/admin-login.component';
import { SignupSellerComponentComponent } from './Components/signup-seller-component/signup-seller-component.component';
import { AuthService, FacebookLoginProvider, GoogleLoginProvider, SocialUser, AuthServiceConfig, SocialLoginModule } from 'angularx-social-login';

import { AddProductsComponent } from './Components/add-products/add-products.component';
import { MatTabsModule, MatSnackBarModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSelectModule } from '@angular/material/select'
import {FormsModule} from '@angular/forms'
import {MatGridListModule} from '@angular/material/grid-list'
import { MatFormFieldModule, MatInputModule } from '@angular/material';
import { ViewComponentComponent } from './Components/view-component/view-component.component';
import { SellerDataService } from './providers/seller-data-service/seller-data.service'; ;
import { MatSliderModule } from '@angular/material/slider';
import {MatButtonModule,MatToolbarModule} from '@angular/material';
import {MatSnackBar} from '@angular/material/snack-bar';
import { SellerDetailsAdminComponent } from './Components/seller-details-admin/seller-details-admin.component';
import { OauthComponent } from './Components/oauth/oauth.component';
import { FileUploadComponent } from './Components/file-upload/file-upload.component';
import { ViewOrderHistoryComponent } from './Components/view-order-history/view-order-history.component';

const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('343600838738-bg4nvl5lc1vc5mqmo29cjhdt4uhk5auk.apps.googleusercontent.com')
  }]);

  export function provideConfig() {
    return config;
  }



@NgModule({
  declarations: [
    AppComponent,
    LoginSellerComponentComponent,
    SignupSellerComponentComponent,
    ProductComponentComponent,
    SellerComponent,
    AdminLoginComponent,
    AddProductsComponent,
    ViewComponentComponent,
    OauthComponent,
    FileUploadComponent,
    SellerDetailsAdminComponent,
    ViewOrderHistoryComponent,
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
    MatToolbarModule,
    SocialLoginModule,
    MatSnackBarModule
  ],
  providers: [
    {
    provide: AuthServiceConfig,
    useFactory: provideConfig
  },
  CookieService, SellerServiceService, SellerDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
