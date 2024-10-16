import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpDepListComponent } from './emp-dep-list.component';

describe('EmpDepListComponent', () => {
  let component: EmpDepListComponent;
  let fixture: ComponentFixture<EmpDepListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmpDepListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmpDepListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
