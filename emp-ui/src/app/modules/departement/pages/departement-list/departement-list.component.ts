import {Component, OnInit} from '@angular/core';
import {DepartementService} from "../../service/departement.service";
import {Router} from "@angular/router";
import {PageResponseDepartement} from "../../../../models/PageResponseDepartement";

@Component({
  selector: 'app-departement-list',
  templateUrl: './departement-list.component.html',
  styleUrl: './departement-list.component.scss'
})
export class DepartementListComponent implements OnInit{
   page: number = 0;
   size: number = 5;
   departementResponse: PageResponseDepartement = {};

  constructor(private departementService: DepartementService, private router: Router) {

  }

  ngOnInit() {
    this.getAllDepartements();
  }

  private getAllDepartements() {
    this.departementService.getAllDepartements(this.page, this.size)
      .subscribe({
        next: (departements)=>{
          this.departementResponse = departements;
        }
      })

  }
}
