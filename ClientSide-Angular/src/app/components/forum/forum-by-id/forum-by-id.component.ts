import {Component, OnInit} from '@angular/core';
import {Forum} from "../../../models/forum.model";
import {ForumService} from "../../../services/forum.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PostService} from "../../../services/post.service";
import {Post} from "../../../models/post.model";
import { PostVoteService } from 'src/app/services/post-vote.service';


@Component({
  selector: 'app-forum-by-id',
  templateUrl: './forum-by-id.component.html',
  styleUrls: ['./forum-by-id.component.css']
})
export class ForumByIdComponent implements OnInit {

  idForum?:any;
  forum:Forum = new Forum();

  post: Post[] = [];
  filtredByLikes:Post[]=[];
  filtredByLikesAndComments:Post[]=[];
  filtredToday:Post[]=[];

 constructor(private forumService:ForumService,
             private route:ActivatedRoute,
             private router :Router,
             private postService:PostService,
             private postvote:PostVoteService) {
 }
  ngOnInit(): void {
   this.idForum = this.route.snapshot.params['id'];
   console.log("idForum",this.idForum);
   this.forumService.getForumById(this.idForum).subscribe(data=>{
     this.forum=data
   },
     error => console.log(error));

   this.forumService.getAllPosts(this.idForum).subscribe(data=>{
       console.log(data);
     this.post=data;
     this.filtredByLikes=data;
   },
     error => console.log(error));

     document.getElementById("allposts")?.classList.add("active");
     document.getElementById("mostliked")?.classList.remove("active");
     document.getElementById("sortToday")?.classList.remove("active");
     document.getElementById("sortAll")?.classList.remove("active");
  }

  addPost(){
    console.log("*** Forum idForum ",this.idForum);
    this.router.navigate(['/addPost',{state: this.idForum}]);
  }

  updatePost(id: any){
    this.router.navigate(['updatePost',id, { state:  this.idForum }]);
  }

  deletePost(id: any){
    this.postService.deletePost(id).subscribe(data =>{
      console.log(data);
      location.reload();
    });
  }

  postById(id:any){
    this.router.navigate(['postById',id,{state: this.idForum}])
  }

  like(id:any){
    this.postvote.like(id).subscribe(d=> {console.log(d)});
    location.reload();
    //console.log("succes ",id);
   }

  dislike(id:any){
    this.postvote.dislike(id).subscribe(d=> {console.log(d)});
    location.reload();
  }

  sortByLikesDescending(posts: Post[]) {
    posts.sort((post1, post2) => {
      if (typeof post1.likes !== 'undefined' && typeof post2.likes !== 'undefined' && post1.likes > post2.likes) {
        return -1;
      } else if (typeof post1.likes !== 'undefined' && typeof post2.likes !== 'undefined' && post1.likes < post2.likes) {
        return 1;
      } else {
        return 0;
      }
    });
  }

  onMostLikedPostsClicked() {
    this.sortByLikesDescending(this.post);
    document.getElementById("allposts")?.classList.remove("active");
    document.getElementById("sortToday")?.classList.remove("active");
    document.getElementById("sortAll")?.classList.remove("active");
    document.getElementById("mostliked")?.classList.add("active");
    //console.log(sortByLikesDescending(this.filtredByLikes));
  }

   sortByCommentsAndLikesDescendingToday(posts: Post[]) {
    const today = new Date().toLocaleDateString();

    console.log("today",today);
  
    const filteredPosts = posts.filter(post => {
      const postDate = post.date_post ? new Date(post.date_post) : null;
      return postDate && postDate.toLocaleDateString() === today;
    });
  
    
    filteredPosts.sort((post1, post2) => {

      const post1Comments = post1.comments ? post1.comments.length : 0;
      const post2Comments = post2.comments ? post2.comments.length : 0;

      const post1Score = (post1.likes || 0) + 0;
      const post2Score = (post2.likes || 0) + 0;
  
      if (post1Score > post2Score) {
        return -1;
      } else if (post1Score < post2Score) {
        return 1;
      } else {
        return 0;
      }
    });
  
    return filteredPosts;
  }

  sortToday(){
    this.filtredToday=this.sortByCommentsAndLikesDescendingToday(this.post);
    this.post=this.filtredToday;
    
    document.getElementById("allposts")?.classList.remove("active");
    document.getElementById("mostliked")?.classList.remove("active");
    document.getElementById("sortAll")?.classList.remove("active");
    document.getElementById("sortToday")?.classList.add("active");
  }

  sortByCommentsAndLikesDescending(posts: Post[]) {
    const filteredPosts = posts;
  
    console.log("posts",posts);
    filteredPosts.sort((post1, post2) => {
      const post1Comments = post1.comments?.length || 0;
      const post2Comments = post2.comments?.length || 0;
      
      console.log("post1",post1);
      console.log("post2",post2);

      const post1Likes = post1.likes || 0;
      const post2Likes = post2.likes || 0;
  
      const post1Score = post1Likes + post1Comments;
      const post2Score = post2Likes + post2Comments;
  
      if (post1Score > post2Score) {
        return -1;
      } else if (post1Score < post2Score) {
        return 1;
      } else {
        return 0;
      }
    });
    console.log("filteredPosts",filteredPosts);
    return filteredPosts;
  }

  sortAll(){
    this.filtredByLikesAndComments=this.sortByCommentsAndLikesDescending(this.post);
    this.post=this.filtredByLikesAndComments;
    console.log("this.filtredByLikesAndComments",this.filtredByLikesAndComments);
    document.getElementById("allposts")?.classList.remove("active");
    document.getElementById("mostliked")?.classList.remove("active");
    document.getElementById("sortToday")?.classList.remove("active");
    document.getElementById("sortAll")?.classList.add("active");
  }

}
