import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffPanelComponent } from './staff-panel.component';

describe('ChefPanelComponent', () => {
  let component: StaffPanelComponent;
  let fixture: ComponentFixture<StaffPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaffPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
