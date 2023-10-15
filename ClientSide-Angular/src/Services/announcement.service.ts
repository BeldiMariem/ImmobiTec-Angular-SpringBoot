import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Announcement } from 'src/app/model/Announcement.model';
import { Appointment } from 'src/app/model/Appointment.model';
import { Property } from 'src/app/model/Property.model';
import { Rating } from 'src/app/model/Rating.model';
import { search } from 'src/app/model/search.model';

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type': 'application/json'
    })
    }
    
  private getAllAnnouncements = 'http://localhost:9000/Announcement/getAllAnnouncements';
  private getAllAppointments = 'http://localhost:9000/Appointment/getAllAppointement';
  private getAllRatings = 'http://localhost:9000/Rating/getAllRatings';
  private getAllAnnouncementsBACk = 'http://localhost:9000/Announcement/getAllAnnouncementsTWO';
  private getAnnouncementByID = 'http://localhost:9000/Announcement/getAnnouncementById';
  private calculateMap = 'http://localhost:9000/map/MapBox';
  private filterURI = 'http://localhost:9000/Announcement/Filter';
  private Listproperties = 'http://localhost:9000/property/getAllProperties';
 private  deleteProperty ='http://localhost:9000/property/deleteProperty';
private closeproperty = 'http://localhost:9000/property/CloseProperty';
private Addproperty = 'http://localhost:9000/property/addProperty';
private updateproperty = 'http://localhost:9000/property/updateProperty';
private getpropertyByID = 'http://localhost:9000/property/getPropertyById';
private TopAnnouncements = 'http://localhost:9000/Announcement/TopAnnouncements';
private deleteImage = 'http://localhost:9000/property/deleteImage';
private addImage = 'http://localhost:9000/property/addImage';
private addAnnouncement = 'http://localhost:9000/Announcement/addAnnouncement';
private updateAnnouncement = 'http://localhost:9000/Announcement/updateAnnouncement';
private AddRating = 'http://localhost:9000/Rating/addRating';
private deleteRating = 'http://localhost:9000/Rating/deleteRating';
private addAppointment = 'http://localhost:9000/Announcement/addAppointement';
private updateAppointment = 'http://localhost:9000/Announcement/updateAppointementProperty';
private deleteAppointment = 'http://localhost:9000/Announcement/DeleteAppointement';
private getAppointmentByProperty = 'http://localhost:9000/Announcement/getAppointementsByProperty';
private getAppointmentByID = 'http://localhost:9000/Announcement/getAAppointmentById';
private updateRating = 'http://localhost:9000/Rating/updateRating';

private payment = 'http://localhost:9000/Paiment/charge';
private addSearch = 'http://localhost:9000/Announcement/addResearch';
private suggestedAnnouncements = 'http://localhost:9000/Announcement/SuggestedAnnouncements';


  /*--------------Get All Announcements--------------*/
GetAllAnnouncements():Observable<any[]>{
  return this.http.get<any[]>(this.getAllAnnouncements,this.httpOptions);
}

GetSuggAnnouncements(id : number):Observable<any[]>{
  return this.http.get<any[]>(`${this.suggestedAnnouncements}/${id}`);
}

GetAllAppointments():Observable<any[]>{
  return this.http.get<any[]>(this.getAllAppointments,this.httpOptions);
}


GetAllAnnouncementsTWO():Observable<any[]>{
  return this.http.get<any[]>(this.getAllAnnouncementsBACk,this.httpOptions);
}
  /*--------------Get All Properties--------------*/
GetAllProperties():Observable<any[]>{
  return this.http.get<any[]>(this.Listproperties,this.httpOptions);
}
  /*--------------Get Announcement by ID--------------*/
GetAnnouncementByID(id : number):Observable<any[]>
{
  return this.http.get<any[]>(`${this.getAnnouncementByID}/${id}`);
}

  /*--------------Get Announcement by ID--------------*/
  GetAppointmentByID(id : number):Observable<any>
  {
    return this.http.get<any>(`${this.getAppointmentByID}/${id}`);
  }

    /*--------------Get Announcement by ID--------------*/
    GetAppointmentByPropertyID(id : number):Observable<any[]>
    {
      return this.http.get<any[]>(`${this.getAppointmentByProperty}/${id}`);
    }

  /*--------------Distance and Duration calculation--------------*/
