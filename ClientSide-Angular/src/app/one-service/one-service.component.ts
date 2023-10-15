import { Component, OnInit } from '@angular/core';
import { Service } from '../Service.model';
import { ActivatedRoute } from '@angular/router';
import { ServicesService } from '../Services/services.service';

@Component({
  selector: 'app-one-service',
  templateUrl: './one-service.component.html',
  styleUrls: ['./one-service.component.css']
})
export class OneServiceComponent implements OnInit{
service! : Service;

constructor(private route:ActivatedRoute,private serviceService:ServicesService){}

ngOnInit(){
  const id = this.route.snapshot.paramMap.get('Serviceid');
  if(id !== null){
    const Serviceid = +id;
    this.serviceService.getServiceByID(Serviceid).subscribe((service) => {
      this.service=service;
      console.log('service data:',service)
    });

  }
  
}

}
