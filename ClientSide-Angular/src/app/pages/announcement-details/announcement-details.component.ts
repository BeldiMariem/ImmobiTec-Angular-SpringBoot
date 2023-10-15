import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';
import * as L from "leaflet";
import { Rating } from 'src/app/model/Rating.model';

@Component({
  selector: 'app-announcement-details',
  templateUrl: './announcement-details.component.html',
  styleUrls: ['./announcement-details.component.css']
})
export class AnnouncementDetailsComponent implements OnInit , AfterViewInit{
  id_announcement : number = 0;
  announcement ?: any;
  value !: number;
  lat !:any;
  lon !:any;
  calcule !:any;
  Rate : Rating = new Rating();
  ratings : any[] = [];
  co=localStorage.getItem('connect');
 
  @Input() maxStars = 7;
  @Input() rating = 0;
  @Input() ratingg = 0;
  @Output() ratingChange = new EventEmitter<number>();

  stars = new Array(this.maxStars).fill(0);


  verifRating(){
    var id = Number(localStorage.getItem('id'));
    for(let item of this.ratings){
        if(item.announcement.id_announcement == this.value && item.user.id_user == id){
          this.ratingg = item.note;
          return true;
        }
    }
    return false;
  }

  supprimerRating(){
    var id = Number(localStorage.getItem('id'));
    for(let item of this.ratings){
      if(item.announcement.id_announcement == this.value && item.user.id_user == id){
        alert(item.id_Rating);
       this.announcementService.DeleteRating(item.id_Rating).subscribe();
      }
  }
  this.reloadComponent();
  }

  rate(rating: number) {
    this.rating = rating;
    var id = Number(localStorage.getItem('id'));
    this.Rate.note = this.rating;
    this.announcementService.addRating(this.Rate,this.value,id).subscribe();
    alert("Confirmer votre Rating !!");
    this.reloadComponent();
  }

  ratee(rating: number) {
    var id = Number(localStorage.getItem('id'));
    this.ratingg = rating;
    this.Rate.note = this.ratingg;
    for(let item of this.ratings){
      if(item.announcement.id_announcement == this.value && item.user.id_user == id){
        item.note = this.ratingg;
       this.announcementService.updaterating(item,item.id_Rating).subscribe();
       alert("Confirm Update Rating !!");
       this.reloadComponent();
      }
  }
  }
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate([currentRoute]);
    });
  }


  constructor(private announcementService: AnnouncementService, private route: ActivatedRoute,private router: Router) {
  }

  ngAfterViewInit(){
    let marqueur: L.Marker;
    let map = L.map("maCarte").setView([33.892166, 9.561555], 6);
    let map2 = L.map("SecondCarte").setView([33.892166, 9.561555], 6);

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
          attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);


    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map2);


      function onMapClick(e: L.LeafletMouseEvent) {
        let pos = e.latlng;
        addMarker(pos, map2);
        document.querySelector<HTMLInputElement>("#lat1")!.value = pos.lat.toString();
        document.querySelector<HTMLInputElement>("#lon1")!.value = pos.lng.toString();
      }

      map2.on('click', onMapClick);
     
  

      function addMarker(pos: L.LatLngExpression, carte: L.Map) {
        if (marqueur != undefined) {
          carte.removeLayer(marqueur);
        }
        marqueur = L.marker(pos , {
          draggable: true
        });
        marqueur.on("dragend", function(e) {
          pos = e.target.getLatLng() as L.LatLng;
          document.querySelector<HTMLInputElement>("#lat1")!.value = pos.lat.toString();
          document.querySelector<HTMLInputElement>("#lon1")!.value = pos.lng.toString(); 
        });
      
        marqueur.addTo(map2);
    }
    let circle = L.circle([this.announcement.property.x,this.announcement.property.y], {
      color: 'red',
      fillColor: '#f03',
      fillOpacity: 0.5,
      radius: 500
  }).addTo(map);
  }
  calculate(){
    this.lat = document.querySelector<HTMLInputElement>("#lat1")!.value;
    this.lon = document.querySelector<HTMLInputElement>("#lon1")!.value;
    if(this.lat != "" && this.lon != ""){
      this.announcementService.calculationMapBox(this.announcement.property.y,this.announcement.property.x,this.lon,this.lat).subscribe(
        res => {
         this.calcule = res;
        });
    } else{
      alert("Choose a place first !!!");
    }
   
  }
  ngOnInit(){
  
    this.route.params.subscribe(
      param => {
        this.value = param['announcementID']
        });
        this.announcementService.GetAllRatings().subscribe(data =>{
          this.ratings = data
        });
    this.announcementService.GetAnnouncementByID(this.value).subscribe(
      res => {
       this.announcement = res;
      });

  
    }
  }
