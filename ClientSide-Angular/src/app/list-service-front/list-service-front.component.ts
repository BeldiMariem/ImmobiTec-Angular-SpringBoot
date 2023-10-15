import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../Services/services.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-service-front',
  templateUrl: './list-service-front.component.html',
  styleUrls: ['./list-service-front.component.css']
})
export class ListServiceFrontComponent implements OnInit{
  services : any;
  searchtext:any;
  bestService :any;
  constructor(private ServicesService: ServicesService,private router: Router) {
  }
  GetAllServices(){
    this.ServicesService.GetAllServices().subscribe(data =>{
      this.services = data
    });
  }
  
  likeService(idService : number) {
    var id = Number(localStorage.getItem('id'));

    this.ServicesService.LikeService(idService,id).subscribe((service) => {
      // Replace `userId` with the ID of the current user
      // Handle the updated service data or UI as needed
    });
  }
  dislikeService(idService :number){
    var id = Number(localStorage.getItem('id'));

    this.ServicesService.DislikeService(idService,id).subscribe((service)=>{

    });
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }


  ngOnInit(){
    this.GetAllServices();
      }

}
