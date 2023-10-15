import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Adress } from 'src/app/models/adress.model';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  addImage = 'http://localhost:9000/auth/addImage';

  registerForm!: FormGroup;
  picture!: any;
  fileToUpload?: any;
  gender!: string;
  firstName!: string;
  lastName!: string;
  email!: string;
  date!: Date;
  phoneNumber!: string;
  password!: string;

  submitted = false;
  adresse:Adress=new Adress()
city:any;
state:any
street_name:any
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private registerService: RegisterService,
    private http: HttpClient
  ) {}

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      title: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      date: ['', Validators.required],
      phoneNumber: [
        '',
        [Validators.required, Validators.minLength(8), Validators.maxLength(8)],
      ],
      password: ['', [Validators.required, Validators.minLength(6)]],
      acceptTerms: [false, Validators.requiredTrue],
      city: ['', Validators.required],
      state: ['', Validators.required],
      street_name: ['', Validators.required],
    });
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid


      console.log("hey")
      this.adresse.city=this.city
      this.adresse.state=this.state
      this.adresse.street_name=this.street_name
      this.goToLogin();
      this.registerService
        .register(
          this.email,
          this.password,
          this.gender,
          this.firstName,
          this.lastName,
          this.date,
          this.phoneNumber,
          this.adresse
        )
        .subscribe(
          (result) => {


          },
          (error) => console.log(error)
        );

  }
  goToLogin() {
    this.router.navigate(['/login']);
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
  image() {
    const formData = new FormData();
    formData.append('file', this.fileToUpload);
    this.http.post(`${this.addImage}/${this.email}`, formData).subscribe(
      (result) => {},
      (error) => console.log(error)
    );
  }
  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.fileToUpload = inputElement.files[0];
    }
  }
}