calculationMapBox(lat : number,lon : number,lat1 : number,lon1 : number):Observable<any>{
  return this.http.get<any>(`${this.calculateMap}/${lat}/${lon}/${lat1}/${lon1}`);
}

  /*--------------Filter--------------*/
FilterAnnouncements(type : any,city : any, maxbudget : any , minbudget : any):Observable<any[]>
{
  return this.http.get<any[]>(`${this.filterURI}?city=${city}&typeAnnouncement=${type}&MaxBudget=${maxbudget}&MinBudget=${minbudget}`);
}

  /*--------------Delete Property--------------*/
  DeleteProperty(id : number):Observable<any>{
    return this.http.delete<any>(`${this.deleteProperty}/${id}`);
  }

  DeleteAppoint(id : number):Observable<any>{
    return this.http.delete<any>(`${this.deleteAppointment}/${id}`);
  }


   /*--------------close Property--------------*/
   closeProperty(id : number):Observable<any>{
    return this.http.put<any>(`${this.closeproperty}/${id}`,this.httpOptions);
  }

  /*--------------add Property--------------*/
  AddProperty(prop : Property , id : number):Observable<Object>{
    return this.http.post(`${this.Addproperty}/${id}`,prop);
  }

   /*--------------add Property--------------*/
   Payment(token : any,id : number):Observable<Object>{
    return this.http.post(`${this.payment}/${id}`,token);
  }

  AddAppoint(appoi : Appointment , iduser : number, id : number):Observable<Object>{
    return this.http.post(`${this.addAppointment}/${iduser}?id=${id}`,appoi);
  }

    /*--------------Update Property--------------*/
    updateProperty(prop : Property , id : number,idAnn : number):Observable<Object>{
      prop.id_property = id;
      return this.http.put(`${this.updateproperty}/${idAnn}`,prop);
    }

    updaterating(rat : Rating , id : number):Observable<Object>{
      rat.id_rating = id;
      return this.http.put(this.updateRating,rat);
    }

    UpdateAppointment(appoint : Appointment , id : number):Observable<Object>{
      appoint.id_appointement = id;
      return this.http.put(this.updateAppointment,appoint);
    }

      /*--------------Get Property by ID--------------*/
GetPropertyByID(id : number):Observable<Property>
{
  return this.http.get<Property>(`${this.getpropertyByID}/${id}`);
}

/*--------------Get Top Announcements--------------*/
GetTopAnnouncements():Observable<any[]> {
return this.http.get<any[]>(`${this.TopAnnouncements}`);
}
 /*--------------Delete Image--------------*/
 DeleteImage(id : number):Observable<any>{
  return this.http.delete<any>(`${this.deleteImage}/${id}`);
}

uploadImage(id : number , img : any):Observable<any>{
  return this.http.post<any>(`${this.addImage}/${id}`,img);
}
  /*--------------add Property--------------*/
  AddAnnouncement(ann : Announcement , id : number):Observable<Object>{
    return this.http.post(`${this.addAnnouncement}/${id}`,ann);
  }

    /*--------------Update Property--------------*/
    UpdateAnnouncement(ann : Announcement , id : number):Observable<Object>{
      ann.id_announcement = id;
      return this.http.put(this.updateAnnouncement,ann);
    }


  /*--------------add Rating--------------*/
  addRating(rat : Rating , idann : number , iduser : number):Observable<Object>{
    return this.http.post(`${this.AddRating}/${idann}/${iduser}`,rat);
  }

  addsearch(rat : search ,  iduser : number):Observable<Object>{
    return this.http.post(`${this.addSearch}/${iduser}`,rat);
  }

    /*--------------Get All Properties--------------*/
GetAllRatings():Observable<any[]>{
  return this.http.get<any[]>(this.getAllRatings,this.httpOptions);
}

  /*--------------Delete Property--------------*/
  DeleteRating(id : number):Observable<any>{
    return this.http.delete<any>(`${this.deleteRating}/${id}`);
  }

}
