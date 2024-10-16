import {Component, OnInit} from '@angular/core';
import {DepartementService} from "../../service/departement.service";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeResponse} from "../../../../models/EmployeResponse";

@Component({
  selector: 'app-emp-dep-list',
  templateUrl: './emp-dep-list.component.html',
  styleUrl: './emp-dep-list.component.scss'
})
export class EmpDepListComponent implements OnInit{

  empResponse: EmployeResponse[] = [];

  constructor(private departementService: DepartementService, private router: Router, private activatedRoute: ActivatedRoute) {
  }
  ngOnInit() {
    const idDep = this.activatedRoute.snapshot.params['idDep'];
    if (idDep){
      this.departementService.getEmployesByDepartement(idDep).subscribe({
        next:(res:EmployeResponse[])=>{
          this.empResponse = res;
        }
      })
    }
  }

}
