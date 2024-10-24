import {Component, OnInit} from '@angular/core';
import {PageResponsePointage} from "../../../../models/PageResponsePointage";
import {PointageService} from "../../service/pointage/pointage.service";

@Component({
  selector: 'app-pointage',
  templateUrl: './pointage.component.html',
  styleUrl: './pointage.component.scss'
})
export class PointageComponent implements OnInit{
  page: number = 0;
  size: number = 5;
  pointagePageReponse: PageResponsePointage = {};
  filteredPointageData: any[] = [];
  allPoi: any[] = [];

  constructor(private pointageService: PointageService) {
  }
  ngOnInit() {
    this.getAllPointages();
    this.getAllPoi();
  }
  private getAllPoi(){
    this.pointageService.getPointages().subscribe(data=>{
      this.allPoi = data;
    })
  }

  private getAllPointages() {
    this.pointageService.getAllPointages(this.page, this.size)
      .subscribe({
        next: (pointages)=>{
          this.pointagePageReponse = pointages;
          this.filteredPointageData = pointages.content ?? [];
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
  searchEmployeByName($event: any) {
    const input = $event.target as HTMLInputElement;
    const searchValue = input.value.toLowerCase();
    if (this.pointagePageReponse.content) {
      this.filteredPointageData = this.allPoi.filter(pointage =>
        pointage.nomEmp?.toLowerCase().includes(searchValue)
      );
    }
  }

  onArchive(idPoi: any) {
    const isConfirmed = window.confirm("Êtes-vous sûr de vouloir supprimer cette présence ?");
    if (isConfirmed) {
      this.pointageService.archivePointage(idPoi).subscribe({
        next: () => {
          window.location.reload();
        },
        error: (err) => {
          console.error('Error archiving presence:', err);
        }
      });
    }
  }
}
