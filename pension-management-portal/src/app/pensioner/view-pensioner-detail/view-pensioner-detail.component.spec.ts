import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPensionerDetailComponent } from './view-pensioner-detail.component';

describe('ViewPensionerDetailComponent', () => {
  let component: ViewPensionerDetailComponent;
  let fixture: ComponentFixture<ViewPensionerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPensionerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPensionerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
