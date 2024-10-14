import {Component, OnInit} from '@angular/core';
import {EmployeResponse} from "../../../../models/EmployeResponse";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeService} from "../../service/employe/employe.service";
import {EmpDto} from "../../../../models/EmpDto";
import {SharedService} from "../../../shared-module/services/shared.service";

@Component({
  selector: 'app-manage-employe',
  templateUrl: './manage-employe.component.html',
  styleUrl: './manage-employe.component.scss'
})
export class ManageEmployeComponent implements OnInit{

  errorMsg: Array<string> = [];
  empResponse: EmployeResponse = {nom: '',prenom: '',cin: '',telephone:'',salaireBase:0 ,idSuperviseur:0,idDep:0 };
  empList: EmpDto[] = [];
  depList: any[] = [];
  constructor(private employeService: EmployeService, private router: Router, private activatedRoute: ActivatedRoute, private sharedService: SharedService) {
  }
  ngOnInit() {
    const idEmp = this.activatedRoute.snapshot.params['idEmp'];
    if (idEmp){
      this.employeService.getEmployeById(idEmp).subscribe({
        next:(res)=>{
          this.empResponse = {
            idEmp: res.idEmp,
            nom: res.nom as string,
            prenom: res.prenom as string,
            cin: res.cin as string,
            telephone: res.telephone as string,
            salaireBase: res.salaireBase,
            idSuperviseur: res.idSuperviseur,
            idDep: res.idDep
          };
        }
      })

    }


    this.getAllEmp();
    this.getAllDep();
  }

  getAllEmp(){
    this.employeService.getEmpAll().subscribe(data=>{
      this.empList = data;
    })
  }



  saveEmp() {
    this.employeService.saveEmploye(this.empResponse).subscribe({
      next: (res)=>{
        this.router.navigate(['/employe'])
      },
      error: (err)=>{
        this.errorMsg = err.error.validationErrors;
      }
    })

  }

  private getAllDep() {
    this.sharedService.getDepAll().subscribe(data=>{
      this.depList = data;
    })

  }
}
