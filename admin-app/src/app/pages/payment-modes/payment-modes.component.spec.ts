import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentModesComponent } from './accounts.component';

describe('AccountsComponent', () => {
  let component: PaymentModesComponent;
  let fixture: ComponentFixture<PaymentModesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymentModesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymentModesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
