import {Forum} from "./forum.model";
import {CommentP} from "./commentP.model";

export class Post {
 id_post?:number;

 description ?:string;

 likes ?:number;

 dislikes?:number ;
 Picture ?:string;
 boosted?:boolean;
 date_post?:Date;
 
 //forum?:Forum;
 
 comments?:CommentP[];

 //user?:number;

}
