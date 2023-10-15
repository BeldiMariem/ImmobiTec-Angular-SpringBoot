import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as L from 'leaflet';
import { AnnouncementService } from 'src/Services/announcement.service';
import { Adress } from 'src/app/model/Adress.model';
import { Property } from 'src/app/model/Property.model';

@Component({
  selector: 'app-update-property',
  templateUrl: './update-property.component.html',
  styleUrls: ['./update-property.component.css']
})
export class UpdatePropertyComponent implements OnInit , AfterViewInit{

  id?:any;
  property : Property = new Property;
  closed : any ="";
  closedHtml : any ="";
  lat : any ;
  lon : any;
  announcements : any;
  announcement : any;

  constructor(private announcementService: AnnouncementService,private router: Router,
    private route :ActivatedRoute) {
  }

  updateProperty(){
    if(this.closed == "closed"){
      this.property.closed = true;
     }
      if(this.closed == "opened"){
      this.property.closed = false;
     }
     this.lat = document.querySelector<HTMLInputElement>("#lat1")!.value;
     this.lon = document.querySelector<HTMLInputElement>("#lon1")!.value;

     this.property.x = this.lat;
     this.property.y = this.lon;
    
    this.announcementService.updateProperty(this.property , this.id , this.announcement).subscribe();
    alert("Property Updated Successfully !!!");

    this.router.navigate(['/ListProperties']);
  }


  ngAfterViewInit(){
    let marqueur: L.Marker;

    let map2 = L.map("SecondCarte").setView([33.892166, 9.561555], 6);

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
  }

  ngOnInit(){
    this.id = this.route.snapshot.params['propertyID'];
    this.announcementService.GetPropertyByID(this.id).subscribe(data =>{
      this.property = data
      
      if(this.property.closed == true){
        this.closedHtml = "closed";
      }
      if(this.property.closed == false){
        this.closedHtml = "opened";
      }
    });
this.announcementService.GetAllAnnouncementsTWO().subscribe(data =>{
  this.announcements = data;
  for(let item of this.announcements){
    if(item.property.id_property == this.id){
        this.announcement = item.id_announcement;
    }
  }
})
  }
}
