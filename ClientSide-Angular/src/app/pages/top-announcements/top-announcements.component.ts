import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';

@Component({
  selector: 'app-top-announcements',
  templateUrl: './top-announcements.component.html',
  styleUrls: ['./top-announcements.component.css']
})
export class TopAnnouncementsComponent implements OnInit{

  topannouncements : any ;

  constructor(private announcementService: AnnouncementService,private router: Router) {
  }

  getTopAnnouncements(){
    this.announcementService.GetTopAnnouncements().subscribe(data => {
      this.topannouncements = data
    })
  }

  ngOnInit(){
    this.getTopAnnouncements();
     }

}
