import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { ProfileBlockComponent } from "../profile-block/profile-block.component";
import { FullArticle } from '../../models/fullarticle.model';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from '../../services/article.service';
import { TagComponent } from "../../../../shared/components/tag/tag.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-article-page',
  standalone: true,
  imports: [ProfileBlockComponent, TagComponent, CommonModule],
  templateUrl: './article-page.component.html',
  styleUrl: './article-page.component.scss',
  encapsulation: ViewEncapsulation.None
})
export class ArticlePageComponent implements OnInit {
  article!: FullArticle;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) { }

  ngOnInit(): void {
    const articleId = this.route.snapshot.paramMap.get('id');
    if (articleId) {
      this.articleService.getArticleById(articleId).subscribe((article) => {
        this.article = article;
      })
    }
  }
}
