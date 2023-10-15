import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ListPropertiesComponent } from './pages/list-Announcements/list-properties.component';
import { ListOfPropertiesComponent } from './pages/list-properties/list-properties.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule  } from '@angular/common/http';
import { ListProductComponent } from './list-product/list-product.component';
import { AddProductComponent } from './add-product/add-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { ListCardComponent } from './list-card/list-card.component';
import { AddCardComponent } from './add-card/add-card.component';
import { UpdateCardsComponent } from './update-cards/update-cards.component';
import { ListOrderComponent } from './list-order/list-order.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { ViewProductCardComponent } from './view-product-card/view-product-card.component';
import { DiscountComponent } from './discount/discount.component';
import { PromoComponent } from './promo/promo.component';
import { ListServiceComponent } from './list-service/list-service.component';
import { AddServiceComponent } from './add-service/add-service.component';
import { UpdateServiceComponent } from './update-service/update-service.component';
import { ListAppointementComponent } from './list-appointement/list-appointement.component';
import { AddAppointementsComponent } from './add-appointements/add-appointements.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { UpdateAppointementComponent } from './update-appointement/update-appointement.component';
import { UploadImageSComponent } from './upload-image/upload-image.component';
import { OneServiceComponent } from './one-service/one-service.component';
import { ListServiceFrontComponent } from './list-service-front/list-service-front.component';


//import { HttpClientModule  } from '@angular/common/http';
import { ListAuctionsComponent } from './list-auctions/list-auctions.component';
import { CommonModule } from '@angular/common';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AuctionByIdComponent } from './auction-by-id/auction-by-id.component';
import { ADauctionsComponent } from './adauctions/adauctions.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { LoginComponent } from './components/User/login/login.component';
import { RegisterComponent } from './components/User/register/register.component';
import { ForgotPasswordComponent } from './components/User/forgot-password/forgot-password.component';
import { UpdatePasswordComponent } from './components/User/update-password/update-password.component';
import { UserAccountComponent } from './components/User/user-account/user-account.component';
import { ListUsersComponent } from './components/User/list-users/list-users.component';
import { AddUserComponent } from './components/User/add-user/add-user.component';
import { EditUserComponent } from './components/User/edit-user/edit-user.component';
import { ViewUserComponent } from './components/User/view-user/view-user.component';
import { ListRolesComponent } from './Role/list-roles/list-roles.component';
import { AddRolesComponent } from './Role/add-roles/add-roles.component';
import { EditRoleComponent } from './Role/edit-role/edit-role.component';

import { SocialLoginModule, SocialAuthServiceConfig } from '@abacritt/angularx-social-login';
import {
  GoogleLoginProvider,
  FacebookLoginProvider
} from '@abacritt/angularx-social-login';


import { AnnouncementDetailsComponent } from './pages/announcement-details/announcement-details.component';
import { TopAnnouncementsComponent } from './pages/top-announcements/top-announcements.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatTreeModule} from '@angular/material/tree';
import {MatExpansionModule} from '@angular/material/expansion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatListModule } from '@angular/material/list';
import { AddPropertyComponent } from './pages/add-property/add-property.component';
import { UpdatePropertyComponent } from './pages/update-property/update-property.component';
import { AddAnnouncementComponent } from './pages/add-announcement/add-announcement.component';
import { ListImagesPropertyComponent } from './pages/list-images-property/list-images-property.component';
import { ViewAnnouncementComponent } from './pages/view-announcement/view-announcement.component';
import { AddAppointmentComponent } from './pages/add-appointment/add-appointment.component';
import { ScheduleModule,DayService , WeekService,WorkWeekService,MonthService,MonthAgendaService, RecurrenceEditorModule } from '@syncfusion/ej2-angular-schedule';
import { AppointmentsByPropertyComponent } from './pages/appointments-by-property/appointments-by-property.component';
import { UpdateAppointmentComponent } from './pages/update-appointment/update-appointment.component';
import { MyAppointmentsComponent } from './pages/my-appointments/my-appointments.component';
import { SuggestedComponent } from './pages/suggested/suggested.component';

const material =[
  MatTableModule,
  MatPaginatorModule,
  MatTreeModule,
  MatExpansionModule,
  MatListModule
  ]

