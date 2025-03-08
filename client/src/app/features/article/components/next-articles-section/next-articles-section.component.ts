import { Component, Input } from '@angular/core';
import { ArticleBlockComponent } from "../../../../shared/components/article-block/article-block.component";
import { Article } from '../../models/article.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-next-articles-section',
  standalone: true,
  imports: [ArticleBlockComponent, CommonModule],
  templateUrl: './next-articles-section.component.html',
  styleUrl: './next-articles-section.component.scss'
})
export class NextArticlesSectionComponent {
  @Input() articles!: Article[];

}
