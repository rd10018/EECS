import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionScoreComponent } from './session-score.component';

describe('SessionScoreComponent', () => {
  let component: SessionScoreComponent;
  let fixture: ComponentFixture<SessionScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SessionScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SessionScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
