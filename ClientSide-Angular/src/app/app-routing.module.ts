

import { ListPropertiesComponent } from './pages/list-Announcements/list-properties.component';

import { Component, NgModule } from '@angular/core';

import { AboutUsComponent } from './components/about-us/about-us.component';
import { ContactComponent } from './components/contact/contact.component';
import { ListProductComponent } from './list-product/list-product.component';
import { AddProductComponent } from './add-product/add-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { ListCardComponent } from './list-card/list-card.component';
import { AddCardComponent } from './add-card/add-card.component';
import { UpdateCardsComponent } from './update-cards/update-cards.component';
import { ListOrderComponent } from './list-order/list-order.component';
import { AddOrderComponent } from './add-order/add-order.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { PromoComponent } from './promo/promo.component';
import { DiscountComponent } from './discount/discount.component';
import { ViewProductCardComponent } from './view-product-card/view-product-card.component';

import { ListDormRoomsComponent } from './list-dorm-rooms/list-dorm-rooms.component';
import { ListReservationsComponent } from './list-reservations/list-reservations.component';
import { AddDormRoomComponent } from './add-dorm-room/add-dorm-room.component';
import { UpdateDormRoomComponent } from './update-dorm-room/update-dorm-room.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { UploadImageComponent } from './pages/upload-image/upload-image.component';
import { DormsbackComponent } from './components/DormsBack/dormsback/dormsback.component';
import { OccupancyRateComponent } from './occupancy-rate/occupancy-rate.component';
import { RoomComponent } from './room/room.component';
import { FeedbackComponent } from './components/feedback/feedback/feedback.component';
import { CaptchaComponent } from './components/captcha/captcha.component';


import { ListAuctionsComponent } from './list-auctions/list-auctions.component';
import { AddAuctionComponent } from './add-auction/add-auction.component';
import { AuctionByIdComponent } from './auction-by-id/auction-by-id.component';
import { ADauctionsComponent } from './adauctions/adauctions.component';
import { LoginComponent } from "./components/User/login/login.component";
import { RegisterComponent } from "./components/User/register/register.component";
import { ForgotPasswordComponent } from "./components/User/forgot-password/forgot-password.component";
import { UpdatePasswordComponent } from "./components/User/update-password/update-password.component";
import { UserAccountComponent } from './components/User/user-account/user-account.component';
import { ListUsersComponent } from './components/User/list-users/list-users.component';
import { AddUserComponent } from './components/User/add-user/add-user.component';
import { EditUserComponent } from './components/User/edit-user/edit-user.component';
import { ViewUserComponent } from './components/User/view-user/view-user.component';
import { ListRolesComponent } from './Role/list-roles/list-roles.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { AddRolesComponent } from './Role/add-roles/add-roles.component';
import { EditRoleComponent } from './Role/edit-role/edit-role.component';
import { HeaderComponent } from './components/header/header.component';


import { AnnouncementDetailsComponent } from './pages/announcement-details/announcement-details.component';
import { TopAnnouncementsComponent } from './pages/top-announcements/top-announcements.component';
import { ListOfPropertiesComponent } from './pages/list-properties/list-properties.component';
import { AddPropertyComponent } from './pages/add-property/add-property.component';
import { UpdatePropertyComponent } from './pages/update-property/update-property.component';
import { ListImagesPropertyComponent } from './pages/list-images-property/list-images-property.component';
import { AddAnnouncementComponent } from './pages/add-announcement/add-announcement.component';
import { ViewAnnouncementComponent } from './pages/view-announcement/view-announcement.component';
import { AddAppointmentComponent } from './pages/add-appointment/add-appointment.component';
import { AppointmentsByPropertyComponent } from './pages/appointments-by-property/appointments-by-property.component';
import { UpdateAppointmentComponent } from './pages/update-appointment/update-appointment.component';
import { MyAppointmentsComponent } from './pages/my-appointments/my-appointments.component';

