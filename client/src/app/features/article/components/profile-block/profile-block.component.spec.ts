import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileBlockComponent } from './profile-block.component';

describe('ProfileBlockComponent', () => {
  let component: ProfileBlockComponent;
  let fixture: ComponentFixture<ProfileBlockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfileBlockComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileBlockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
