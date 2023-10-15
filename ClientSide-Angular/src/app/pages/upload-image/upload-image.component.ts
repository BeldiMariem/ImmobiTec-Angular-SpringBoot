import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DormServiceService } from 'src/app/services/dorm-service.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit{
  id : any;
  fileToUpload?: File;

  constructor(private dormService : DormServiceService ,private router: Router,private route :ActivatedRoute){};

  onUpload() {
    if (!this.fileToUpload) {
      alert('No file selected please upload an image first !!');
      return;
    }
  
    const formData = new FormData();
    formData.append('file', this.fileToUpload);
  
    this.dormService.uploadImage(this.id , formData).subscribe();
    this.reloadComponent();
  }
  
  reloadComponent(): void {
    const currentRoute = this.router.url.split('?')[0];
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this.router.navigate(['/dormsback']);
      
    });
  }

  onFileSelected(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files && inputElement.files.length > 0) {
      this.fileToUpload = inputElement.files[0];
    }
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['roomID'];
  }
}
