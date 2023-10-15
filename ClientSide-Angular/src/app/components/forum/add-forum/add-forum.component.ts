import {Component, OnInit} from '@angular/core';
import {Forum} from "../../../models/forum.model";
import {ForumService} from "../../../services/forum.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-forum',
  templateUrl: './add-forum.component.html',
  styleUrls: ['./add-forum.component.css']
})
export class AddForumComponent implements OnInit{

  forum:Forum = new Forum();
  constructor(private forumService: ForumService,
              private router: Router) {
  }

  ngOnInit(): void {

  }

  saveForum(){
    this.forumService.createForum(this.forum).subscribe(data=>{
      console.log(data);
      console.log("success 1");
      this.goToForumList();
    },
      error => console.log(error));
  }

  goToForumList(){
    this.router.navigate(['/allForums']);
  }

  onSubmit(){
    console.log(this.forum);
    this.saveForum();
    console.log("success 2");
  }
}
