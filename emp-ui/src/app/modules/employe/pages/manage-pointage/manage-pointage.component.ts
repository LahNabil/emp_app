import {Component, OnInit} from '@angular/core';
import {EmpDto} from "../../../../models/EmpDto";
import {EmployeService} from "../../service/employe/employe.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PointageResponse} from "../../../../models/PointageResponse";
import {PointageService} from "../../service/pointage/pointage.service";

@Component({
  selector: 'app-manage-pointage',
  templateUrl: './manage-pointage.component.html',
  styleUrl: './manage-pointage.component.scss'
})
export class ManagePointageComponent implements OnInit{

  errorMsg: Array<string> = [];
  poiResponse: PointageResponse = {};
  empList: EmpDto[] = [];
  depList: any[] = [];
  constructor(private pointageService: PointageService,private employeService: EmployeService, private router: Router, private activatedRoute: ActivatedRoute) {
  }
  ngOnInit() {
    this.getAllEmp();
  }
  getAllEmp(){
    this.employeService.getEmpAll().subscribe(data=>{
      this.empList = data;
    })
  }

  savePoi() {
    this.pointageService.savePointage(this.poiResponse).subscribe({
      next: (res)=>{
        this.router.navigate(['/employe/pointage'])
      },
      error: (err)=>{
        this.errorMsg = err.error.validationErrors;
      }
    })

  }

}
