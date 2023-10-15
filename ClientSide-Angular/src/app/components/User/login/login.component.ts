import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';

import { SocialAuthService, GoogleLoginProvider,SocialUser } from '@abacritt/angularx-social-login';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { AuthServiceService } from 'src/app/services/auth-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { from } from 'rxjs/internal/observable/from';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email!: string;
  password!: string;
  id!: number;
  errorMessage = 'Invalid Credentials';
  successMessage!: string;
  invalidLogin = false;
  loginSuccess = false;
  user: User = new User();
  accessToken=""
  useer!: SocialUser;
  loggedIn!: boolean;
  token:any
  tokenGoogle:any

  constructor(
    private authServic: AuthServiceService,
    private router: Router,
    private authService: SocialAuthService,
    private http:HttpClient,
    private route:ActivatedRoute
  ) {}
  private isReloaded: boolean = false;





  ngOnInit(): void {

    this.authServic.getUserByEmail(this.route.snapshot.queryParamMap.get('email')!,this.route.snapshot.queryParamMap.get('token')!).subscribe((data) => {
      localStorage.setItem('id', data.id_user!.toString()!);
      localStorage.setItem('email', data.email!);
      localStorage.setItem('role', data.role_name!);
      localStorage.setItem('name', data.name!);
      localStorage.setItem('lastname',data.lastname!);
      localStorage.setItem('password',data.password!);
      localStorage.setItem('phoneNumber',data.phoneNumber!.toString()!)
      localStorage.setItem('birthday', data.birthday!.toString()!)
      localStorage.setItem('city', data.adresse!.city!)
      localStorage.setItem('token',this.route.snapshot.queryParamMap.get('token')!)!
      localStorage.setItem('connect', 'true')
      var id = Number(localStorage.getItem('id'));
      window.location.href="http://localhost:4200/userAccount"

    });


  this.authService.authState.subscribe((u:any) => {
    this.useer = u;
    this.loggedIn = (u != null);
  }

  );
}

  getAccessToken(): void {
    this.authService.getAccessToken(GoogleLoginProvider.PROVIDER_ID).then((accessToken:any) => this.accessToken = accessToken);
  }

  signOut(): void {
    this.authService.signOut();
  }
  handleLogin() {
    this.authServic.login(this.email, this.password,).subscribe(
      (l:any) => {
        this.currentUser(this.email,l['token']);
      },
      (error:any) => alert(error.error.message)
    );
  }
  goToAccount() {
    this.router.navigate(['/home']);
    this.router.navigate(['/userAccount']);
  }

  currentUser(email: string, token:any) {
    this.authServic.getUserByEmail(email,token).subscribe((data) => {
      this.id = data.id_user!;
      console.log(data.birthday)
      localStorage.setItem('id', this.id.toString()!);
      localStorage.setItem('token', token)
      localStorage.setItem('email', data.email!);
      localStorage.setItem('role', data.role_name!);
      localStorage.setItem('name', data.name!);
      localStorage.setItem('lastname',data.lastname!);
      localStorage.setItem('password',data.password!);
      localStorage.setItem('phoneNumber',data.phoneNumber!.toString()!)
      localStorage.setItem('birthday', data.birthday!.toString()!)
      localStorage.setItem('city', data.adresse!.city!)
      localStorage.setItem('connect', 'true')

      window.location.href="http://localhost:4200/userAccount"

      var id = Number(localStorage.getItem('id'));
    });
  }
  google()
  {
window.location.href="http://localhost:9000/oauth2/authorization/google";


  }
}
