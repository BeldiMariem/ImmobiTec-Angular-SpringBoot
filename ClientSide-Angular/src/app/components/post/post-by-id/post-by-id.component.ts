import {Component, OnInit} from '@angular/core';
import {Forum} from "../../../models/forum.model";
import {ForumService} from "../../../services/forum.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../../../services/post.service";
import {Post} from "../../../models/post.model";
import { CommentService } from 'src/app/services/comment.service';
import { CommentP } from 'src/app/models/commentP.model';
import { error } from '@angular/compiler-cli/src/transformers/util';
//import { PostVoteService } from 'src/app/services/post-vote.service';

//import { CommentP } from 'src/app/models/commentP.model';


@Component({
  selector: 'app-post-by-id',
  templateUrl: './post-by-id.component.html',
  styleUrls: ['./post-by-id.component.css', 
            ]
})
export class PostByIdComponent implements OnInit{

  idPost?:any;
  idForum?:any;
  forum?:Forum;
  post:Post=new Post();
  comment :CommentP[] = [];

  newcomment :CommentP = new CommentP();

  constructor(private postService:PostService,
              private route:ActivatedRoute,
              private router:Router,
              private forumService:ForumService,
              private commentService:CommentService) {
  }

  ngOnInit(): void {
    document.getElementById("essentialcard")?.classList.add("show");
    document.getElementById("essentialcard")?.classList.remove("collapse");
    this.idPost= this.route.snapshot.params['id'];
    console.log(this.idPost)

    this.idForum = history.state.myData;
    console.log('** Forum=',this.idForum);

   // console.log("this.route.snapshot.params['state']",this.route.snapshot.params['state']);

    /*this.forumService.getForumById(this.idForum).subscribe(data=>{
      this.forum=data
      },
      error => console.log(error));
*/

    this.postService.getPostById(this.idPost).subscribe(data=>{
      console.log(data);
      //this.post.forum=this.forum;
      //console.log("this.post.forum",this.post.forum);
      this.post=data;
    },
      error => console.log(error));

      this.postService.getCommentsByPost(this.idPost).subscribe(data=>{
        this.comment=data;
      },
      error=>console.log(error));

    document.getElementById("essentialcard")?.classList.remove("collapse");
    document.getElementById("essentialcard")?.classList.add("show");
  }


  saveComment(){

    this.commentService.createComment(this.newcomment,this.idPost).subscribe(data=>{
      this.newcomment=data;
      console.log("success 1");
      location.reload();
      },
      error=>console.log(error));
  }

  onSubmit(){
    this.saveComment();
    console.log("success ");
  }

  updateComment(id:any){

  }

  deleteComment(id:any){
    this.commentService.deleteComment(id).subscribe(data=>{
      location.reload();
    });

  }

}
