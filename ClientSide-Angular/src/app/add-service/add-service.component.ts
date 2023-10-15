import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Service } from '../Service.model';
import { ServicesService } from '../Services/services.service';

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.css']
})
export class AddServiceComponent implements OnInit {

service : Service = new Service();

saveService(){
  var id = Number(localStorage.getItem('id'));
  this.serviceS.AddService(this.service , id).subscribe();
  this.router.navigate(['/ListServices']);
}

constructor(private serviceS : ServicesService,private router: Router){}

ngOnInit(){

}

}
