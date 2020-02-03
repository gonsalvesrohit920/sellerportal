import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService, FacebookLoginProvider, GoogleLoginProvider, SocialUser, SocialLoginModule } from 'angularx-social-login';
@Component({
  selector: 'app-oauth',
  templateUrl: './oauth.component.html',
  styleUrls: ['./oauth.component.css'],
  providers: [SocialLoginModule, AuthService]
})
export class OauthComponent implements OnInit {


  signinForm: FormGroup;
  user: SocialUser;
  loggedIn: boolean;
  constructor(private fb: FormBuilder, private authService: AuthService) { }
  ngOnInit() {
    this.signinForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
      console.log(this.user);
    });
  }
  signInWithFB(): void {
    this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
  }
  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID);
  }
  signOut(): void {
    this.authService.signOut();
  }

}
