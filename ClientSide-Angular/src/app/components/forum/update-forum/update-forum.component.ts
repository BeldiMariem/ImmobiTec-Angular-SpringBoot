import {Component, OnInit} from '@angular/core';
import {Forum} from "../../../models/forum.model";
import {ForumService} from "../../../services/forum.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-update-forum',
  templateUrl: './update-forum.component.html',
  styleUrls: ['./update-forum.component.css']
})
export class UpdateForumComponent implements OnInit{

  id?:any;
  forum:Forum = new Forum();

  constructor(private forumService:ForumService,
              private route :ActivatedRoute,
              private router: Router) {

  }
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    console.log(this.id);
    this.forumService.getForumById(this.id).subscribe(data=>{
      this.forum=data;
    },
      error => console.log(error));

  }

  onSubmit(){
    this.forumService.updateForum(this.id,this.forum).subscribe(data =>{
      this.goToForumList();
    },
      error => console.log(error));
  }

  goToForumList(){
    this.router.navigate(['/allForums']);
  }
}
