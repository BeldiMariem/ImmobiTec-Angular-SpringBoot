import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CaptchaService } from 'src/app/Services/captcha.service';


interface CaptchaSettings {
  captcha: string;
  hiddenCaptcha: string;
  realCaptcha: string;
}


@Component({
  selector: 'app-captcha',
  templateUrl: './captcha.component.html',
  styleUrls: ['./captcha.component.css']
})
export class CaptchaComponent {
  

  constructor(private captchaService: CaptchaService) {}


}

