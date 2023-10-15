import {Component, OnInit} from '@angular/core';
import {Forum} from "../../../models/forum.model";
import {ForumService} from "../../../services/forum.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forum-list',
  templateUrl: './forum-list.component.html',
  styleUrls: ['./forum-list.component.css']
})
export class ForumListComponent implements OnInit{
  forums: Forum[] = [];

  lengthForums?:number;
  constructor(private forumService:ForumService,
              private router:Router) { }

  ngOnInit(): void {
    this.getAllForums();
    this.lengthForums=this.forums.length;
  }

  private getAllForums(){
    this.forumService.getAllForums().subscribe(data =>{
        this.forums=data;
    });
  }

  forumById(id:any){
    this.router.navigate(['forumById',id]);
  }

  updateForum(id: any){
    this.router.navigate(['updateForum',id]);
  }

  deleteForum(id: any){
    this.forumService.deleteForum(id).subscribe(data =>{
      console.log(data);
      this.getAllForums();
    });
  }


     
}
