import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../Services/services.service';
import { Service } from '../Service.model';

@Component({
  selector: 'app-list-service',
  templateUrl: './list-service.component.html',
  styleUrls: ['./list-service.component.css']
})
export class ListServiceComponent implements OnInit{

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

  GetBestService(){
    this.ServicesService.BestService().subscribe(data =>{
      this.bestService = data
    });
  }


  deleteService(id : number){
    this.ServicesService.DeleteService(id).subscribe();
    this.GetAllServices();
   this.reloadComponent();
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
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

  ngOnInit(){
this.GetAllServices();
  }
}
