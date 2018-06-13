import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { TeacherService } from './shared/teacher/teacher.service';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from './employee.service';
import { AppRoutingngMOdule, routingComponents } from './app-routing.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';



@NgModule({
  declarations: [
    AppComponent,
      routingComponents,
      PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,//MODULO ENCQRGADO DE HACER LLAMADAS HTTP ETC BLABLA
    FormsModule,
    AppRoutingngMOdule

  ],
  providers: [
    TeacherService,
    EmployeeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
