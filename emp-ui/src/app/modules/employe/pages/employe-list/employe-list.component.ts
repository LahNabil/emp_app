import {Component, OnInit} from '@angular/core';
import {PageResponseDepartement} from "../../../../models/PageResponseDepartement";
import {PageResponseEmploye} from "../../../../models/PageResponseEmploye";
import {EmployeService} from "../../service/employe.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employe-list',
  templateUrl: './employe-list.component.html',
  styleUrl: './employe-list.component.scss'
})
export class EmployeListComponent implements OnInit{

  page: number = 0;
  size: number = 4;
  employePageReponse: PageResponseEmploye = {};

  constructor(private employeService: EmployeService, private router: Router) {
  }
  ngOnInit() {
    this.getAllEmployes();
  }

  private getAllEmployes() {
    this.employeService.getAllEmployes(this.page, this.size)
      .subscribe({
        next: (employes)=>{
          this.employePageReponse = employes;
          console.log(employes);
        }
      })

  }

  goToFirstPage() {
    this.page = 0;
    this.getAllEmployes();

  }

  goToPreviousPage() {
    this.page--;
    this.getAllEmployes();

  }

  goToPage(index: number) {
    this.page = index;
    this.getAllEmployes();

  }

  goToNextPage() {
    this.page ++;
    this.getAllEmployes();

  }

  goToLastPage() {
    this.page = this.employePageReponse.totalPages as number - 1;
    this.getAllEmployes();

  }

  isLastPage() {
    return this.page == this.employePageReponse.totalPages as number - 1;
  }


}
