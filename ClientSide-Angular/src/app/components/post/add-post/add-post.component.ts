import {Component, OnInit, Input} from '@angular/core';
import {Post} from "../../../models/post.model";
import {PostService} from "../../../services/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit{
  @Input() forumId?: number;
  
  post:Post=new Post();
  
  
  constructor(private postService:PostService,
              private router: Router,
              private route:ActivatedRoute) {

  }
  ngOnInit(): void {
    //console.log("********* Post history =",history.state.value);
    //console.log ("test",this.route.snapshot.params['state']);
    //this.id=this.route.snapshot.params['state'];
    //console.log("id =",this.id);
    console.log("forumId",this.forumId);
  }

  savePost(){
    if(this.forumId){
    this.postService.createPost(this.post,this.forumId).subscribe(data=>{
      this.router.navigate(['forumById',this.forumId]);
    });
  }else
  console.log("ERREUUUUUUUUR");
  
  }

  goToPostList(){
    this.router.navigate(['forumById',this.forumId]);
  }

  onSubmit(){
    this.savePost();
    console.log("success 2");
  }

}
