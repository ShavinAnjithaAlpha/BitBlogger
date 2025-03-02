import { Component } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

@Component({
  selector: 'app-article-editor',
  standalone: true,
  imports: [CKEditorModule],
  templateUrl: './article-editor.component.html',
  styleUrl: './article-editor.component.scss'
})
export class ArticleEditorComponent {
  public Editor = ClassicEditor;
  articleContent: string = '';
  editorConfig = {
    height: '400px'
  };
}
