import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AnnouncementService } from 'src/Services/announcement.service';

@Component({
  selector: 'app-list-images-property',
  templateUrl: './list-images-property.component.html',
  styleUrls: ['./list-images-property.component.css']
})
export class ListImagesPropertyComponent implements OnInit {
  property : any;
  id : any;
  fileToUpload?: File;

constructor(private announcementService: AnnouncementService,private router: Router,private route :ActivatedRoute){
}

deleteImage(id : number){
  this.announcementService.DeleteImage(id).subscribe();
 this.reloadComponent();
}

onUpload() {
  if (!this.fileToUpload) {
    alert('No file selected please upload an image first !!');
    return;
  }

  const formData = new FormData();
  formData.append('file', this.fileToUpload);

  this.announcementService.uploadImage(this.id , formData).subscribe();
  alert("Image Added Successfully !!!");

  this.reloadComponent();
}

onFileSelected(event: Event) {
  const inputElement = event.target as HTMLInputElement;
  if (inputElement.files && inputElement.files.length > 0) {
    this.fileToUpload = inputElement.files[0];
  }
}


reloadComponent(): void {
  const currentRoute = this.router.url.split('?')[0];
  this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.router.navigate([currentRoute]);
  });
}
  ngOnInit(){
    this.id = this.route.snapshot.params['propertyID'];
    this.announcementService.GetPropertyByID(this.id).subscribe(data =>{
      this.property = data
    })

  }
}
