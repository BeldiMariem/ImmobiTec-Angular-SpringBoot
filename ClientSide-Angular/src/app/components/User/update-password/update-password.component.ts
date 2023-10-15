import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css'],
})
export class UpdatePasswordComponent implements OnInit {
  password!: string;
  confirmPassword!: string;
  constructor(private http: HttpClient, private router: Router) {}
  private authUrl = 'http://localhost:9000/auth/update_password';
  ngOnInit(): void {}

  update(password: string, confirmPassword: string) {
    if (password == confirmPassword) {
      return this.http.post(this.authUrl, password).subscribe(
        (data) => {
          this.goToLogin();
        },error=>{ this.goToLogin() }
      );
    } else {

      alert('The two passwords do not match');
      return;
    }
  }
  goToLogin() {
    this.router.navigate(['/login']);
  }
}
