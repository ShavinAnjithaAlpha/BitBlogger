import { Component, Input } from '@angular/core';
import { Article } from '../../../features/article/models/article.model';

@Component({
  selector: 'app-article-block',
  standalone: true,
  imports: [],
  templateUrl: './article-block.component.html',
})
export class ArticleBlockComponent {
  @Input() article!: Article;

}
