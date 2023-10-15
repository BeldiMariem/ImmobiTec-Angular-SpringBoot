import { AfterViewInit, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as L from 'leaflet';
import { AnnouncementService } from 'src/Services/announcement.service';
import { Adress } from 'src/app/model/Adress.model';
import { Property } from 'src/app/model/Property.model';

@Component({
  selector: 'app-add-property',
  templateUrl: './add-property.component.html',
  styleUrls: ['./add-property.component.css']
})
export class AddPropertyComponent implements OnInit, AfterViewInit{


  property:Property = new Property();
  adress : Adress = new Adress();
  closed : any = "";
  lat : any ;
  lon : any;
  constructor(private announcementService: AnnouncementService,
    private router: Router) {}


    saveProperty(){
      var id = Number(localStorage.getItem('id'));
     if(this.closed == "closed"){
      this.property.closed = true;
     }
      if(this.closed == "opened"){
      this.property.closed = false;
     }
     this.lat = document.querySelector<HTMLInputElement>("#lat1")!.value;
     this.lon = document.querySelector<HTMLInputElement>("#lon1")!.value;
     this.property.adresse = this.adress;
     this.property.x = this.lat;
     this.property.y = this.lon;
      this.announcementService.AddProperty(this.property,id).subscribe();
      alert("Property Added Successfully !!!");
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

    ngOnInit(): void {

    }

}
