import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  SERVER_URL = 'sellerportal/seller/upload';
  uploadForm: FormGroup;
  file: string;
  myFiles: string[] = [];

  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient) { }


  ngOnInit() {

    this.uploadForm = this.formBuilder.group({
      profile: ['']
    });

  }

  onFileSelect(event) {
    for (let i = 0; i < event.target.files.length; i++) {
      this.file = event.target.files[i];
      this.myFiles.push(event.target.files[i]);
    }
    console.log(this.myFiles);
  }

  onSubmit() {
    const formData = new FormData();

    for (let i = 0; i < this.myFiles.length; i++) {
      formData.append('rohit[]', this.myFiles[i]);
    }

    this.httpClient.post<any>(this.SERVER_URL, formData).subscribe(
      (res) => {
        console.log(res);
        this.myFiles = null;
        this.uploadForm.reset();
      },
      (err) => console.log(err),
    );


  }

}
