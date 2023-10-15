import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Service } from '../Service.model';
import { ServicesService } from '../Services/services.service';

@Component({
  selector: 'app-update-service',
  templateUrl: './update-service.component.html',
  styleUrls: ['./update-service.component.css']
})
export class UpdateServiceComponent implements OnInit{

  service : Service = new Service();
  value : any;

  updateService(){
this.serviceS.UpdateService(this.service,this.value).subscribe();
this.router.navigate(['/ListServices']);
  }

  constructor(private serviceS : ServicesService,private route: ActivatedRoute,private router: Router){}

  ngOnInit(){

    this.route.params.subscribe(
      param => {
        this.value = param['id']
        });
    this.serviceS.getServiceByID(this.value).subscribe(data =>{
      this.service = data
    });
  }

}
