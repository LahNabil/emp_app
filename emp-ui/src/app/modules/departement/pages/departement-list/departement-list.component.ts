import {Component, OnInit} from '@angular/core';
import {DepartementService} from "../../service/departement.service";
import {Router} from "@angular/router";
import {PageResponseDepartement} from "../../../../models/PageResponseDepartement";
import {DepartementResponse} from "../../../../models/DepartementResponse";

@Component({
  selector: 'app-departement-list',
  templateUrl: './departement-list.component.html',
  styleUrl: './departement-list.component.scss'
})
export class DepartementListComponent implements OnInit{
   page: number = 0;
   size: number = 3;
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

  goToFirstPage() {
    this.page = 0;
    this.getAllDepartements();

  }

  goToPreviousPage() {
    this.page--;
    this.getAllDepartements();

  }

  goToPage(index: number) {
    this.page = index;
    this.getAllDepartements();

  }

  goToNextPage() {
    this.page ++;
    this.getAllDepartements();

  }

  goToLastPage() {
    this.page = this.departementResponse.totalPages as number - 1;
    this.getAllDepartements();

  }

  isLastPage() {
    return this.page == this.departementResponse.totalPages as number - 1;
  }

  editDepartement(dep: DepartementResponse) {
    this.router.navigate(['departement', 'manage', dep.idDep])

  }

  detailsDepartement(dep: DepartementResponse) {
    this.router.navigate(['departement', 'emp', dep.idDep])

  }

  archiveDepartement(dep: DepartementResponse) {
    const isConfirmed = window.confirm("Êtes-vous sûr de vouloir supprimer ce Departement ?");
    if (isConfirmed) {
      this.departementService.archiveDepartement(dep.idDep).subscribe({
        next: () => {
          window.location.reload();
        },
        error: (err) => {
          console.error('Error archiving department:', err);
        }
      });
    }
  }

}
