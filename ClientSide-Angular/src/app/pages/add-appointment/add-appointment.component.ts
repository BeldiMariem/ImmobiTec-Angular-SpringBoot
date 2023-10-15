import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';
import { Appointment } from 'src/app/model/Appointment.model';
import {Location} from '@angular/common';

@Component({
  selector: 'app-add-appointment',
  templateUrl: './add-appointment.component.html',
  styleUrls: ['./add-appointment.component.css']
})
export class AddAppointmentComponent implements OnInit {

appointment : Appointment = new Appointment();
value :any;
message : any;

  saveAppointment(){
    var id = Number(localStorage.getItem('id'));
    this.announcementService.AddAppoint(this.appointment,id,this.value).subscribe(data =>{
      this.message = data;
      if(this.message == null){
        alert("cannot add this Appointment at this date !!");
        this.reloadComponent();
      }else{
        alert("Appointment added successfully !!");
        this.router.navigate(['/ListAnnouncements']);
      }
    });
   
  }

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  backClicked() {
    this._location.back();
  }

constructor(private announcementService: AnnouncementService,
  private router: Router,private route: ActivatedRoute,private _location: Location){
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      param => {
        this.value = param['propertyID']
        });
  }
}
