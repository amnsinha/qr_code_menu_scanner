import {Component, OnInit} from '@angular/core';
import {CdkDrag, CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {MessageService} from 'primeng';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  neworder: Item[] = [];
  progress: Item[] = [];

  constructor(private messageService: MessageService) {
  }

  ngOnInit() {
    this.neworder.push({
        name: 'test1',
        tableNo: 'table1',
        children: [
          {
            name: 'Kadhai Panner',
            specialInstruction: 'Less Spicy',
            serviceTime: 'Please bring later',
            quantity: 'full'
          },
          {name: 'Veg Pulao'}
        ]
      },
      {
        name: 'test2',
        tableNo: 'table2',
        children: [
          {
            name: 'Kadhai chicken',
            specialInstruction: 'Less Spicy',
            serviceTime: 'Please bring Now',
            quantity: 'full'
          },
          {name: 'Mashroom Chilli'},
        ]
      });
    this.progress.push({
        name: 'test1',
        tableNo: 'table1',
        children: [
          {
            name: 'Kadhai Chicken',
            specialInstruction: 'Less Spicy',
            serviceTime: 'Please bring now',
            quantity: 'full'
          }
        ]
      },
      {
        name: 'test2',
        tableNo: 'table1',
        children: [
          {name: 'subItem4'},
          {name: 'subItem5'},
          {name: 'subItem6'}
        ]
      }
    );
  }


  setInProgress(event) {
    console.log(event);
  }

  drop(event: CdkDragDrop<string[]>) {
    console.log('log', event);
    if (event.previousContainer === event.container) {

      this.messageService.add({
        severity: 'error',
        summary: 'Service Message',
        detail: 'Cannot move to this location'
      });
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  evenPredicate(name: string) {
    return (item: CdkDrag<any>) => {
      return name === item.data;
    };
  }

}

export interface Item {
  name: string;
  tableNo?: string;
  serviceTime?: string;
  specialInstruction?: string;
  quantity?: string;
  children?: Item[];
}
