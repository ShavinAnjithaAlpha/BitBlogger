import { Component, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';
import { Comment } from '../../models/comment.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [CommentComponent, CommonModule],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.scss'
})
export class CommentComponent implements OnInit {
  @Input() comment!: Comment;
  @Input() divWidth: string = '900px';

  constructor(private renderer: Renderer2, private el: ElementRef) { }

  ngOnInit(): void {
    this.setWidth();
  }

  setWidth(): void {
    this.renderer.setStyle(this.el.nativeElement, 'width', this.divWidth);
  }
}