import {  HttpClient  } from '@angular/common/http';
import { AddForumComponent } from './components/forum/add-forum/add-forum.component';
import { ForumListComponent } from './components/forum/forum-list/forum-list.component';
import { ForumByIdComponent } from './components/forum/forum-by-id/forum-by-id.component';
import { UpdateForumComponent } from './components/forum/update-forum/update-forum.component';
import { AddPostComponent } from './components/post/add-post/add-post.component';
import { PostByIdComponent } from './components/post/post-by-id/post-by-id.component';
import { UpdatePostComponent } from './components/post/update-post/update-post.component';
import { PostService } from './services/post.service';
import { PostVoteService } from './services/post-vote.service';
import { ForumService } from './services/forum.service';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { ReservationServiceService } from './services/reservation-service.service';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { UpdateDormRoomComponent } from './update-dorm-room/update-dorm-room.component';
import { RoomComponent } from './room/room.component';
import { UploadImageComponent } from './pages/upload-image/upload-image.component';
import { OccupancyRateComponent } from './occupancy-rate/occupancy-rate.component';
import { ListDormRoomsComponent } from './list-dorm-rooms/list-dorm-rooms.component';
import { DormsbackComponent } from './components/DormsBack/dormsback/dormsback.component';
import { FeedbackComponent } from './components/feedback/feedback/feedback.component';
import { ListReservationsComponent } from './list-reservations/list-reservations.component';
import { AddDormRoomComponent } from './add-dorm-room/add-dorm-room.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    ListPropertiesComponent,
    ContactComponent,
    AboutUsComponent,
    ListProductComponent,
    AddProductComponent,
    UpdateProductComponent,
    ListCardComponent,
    AddCardComponent,
    UpdateCardsComponent,
    ListOrderComponent,
    AddOrderComponent,
    UpdateOrderComponent,
    ViewProductCardComponent,
    DiscountComponent,
    PromoComponent,


    ListReservationsComponent,
    AddDormRoomComponent,
    AboutUsComponent,
    ListDormRoomsComponent,
    FeedbackComponent,
    DormsbackComponent,
    AddReservationComponent,
    UpdateReservationComponent,
    OccupancyRateComponent,
    RoomComponent,
    UploadImageSComponent,
    UpdateDormRoomComponent,
    ListAuctionsComponent,
    AddAuctionComponent,
    AuctionByIdComponent,
    ADauctionsComponent,
    LoginComponent,
    RegisterComponent,
    ForgotPasswordComponent,
    UpdatePasswordComponent,
    UserAccountComponent,
    ListUsersComponent,
    AddUserComponent,
    EditUserComponent,
    ViewUserComponent,
    ListRolesComponent,
    AddRolesComponent,
    EditRoleComponent,

    AnnouncementDetailsComponent,
    TopAnnouncementsComponent,
    ListOfPropertiesComponent,
    AddPropertyComponent,
    UpdatePropertyComponent,
    AddAnnouncementComponent,
    ListImagesPropertyComponent,
    ViewAnnouncementComponent,
    AddAppointmentComponent,
    AppointmentsByPropertyComponent,
    UpdateAppointmentComponent,
    MyAppointmentsComponent,
    SuggestedComponent,
    AddForumComponent,
    ForumListComponent,
    ForumByIdComponent,
    UpdateForumComponent,
    AddPostComponent,
    PostByIdComponent,
    UpdatePostComponent,
    UnauthorizedComponent,


    AboutUsComponent,
    ListServiceComponent,
    AddServiceComponent,
    UpdateServiceComponent,
    ListAppointementComponent,
    AddAppointementsComponent,
    UpdateAppointementComponent,
    UploadImageComponent,
    OneServiceComponent,
    ListServiceFrontComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,

    HttpClientModule,
    CommonModule ,
    ReactiveFormsModule,
    NgxPaginationModule,
    SocialLoginModule,
    material,
    BrowserAnimationsModule,
    ScheduleModule, RecurrenceEditorModule ,
    Ng2SearchPipeModule,
    HttpClientModule,


  ],

  providers: [PostService,PostVoteService,ForumService,HttpClient,ReservationServiceService,DayService, WeekService,WorkWeekService,MonthService,MonthAgendaService,    {
    provide: 'SocialAuthServiceConfig',
    useValue: {
      autoLogin: false,
      providers: [
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider(
            '381776128801-62onkpeev2iuss0npe8li35412jsksqh.apps.googleusercontent.com'
          )
        }
      ],
      onError: (err) => {
        console.error(err);
      }
    } as SocialAuthServiceConfig,

  }],


  exports:[material],
  bootstrap: [AppComponent]
})
export class AppModule { }
