import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';

@Component({
  selector: 'app-my-appointments',
  templateUrl: './my-appointments.component.html',
  styleUrls: ['./my-appointments.component.css']
})
export class MyAppointmentsComponent implements OnInit{

  message : string = "";
  dataSource = new MatTableDataSource;
  announcements : any;
  displayedColumns: string[] = ['Date','Action'];
  @ViewChild(MatPaginator) paginator?: MatPaginator;

  constructor(private announcementService: AnnouncementService,private router: Router) {
  }

  deleteAppointment(id : number){
    alert("Are you sure you want to delete this appointment !!!");
    this.announcementService.DeleteAppoint(id).subscribe(data =>{
    });
    alert("Appointment Deleted Successfully !!!");
    this.reloadComponent();

  }

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }

  ngOnInit(): void {
    var id = Number(localStorage.getItem('id'));
    this.announcementService.GetAllAppointments().subscribe(data =>{
      for(let item of data){
        if(item.user.id_user == id){
          this.dataSource.data.push(item);
        }
      }
      this.dataSource.paginator = this.paginator!;
    });
  }
}
