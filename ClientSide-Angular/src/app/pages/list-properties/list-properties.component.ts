import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';

@Component({
  selector: 'app-list-properties',
  templateUrl: './list-properties.component.html',
  styleUrls: ['./list-properties.component.css']
})
export class ListOfPropertiesComponent implements OnInit{

  dataSource = new MatTableDataSource;
  announcements : any;
  properties : any;
  displayedColumns: string[] = ['Adresse','Description','Type','Close','Action'];
  @ViewChild(MatPaginator) paginator?: MatPaginator;

  constructor(private announcementService: AnnouncementService,private router: Router) {
  }
  
  deleteProperty(id : number){
    this.announcementService.DeleteProperty(id).subscribe();
    this.getAllProperties();
   this.reloadComponent();
  }

verifProperty(id : number){
  for(let item of this.announcements){
    if(item.property.id_property == id){
      return true;
    }
  }
  return false;
}

  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }


  closeProperty(id : number){
    this.announcementService.closeProperty(id).subscribe();
    this.getAllProperties();
   this.reloadComponent();
  }

  getAllProperties(){
    var id = Number(localStorage.getItem('id'));
    this.announcementService.GetAllProperties()
    .subscribe(data => {
      for(let item of data){
        if(item.user.id_user == id){
          this.dataSource.data.push(item);
        }
      }
      this.dataSource.paginator = this.paginator!;
    });
  }

  ngOnInit(){
    this.announcementService.GetAllAnnouncementsTWO().subscribe(data =>{
      this.announcements = data
    });
 this.getAllProperties();
  }
}
