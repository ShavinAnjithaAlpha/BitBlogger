import { Component, Input } from '@angular/core';
import { TagComponent } from "../tag/tag.component";
import { Tag } from '../../models/tag.model';

@Component({
  selector: 'app-tag-card',
  standalone: true,
  imports: [TagComponent],
  templateUrl: './tag-card.component.html',
  styleUrl: './tag-card.component.scss'
})
export class TagCardComponent {
  @Input() tag!: Tag;
}
