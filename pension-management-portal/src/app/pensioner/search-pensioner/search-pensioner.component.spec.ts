import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPensionerComponent } from './search-pensioner.component';

describe('SearchPensionerComponent', () => {
  let component: SearchPensionerComponent;
  let fixture: ComponentFixture<SearchPensionerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchPensionerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPensionerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
