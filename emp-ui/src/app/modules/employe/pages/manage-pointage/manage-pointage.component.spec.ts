import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagePointageComponent } from './manage-pointage.component';

describe('ManagePointageComponent', () => {
  let component: ManagePointageComponent;
  let fixture: ComponentFixture<ManagePointageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManagePointageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManagePointageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
