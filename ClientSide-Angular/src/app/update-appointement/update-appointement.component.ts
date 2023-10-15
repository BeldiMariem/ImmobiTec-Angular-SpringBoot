import { Component, OnInit } from '@angular/core';
import { AppointementService } from '../appointement.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointements } from '../Appointement.model';

@Component({
  selector: 'app-update-appointement',
  templateUrl: './update-appointement.component.html',
  styleUrls: ['./update-appointement.component.css']
})
export class UpdateAppointementComponent implements OnInit{
  appointements : Appointements = new Appointements();
  value : any;
  updateAppointements(){
    this.appointementsS.UpdateAppointement(this.appointements,this.value).subscribe();
    this.router.navigate(['/ListAppointments']);
      }
    


  constructor(private appointementsS : AppointementService,private route: ActivatedRoute,private router: Router){}

  ngOnInit(){

    this.route.params.subscribe(
      param => {
        this.value = param['id']
        });
    this.appointementsS.GetAppointementByID(this.value).subscribe(data =>{
      this.appointements = data
    });
  }
  formatDate(timestamp: number): string {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

}
