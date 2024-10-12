import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageDepartementsComponent } from './manage-departements.component';

describe('ManageDepartementsComponent', () => {
  let component: ManageDepartementsComponent;
  let fixture: ComponentFixture<ManageDepartementsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ManageDepartementsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageDepartementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
