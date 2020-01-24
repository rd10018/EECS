import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockSoldComponent } from './stock-sold.component';

describe('StockSoldComponent', () => {
  let component: StockSoldComponent;
  let fixture: ComponentFixture<StockSoldComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockSoldComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockSoldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
