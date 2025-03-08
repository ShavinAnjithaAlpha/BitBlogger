import { Component, Input } from '@angular/core';
import { Author } from '../../models/author.model';

@Component({
  selector: 'app-comment-form',
  standalone: true,
  imports: [],
  templateUrl: './comment-form.component.html',
  styleUrl: './comment-form.component.scss'
})
export class CommentFormComponent {
  @Input() user!: Author;
  @Input() articleId!: string;


  addComment() {
    // Add comment
  }


}
