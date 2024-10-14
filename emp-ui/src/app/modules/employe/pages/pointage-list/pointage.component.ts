import {Component, OnInit} from '@angular/core';
import {PageResponsePointage} from "../../../../models/PageResponsePointage";
import {Router} from "@angular/router";
import {PointageService} from "../../service/pointage/pointage.service";

@Component({
  selector: 'app-pointage',
  templateUrl: './pointage.component.html',
  styleUrl: './pointage.component.scss'
})
export class PointageComponent implements OnInit{
  page: number = 0;
  size: number = 10;
  pointagePageReponse: PageResponsePointage = {};

  constructor(private pointageService: PointageService, private router: Router) {
  }
  ngOnInit() {
    this.getAllPointages();
  }

  private getAllPointages() {
    this.pointageService.getAllPointages(this.page, this.size)
      .subscribe({
        next: (pointages)=>{
          this.pointagePageReponse = pointages;
        }
      })

  }

  goToFirstPage() {
    this.page = 0;
    this.getAllPointages();

  }

  goToPreviousPage() {
    this.page--;
    this.getAllPointages();

  }

  goToPage(index: number) {
    this.page = index;
    this.getAllPointages();

  }

  goToNextPage() {
    this.page ++;
    this.getAllPointages();

  }

  goToLastPage() {
    this.page = this.pointagePageReponse.totalPages as number - 1;
    this.getAllPointages();

  }

  isLastPage() {
    return this.page == this.pointagePageReponse.totalPages as number - 1;
  }

}
