import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablesOverviewComponent } from './tables-overview.component';

describe('TablesOverviewComponent', () => {
  let component: TablesOverviewComponent;
  let fixture: ComponentFixture<TablesOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablesOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablesOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
