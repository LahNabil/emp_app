import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-departement-list',
  templateUrl: './departement-list.component.html',
  styleUrl: './departement-list.component.scss'
})
export class DepartementListComponent implements OnInit{

  constructor(private departementService: DepartementService, private router: Router) {

  }

  ngOnInit() {
    this.getAllDepartements();
  }

  private getAllDepartements() {
    this.departementService.getAllDeps()

  }
}
