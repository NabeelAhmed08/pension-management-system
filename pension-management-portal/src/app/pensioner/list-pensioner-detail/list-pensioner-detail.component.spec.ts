import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPensionerDetailComponent } from './list-pensioner-detail.component';

describe('ListPensionerDetailComponent', () => {
  let component: ListPensionerDetailComponent;
  let fixture: ComponentFixture<ListPensionerDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListPensionerDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPensionerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
