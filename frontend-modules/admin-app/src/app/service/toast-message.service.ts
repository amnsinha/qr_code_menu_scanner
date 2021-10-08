import {Injectable} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ToastMessageService {
  constructor(private messageService: MessageService) {
  }

  showMessage(detail: string, severity: string) {
    this.clearMessage();
    this.messageService.add({severity: severity, detail: detail, life: 3000});
  }

  clearMessage() {
    if (document.getElementsByClassName('ul-growl-item-contaimer').length > 0) {
      document.getElementsByClassName('ul-growl-item-contaimer')[0].remove();
    }
  }

}
