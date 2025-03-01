import { Component, ElementRef, Input, OnInit, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-tag',
  standalone: true,
  imports: [],
  templateUrl: './tag.component.html',
  styleUrl: './tag.component.scss'
})
export class TagComponent implements OnInit {
  @Input() title!: string;

  constructor(private renderer: Renderer2, private el: ElementRef) { }

  ngOnInit(): void {
    this.setRandomColor();
  }

  setRandomColor() {
    const colors = ['#FF5733', '#33FF57', '#3357FF', '#F333FF', '#FF33A1'];
    const randomColor = colors[Math.floor(Math.random() * colors.length)];
    this.renderer.setStyle(this.el.nativeElement, 'color', randomColor);
    this.renderer.setStyle(this.el.nativeElement, 'border-color', randomColor);

    // Create a dynamic style element
    const style = this.renderer.createElement('style');
    style.innerHTML = `
        .tag:hover {
          background-color: ${randomColor}1A;
        }
      `;
    this.renderer.appendChild(this.el.nativeElement, style);
  }

  onCloseClick() {
    console.log('close clicked');
  }
}
