import {Injectable} from '@angular/core';
import {Message} from 'primeng/primeng';
import {MessageService} from 'primeng/api';

const GLOBAL_TOAST_KEY = 'globalToast';
const TOAST_LIFE_DURATION = 5000;

export enum ToastSeverity {
  NONE = '',
  WARN = 'warn',
  ERROR = 'error',
  INFO = 'info',
  SUCCESS = 'success'
}

export class ToastMessage {
  errorDetail: string;
  severity?: ToastSeverity;
  summary?: string;
}

@Injectable()
export class ToastService {

  constructor(public messageService: MessageService) {
  }

  public showToastMessage(errorDetail: string, severity?: ToastSeverity, summary?: string, sticky: boolean = false): void {
    this.clearToastMessages();
    this._showToastMessages([{
      severity: severity ? severity : ToastSeverity.ERROR,

      summary: summary ? summary : (severity === ToastSeverity.ERROR ? 'Error' : ''),
      detail: errorDetail,
      key: GLOBAL_TOAST_KEY,
      life: TOAST_LIFE_DURATION
    }]);
  }

  public showToastMessages(toastMessages: ToastMessage[]): void {
    this.clearToastMessages();
    const messages: Message[] = [];
    for (const toastMessage of toastMessages) {
      const message: Message = {} as Message;
      message.severity = toastMessage.severity ? toastMessage.severity : ToastSeverity.ERROR;
      message.summary = toastMessage.summary ? toastMessage.summary : (toastMessage.severity === ToastSeverity.ERROR ?
        'Error' : '');
      message.detail = toastMessage.errorDetail;
      message.key = GLOBAL_TOAST_KEY;
      message.life = TOAST_LIFE_DURATION;
      messages.push(message);
    }
    this._showToastMessages(messages);
  }

  clearToastMessages() {
    this.messageService.clear();
  }

  private _showToastMessages(message: Message[]) {
    this.messageService.addAll(message);
  }
}
