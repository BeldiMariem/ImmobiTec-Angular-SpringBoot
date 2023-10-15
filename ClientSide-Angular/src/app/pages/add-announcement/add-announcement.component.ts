import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';
import { Announcement } from 'src/app/model/Announcement.model';

@Component({
  selector: 'app-add-announcement',
  templateUrl: './add-announcement.component.html',
  styleUrls: ['./add-announcement.component.css']
})
export class AddAnnouncementComponent  implements OnInit{

announcement : Announcement = new Announcement();
id : any;
  constructor(private announcementService: AnnouncementService,
    private router: Router,private route: ActivatedRoute) {}


    saveAnnouncement(){
      this.announcementService.AddAnnouncement(this.announcement , this.id).subscribe();
      alert("Announcement Added Successfully !!!");
      this.router.navigate(['/ListProperties']);
    }

ngOnInit(){
  this.route.params.subscribe(
    param => {
      this.id = param['propertyID']
      });

}


}
