import { Component, OnInit } from '@angular/core';
import { TeacherService } from '../shared/teacher/teacher.service';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-list.component.html',
  styleUrls: ['./teacher-list.component.css']
})
export class TeacherListComponent implements OnInit {

  teachers: Array<any>;
  public myid= "";
  public employees = [];
  public errorMsg= "";

  constructor(private teacherService: TeacherService, private _employeeservice:  EmployeeService) { }
  //a este componente se le esta inyectando dos objetos para tener una variabel de instancia y asi llamar a los metodos de estos objetospara obtener esos datos aqui en
  ///este componente

  ngOnInit() {

    this.teacherService.getAll()
    .subscribe(data =>	this.teachers= data,
                error =>  this.errorMsg = error);
    }
  
   

  }

