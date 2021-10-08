import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuisnessAnalyticsComponent } from './dashboard.component';

describe('DashboardComponent', () => {
  let component: BuisnessAnalyticsComponent;
  let fixture: ComponentFixture<BuisnessAnalyticsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuisnessAnalyticsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuisnessAnalyticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
