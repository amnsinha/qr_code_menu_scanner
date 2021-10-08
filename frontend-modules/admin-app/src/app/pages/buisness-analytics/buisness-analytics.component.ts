import {Component, OnInit} from '@angular/core';
import Chart from 'chart.js';

// core components
import {
  chartOptions,
  parseOptions,
  chartExample1,
  chartExample2
} from '../../variables/charts';

@Component({
  selector: 'app-buisness-analytics',
  templateUrl: './buisness-analytics.component.html',
  styleUrls: ['./buisness-analytics.component.scss']
})
export class BuisnessAnalyticsComponent implements OnInit {

  public datasets: any;
  public data: any;
  public salesChart;
  public clicked = true;
  public clicked1 = false;

  constructor() {
  }

  ngOnInit() {}

}
