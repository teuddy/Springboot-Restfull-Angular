import {NgModule} from '@angular/core';
import {Routes, RouterModule} from  '@angular/router';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { CoursesListComponent } from './courses-list/courses-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';


const routes: Routes =[
    {path: '', component: TeacherListComponent },
    {path: 'teachers',component: TeacherListComponent},
    {path: 'courses', component:CoursesListComponent} ,
    {path: "**", component: PageNotFoundComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingngMOdule{
}

export const routingComponents= [TeacherListComponent,CoursesListComponent, PageNotFoundComponent]