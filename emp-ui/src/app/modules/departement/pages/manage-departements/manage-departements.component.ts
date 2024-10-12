import {Component, OnInit} from '@angular/core';
import {DepartementResponse} from "../../../../models/DepartementResponse";
import {DepartementService} from "../../service/departement.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-manage-departements',
  templateUrl: './manage-departements.component.html',
  styleUrl: './manage-departements.component.scss'
})
export class ManageDepartementsComponent implements OnInit{
  errorMsg: Array<string> = [];
  depResponse: DepartementResponse = {nom: ''};

  constructor(private departementService: DepartementService, private router: Router, private activatedRoute: ActivatedRoute) {
  }
  ngOnInit() {
    const idDep = this.activatedRoute.snapshot.params['idDep'];
    if (idDep){
      this.departementService.getDepartementById(idDep).subscribe({
        next:(res)=>{
          this.depResponse = {
            idDep: res.idDep,
            nom: res.nom as string
          }
        }
      })

    }
  }

  saveDep() {
    this.departementService.saveDepartement(this.depResponse).subscribe({
      next: (res)=>{
        this.router.navigate(['/departement'])
      },
      error: (err)=>{
        this.errorMsg = err.error.validationErrors;
      }
    })

  }
}
