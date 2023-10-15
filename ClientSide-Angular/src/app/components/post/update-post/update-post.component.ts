import {Component, OnInit} from '@angular/core';
import {Post} from "../../../models/post.model";
import {PostService} from "../../../services/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ForumService} from "../../../services/forum.service";
import {Forum} from "../../../models/forum.model";


@Component({
  selector: 'app-update-post',
  templateUrl: './update-post.component.html',
  styleUrls: ['./update-post.component.css']
})
export class UpdatePostComponent implements OnInit{

  idPost?:any;
  idForum?:any;
  post:Post=new Post();

  forum?:any;
  constructor(private postService:PostService,
              private route:ActivatedRoute,
              private router:Router,
              private forumService:ForumService) {
  }
  ngOnInit(): void {
    this.idForum= this.route.snapshot.params['state'];
    console.log('** Forum=',this.idForum) ;
    this.idPost=this.route.snapshot.params['id'];
    console.log("**ID POST ", this.idPost);

    this.forumService.getForumById(this.idForum).subscribe(data=>{

        this.forum=data;
        console.log("data",data);
        console.log("***-this.forum",this.forum);

       // this.post.forum=data;

      },
      error => console.log(error));

    //console.log("***-this.forum",this.forum);

    this.postService.getPostById(this.idPost).subscribe(data => {
      this.post = data;
      },
      error => console.log(error));
  }

  onSubmit(){
    this.postService.updatePost(this.idForum,this.post).subscribe(data=>{
      this.goToPostList();
    },
      error => console.log(error));
  }

  goToPostList(){
    this.router.navigate(['forumById',this.idForum]);
  }
}
