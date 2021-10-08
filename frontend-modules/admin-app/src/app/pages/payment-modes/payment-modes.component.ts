import {Component, OnDestroy, OnInit} from '@angular/core';
import {MessageService} from 'primeng';
import {PaymentModeService} from '../../service/payment-mode.service';
import {PaymentFiles} from '../../model/customer-app-settings/PaymentFiles';
import {FilesInfo} from '../../model/customer-app-settings/FilesInfo';

@Component({
  selector: 'app-payment-modes',
  templateUrl: './payment-modes.component.html',
  styleUrls: ['./payment-modes.component.css']
})
export class PaymentModesComponent implements OnInit {


  uploadedFiles: any[] = [];
  filesToUploadList: PaymentFiles;
  filesfetchedInfos: FilesInfo[] = [];

  constructor(private messageService: MessageService, private paymentModeService: PaymentModeService) {
  }

  onUpload(data: { files: File }, event) {


    const formData:FormData = new FormData();

    const fileReader = new FileReader();
     for (let file of event.files) {
      this.uploadedFiles.push(file);
      fileReader.readAsDataURL(file);
      let filebase64 = null;
      fileReader.onload = function () {
        filebase64 = fileReader.result;
      };

      setTimeout(() => {
        const filesInfo = new FilesInfo();
        filesInfo.file = data.files[0];
        filesInfo.files = filebase64;
        filesInfo.name = file.name;
        this.filesToUploadList = new PaymentFiles();
        this.filesToUploadList.hotelId = localStorage.getItem('currentUser');
        this.filesToUploadList.files = [];
        this.filesToUploadList.files.push(filesInfo);





        formData.append('hotelId', localStorage.getItem('currentUser'));
        formData.append('file',  data.files[0]);
        this.paymentModeService.uploadPayment(formData).subscribe(res => {
          if (res.payload) {
            this.getPaymentFiles();
          }
        });
      }, 2000);
    }
    this.messageService.add({severity: 'info', summary: 'File Uploaded', detail: ''});
  }


  removePayment(filenumbner) {
    const hotelId = localStorage.getItem('currentUser');
    this.paymentModeService.removePayment(hotelId, filenumbner).subscribe(res => {
      if (res.payload) {
        this.getPaymentFiles();
      }
    });
    this.messageService.add({severity: 'info', summary: 'File Removed', detail: ''});
  }

  getPaymentFiles() {
    const hotelId = localStorage.getItem('currentUser');
    this.paymentModeService.getPaymentFiles(hotelId).subscribe(res => {
      if (res.payload && res.payload.files.length > 0) {
        this.filesfetchedInfos = res.payload.files;
      }
      if (res.payload && res.payload.files.length === 0) {
        this.filesfetchedInfos = [];
      }
    });
  }

  ngOnInit(): void {
    this.getPaymentFiles();
  }
}
