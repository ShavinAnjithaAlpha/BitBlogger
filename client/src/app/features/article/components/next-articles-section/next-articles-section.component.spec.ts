import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NextArticlesSectionComponent } from './next-articles-section.component';

describe('NextArticlesSectionComponent', () => {
  let component: NextArticlesSectionComponent;
  let fixture: ComponentFixture<NextArticlesSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NextArticlesSectionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NextArticlesSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
