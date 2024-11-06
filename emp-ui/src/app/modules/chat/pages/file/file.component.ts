import {Component, OnInit} from '@angular/core';
import {FileService} from "../../services/file.service";
import {HttpErrorResponse, HttpEvent, HttpEventType} from "@angular/common/http";
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-file',
  templateUrl: './file.component.html',
  styleUrl: './file.component.scss'
})
export class FileComponent implements OnInit{

  //AllFiles: FileDto[] = [];
  AllFiles: any;
  filenames: string[] = [];
  fileStatus = { status: '', requestType: '', percent: 0 };

  constructor(private fileService: FileService ) {
  }
  ngOnInit() {
    this.getFiles();
  }

  getFiles(){
    this.fileService.loadFiles().subscribe(data=>{
      this.AllFiles = data;
    },
      error => {
        console.error('Erreur lors du chargement des fichiers', error);
        // Ici, vous pourriez afficher un message d'erreur à l'utilisateur
      }
      )
  }
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files) {
      const files = Array.from(input.files); // Convert FileList to an array of File
      this.onUploadFiles(files);
    }
  }
  onUploadFiles(files: File[]): void {
    const formData = new FormData();
    for (const file of files) {
      formData.append('files', file, file.name);
    }
    this.fileService.saveFiles(formData).subscribe(
      event => {
        console.log(event);
        this.resportProgress(event);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
  // onUploadFiles(files: File[]):void{
  //   const formData = new FormData();
  //   for(const file of files){
  //     formData.append('files', file, file.name);}
  //   this.fileService.saveFiles(formData).subscribe(
  //     event =>{
  //       console.log(event);
  //       this.resportProgress(event);
  //     },
  //     (error: HttpErrorResponse)=> {
  //       console.log(error);
  //     }
  //   );
  //
  // }

  onDelete(name:String) {

  }
  private updateStatus(loaded: number, total: number, requestType: string): void {
    this.fileStatus.status = 'progress';
    this.fileStatus.requestType = requestType;
    this.fileStatus.percent = Math.round(100 * loaded / total);
  }

  private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
    switch(httpEvent.type) {
      case HttpEventType.UploadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Uploading... ');
        break;
      case HttpEventType.DownloadProgress:
        this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Downloading... ');
        break;
      case HttpEventType.ResponseHeader:
        console.log('Header returned', httpEvent);
        break;
      case HttpEventType.Response:
        if (httpEvent.body instanceof Array) {
          this.fileStatus.status = 'done';
          for (const filename of httpEvent.body) {
            this.filenames.unshift(filename);
          }
        } else {
          saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!,
            {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
        }
        this.fileStatus.status = 'done';
        break;
      default:
        console.log(httpEvent);
        break;

    }
  }
}
