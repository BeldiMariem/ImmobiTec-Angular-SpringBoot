import { Component, OnInit } from '@angular/core';
import { AnnouncementService } from 'src/Services/announcement.service';

@Component({
  selector: 'app-suggested',
  templateUrl: './suggested.component.html',
  styleUrls: ['./suggested.component.css']
})
export class SuggestedComponent implements OnInit{
  suggested : any;


  constructor(private announcementService: AnnouncementService) {
  }

  ngOnInit(): void {
    var id = Number(localStorage.getItem('id'));
    this.announcementService.GetSuggAnnouncements(id).subscribe(dataa =>{
      this.suggested = dataa
    });
  }
}
