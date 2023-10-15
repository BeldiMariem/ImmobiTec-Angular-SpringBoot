import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  role=localStorage.getItem('role')
  co=localStorage.getItem('connect')
  constructor(private router:Router){}
  ngOnInit() {


    }
logout(){
  localStorage.clear()
  window.location.href="login"
}
}
