import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';
import { Appointment } from 'src/app/model/Appointment.model';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css']
})
export class UpdateAppointmentComponent implements OnInit{

id :any;
date : string = "";
formattedDate:any;
appointment : Appointment = new Appointment();
message : any;
constructor(private announcementService: AnnouncementService,private router: Router,
  private route :ActivatedRoute) {
}
updateAppointment(){
    this.appointment.date_appointement = this.date;
    this.announcementService.UpdateAppointment(this.appointment , this.id).subscribe(data =>{
      this.message = data
      if(this.message == null){
        alert("cannot update this Appointment at this date !!");
        this.reloadComponent();
      }else{
        alert("Appointment Updated successfully !!");
        this.router.navigate(['/MyAppointment']);
      }
    });
 
  
}
reloadComponent(): void {
  const currentRoute = this.router.url.split('?')[0];
  this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.router.navigate([currentRoute]);
  });
}
  ngOnInit(): void {
     this.route.params.subscribe(
      param => {
        this.id = param['appointmentId']
        });

        this.announcementService.GetAppointmentByID(this.id).subscribe(data =>{
          this.appointment = data;
          const date = new Date(this.appointment.date_appointement);

          const year = date.getFullYear().toString(); // extract last two digits of the year
          const month = (date.getMonth() + 1).toString().padStart(2, '0'); // add leading zero if needed
          const day = date.getDate().toString().padStart(2, '0'); // add leading zero if needed
          const hour = date.getHours().toString().padStart(2, '0'); // add leading zero if needed
          const minute = date.getMinutes().toString().padStart(2, '0'); // add leading zero if needed
      
          this.date = `${year}-${month}-${day}T${hour}:${minute}`;
          console.log(this.date);
        });

      
  }
}
