import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {//Se puede usar este service (clase ) paraobtener lalogica o consumir la api y desde aqui enviarla a varios componentes

  constructor() { }


  getEmployees(){

    return [
      {"id": 1, "name": "teuddy" , "age": 22},
      {"id": 1, "name": "teuddy" , "age": 22},
      {"id": 1, "name": "teuddy" , "age": 22},
      {"id": 1, "name": "teuddy" , "age": 22},
      {"id": 1, "name": "teuddy" , "age": 22},
      {"id": 1, "name": "teuddy" , "age": 22}

    ];
  }
}
