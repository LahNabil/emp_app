import {Component, OnInit} from '@angular/core';
import {PageResponseEmploye} from "../../../../models/PageResponseEmploye";
import {EmployeService} from "../../service/employe/employe.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-employe-list',
  templateUrl: './employe-list.component.html',
  styleUrl: './employe-list.component.scss'
})
export class EmployeListComponent implements OnInit{

  page: number = 0;
  size: number = 5;
  employePageReponse: PageResponseEmploye = {};
  filteredEmployeData: any[] = [];
  allEmp: any[] = [];

  constructor(private employeService: EmployeService, private router: Router) {
  }
  ngOnInit() {
    this.getAllEmployes();
    this.getAllEmp();
  }

  private getAllEmp(){
    this.employeService.getEmpAll().subscribe(data=>{
      this.allEmp = data;
    })
  }

  private getAllEmployes() {
    this.employeService.getAllEmployes(this.page, this.size)
      .subscribe({
        next: (employes)=>{
          this.employePageReponse = employes;
          this.filteredEmployeData = employes.content ?? [];
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


  onEdit(idEmp: number | undefined) {
    this.router.navigate(['employe', 'manage', idEmp])

  }

  searchEmployeByName($event: any) {
    const input = $event.target as HTMLInputElement;
    const searchValue = input.value.toLowerCase();
    if (this.employePageReponse.content) {
      this.filteredEmployeData = this.allEmp.filter(employee =>
        employee.nom.toLowerCase().includes(searchValue)
      );
    }
    // this.filteredEmployeData = this.employePageReponse.content?.filter(employee=>{
    //   if(employee.nom.toLowerCase().includes(input.value.toLowerCase())){
    //     return employee
    //
    //   }
    // })

  }
}
