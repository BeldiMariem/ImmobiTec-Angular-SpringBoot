import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointementService } from '../appointement.service';

@Component({
  selector: 'app-list-appointement',
  templateUrl: './list-appointement.component.html',
  styleUrls: ['./list-appointement.component.css']
})
export class ListAppointementComponent implements OnInit{

  appointements : any;
  constructor(private AppointementService :AppointementService ,private router: Router) {
  }
  
  GetAllAppointements(){
    this.AppointementService.GetAllAppointements().subscribe(data =>{
      this.appointements = data
    });
  }
  pdf(){
    console.log('PDF function called');
    this.AppointementService.PDF().subscribe(response => {
      const blob = new Blob([response], { type: 'application/pdf' });
      const url = URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = 'appointements.pdf';
      link.click();
      URL.revokeObjectURL(url);
    }, error => {
      console.log(error);
    });
  }
  deleteAppointement(id : number){
    this.AppointementService.DeleteAppointements(id).subscribe();
    this.GetAllAppointements;
   this.reloadComponent();
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }
  ngOnInit(){
    this.GetAllAppointements();

}
}
