import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
@Component({
  selector: 'app-appointments-by-property',
  templateUrl: './appointments-by-property.component.html',
  styleUrls: ['./appointments-by-property.component.css']
})
export class AppointmentsByPropertyComponent implements OnInit{

value : any;
dataSource = new MatTableDataSource;
announcements : any;
message : string = "";
displayedColumns: string[] = ['Date','UserName','Phone','Action'];
@ViewChild(MatPaginator) paginator?: MatPaginator;

  constructor(private announcementService: AnnouncementService,
    private router: Router,private route: ActivatedRoute) {}

    deleteAppointment(id : number){
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
  this.route.params.subscribe(
    param => {
      this.value = param['propertyID']
      });

      this.announcementService.GetAppointmentByPropertyID(this.value).subscribe(data =>{
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator!;
      });
}

}