import { ForumListComponent } from "./components/forum/forum-list/forum-list.component";
import { ForumByIdComponent } from "./components/forum/forum-by-id/forum-by-id.component";
import { AddForumComponent } from "./components/forum/add-forum/add-forum.component";
import { UpdateForumComponent} from "./components/forum/update-forum/update-forum.component";
import { AddPostComponent} from "./components/post/add-post/add-post.component";
import { UpdatePostComponent} from "./components/post/update-post/update-post.component";
import { PostByIdComponent} from "./components/post/post-by-id/post-by-id.component";
import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [

  {path:"" ,redirectTo:"/ListAnnouncements" , pathMatch:"full"},
  {path:"AboutUs" , component:AboutUsComponent},
  {path:"ListCards" , component:ListCardComponent},
  {path:"ListProduct",component:ListProductComponent},
  {path:"Contact", component:ContactComponent},
  {path:"AddProduct", component:AddProductComponent},
  {path:"AddCards", component:AddCardComponent},
  {path:"AddOrders/:idproduct", component:AddOrderComponent},
  {path:"ListOrders", component:ListOrderComponent},
  {path:"UpdateProduct/:id", component:UpdateProductComponent},
  {path:"UpdateCards/:id", component:UpdateCardsComponent},
  {path:"UpdateOrders/:id", component:UpdateOrderComponent},
  {path:"Promo/:id", component:PromoComponent},
  {path:"discount/:id", component:DiscountComponent},
  {path:"cardProduct", component:ViewProductCardComponent},



  {path:"ListProperties",component:ListOfPropertiesComponent},
  {path:"Contact", component:ContactComponent},


  {path:"Auctions", component:ListAuctionsComponent},
  {path:"AdimnAuctions", component:ADauctionsComponent},
  {path:"AddAuction/:idprop", component:AddAuctionComponent},
  {path:"AuctionById/:idAuction", component:AuctionByIdComponent},
  {path:"login", component: LoginComponent},
  {path:"register", component: RegisterComponent},
  {path:"forgotPassword", component: ForgotPasswordComponent},
  {path:"updatePassword", component:UpdatePasswordComponent},
  {path:"userAccount", component:UserAccountComponent},
  {path:"listUsers", component:ListUsersComponent},
  { path: 'ViewUser/:email', component: ViewUserComponent },
  { path: 'AddUser', component: AddUserComponent },
  { path: 'EditRole/:id', component: EditRoleComponent},
  { path: 'EditUser/:email', component: EditUserComponent},
  { path: 'addRoles', component: AddRolesComponent},
  { path: 'listRoles', component: ListRolesComponent},

  { path: 'header', component: HeaderComponent},

  {path:"ListAnnouncements",component:ListPropertiesComponent},
  {path:"Contact", component:ContactComponent},
  {path:"AnnouncementDetails/:announcementID" , component:AnnouncementDetailsComponent},
  {path:"TopAnnouncements", component:TopAnnouncementsComponent},
  {path:"ListProperties", component:ListOfPropertiesComponent},
  {path:"AddProperty", component:AddPropertyComponent},
  {path:"UpdateProperty/:propertyID", component:UpdatePropertyComponent},
  {path:"ListImages/:propertyID", component:ListImagesPropertyComponent},
  {path:"AddAnnouncement/:propertyID", component:AddAnnouncementComponent},
  {path:"ViewAnnouncement/:propertyID", component:ViewAnnouncementComponent},
  {path:"AddAppointment/:propertyID", component:AddAppointmentComponent},
  {path:"GetAppointment/:propertyID", component:AppointmentsByPropertyComponent},
  {path:"UpdateAppointment/:appointmentId", component:UpdateAppointmentComponent},
  {path:"MyAppointment", component:MyAppointmentsComponent},

  { path: 'header', component: HeaderComponent},

//Forum
  {path:"allForums", component: ForumListComponent},
  {path:"forumById/:id", component: ForumByIdComponent},
  {path:"addForum", component: AddForumComponent},
  {path:"updateForum/:id", component: UpdateForumComponent},

  //Post
  {path:"addPost", component: AddPostComponent},
  {path:"updatePost/:id", component: UpdatePostComponent},
  {path:"postById/:id", component: PostByIdComponent},
  {path:"unauth", component: UnauthorizedComponent},

  //Comment

  {path:"ListProperties",component:ListPropertiesComponent},
  {path:"Contact", component:ContactComponent},
  {path:"ListDormRooms", component:ListDormRoomsComponent},
  {path:"ListReservations", component:ListReservationsComponent},
  {path:"AddReservation/:roomID", component:AddReservationComponent},
  {path:"AddDormRoom", component:AddDormRoomComponent},
  {path:"UpdateDormRoom/:roomID", component:UpdateDormRoomComponent},
  {path:"AddImage/:roomID", component:UploadImageComponent},
  {path:"UpdateReservation/:reservationID", component:UpdateReservationComponent},
  {path:"dormsback",component:DormsbackComponent},
  { path: 'occupancy-rate', component: OccupancyRateComponent },
  { path: 'dorms/:roomID', component: RoomComponent },
  { path: 'feedback', component: FeedbackComponent },
  {path:'captcha',component: CaptchaComponent},
  {path:"ListServices", component:ListServiceComponent},
  {path:"ListServicesFront", component:ListServiceFrontComponent},
  {path:"AddService", component:AddServiceComponent},
  {path:"AddAppointements", component:AddAppointementsComponent},
  {path:"UpdateService/:id", component:UpdateServiceComponent},
  {path:"oneService/:Serviceid", component:OneServiceComponent},
  {path:"AddImage/:id_service", component:UploadImageSComponent},
  {path:"UpdateAppointements/:id", component:UpdateAppointementComponent},
  {path:"ListAppointments",component:ListAppointementComponent},


];





import { ListServiceComponent } from './list-service/list-service.component';
import { AddServiceComponent } from './add-service/add-service.component';
import { UpdateServiceComponent } from './update-service/update-service.component';
import { ListAppointementComponent } from './list-appointement/list-appointement.component';
import { AddAppointementsComponent } from './add-appointements/add-appointements.component';
import { UpdateAppointementComponent } from './update-appointement/update-appointement.component';
import { UploadImageSComponent } from './upload-image/upload-image.component';
import { OneServiceComponent } from './one-service/one-service.component';
import { ListServiceFrontComponent } from './list-service-front/list-service-front.component';




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
