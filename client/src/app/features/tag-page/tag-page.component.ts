import { Component, OnInit } from '@angular/core';
import { Tag } from '../../shared/models/tag.model';
import { TagCardComponent } from '../../shared/components/tag-card/tag-card.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tag-page',
  standalone: true,
  imports: [TagCardComponent, CommonModule],
  templateUrl: './tag-page.component.html',
  styleUrl: './tag-page.component.scss'
})
export class TagPageComponent implements OnInit {

  tags: Tag[] = [
    { id: 1, title: 'Angular', description: 'A framework for building client applications in HTML, CSS, and JavaScript/TypeScript.', image: 'https://angular.io/assets/images/logos/angular/angular.png', posts: 5455 },
    { id: 2, title: 'TypeScript', description: 'A typed superset of JavaScript that compiles to plain JavaScript.', image: 'https://upload.wikimedia.org/wikipedia/commons/4/4c/Typescript_logo_2020.svg', posts: 1234 },
    { id: 3, title: 'JavaScript', description: 'A programming language that conforms to the ECMAScript specification.', image: 'https://upload.wikimedia.org/wikipedia/commons/9/99/Unofficial_JavaScript_logo_2.svg', posts: 9876 },
    { id: 4, title: 'HTML', description: 'The standard markup language for documents designed to be displayed in a web browser.', image: 'https://upload.wikimedia.org/wikipedia/commons/6/61/HTML5_logo_and_wordmark.svg', posts: 6543 },
    { id: 5, title: 'CSS', description: 'A style sheet language used for describing the presentation of a document written in HTML or XML.', image: 'https://upload.wikimedia.org/wikipedia/commons/d/d5/CSS3_logo_and_wordmark.svg', posts: 3210 },
    { id: 7, title: 'Node.js', description: 'An open-source, cross-platform, back-end JavaScript runtime environment that runs on the V8 engine and executes JavaScript code outside a web browser.', image: 'https://upload.wikimedia.org/wikipedia/commons/d/d9/Node.js_logo.svg', posts: 7890 },
    { id: 8, title: 'Express.js', description: 'A web application framework for Node.js, released as free and open-source software under the MIT License.', image: 'https://upload.wikimedia.org/wikipedia/commons/6/64/Expressjs.png', posts: 2345 },
    { id: 9, title: 'MongoDB', description: 'A source-available cross-platform document-oriented database program.', image: 'https://upload.wikimedia.org/wikipedia/commons/9/93/MongoDB_Logo.svg', posts: 8765 },
    { id: 10, title: 'SQL', description: 'A domain-specific language used in programming and designed for managing data held in a relational database management system.', image: 'https://upload.wikimedia.org/wikipedia/commons/8/87/Sql_data_base_with_logo.png', posts: 5432 },
    { id: 11, title: 'Python', description: 'An interpreted high-level general-purpose programming language.', image: 'https://upload.wikimedia.org/wikipedia/commons/c/c3/Python-logo-notext.svg', posts: 2109 },
    { id: 12, title: 'Java', description: 'A class-based, object-oriented programming language.', image: 'https://upload.wikimedia.org/wikipedia/commons/5/52/Java_programming_language_logo.svg', posts: 8765 },
    { id: 13, title: 'Spring Boot', description: 'An open-source Java-based framework used to create micro-services.', image: 'https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg', posts: 5432 },
    { id: 14, title: 'Kotlin', description: 'A cross-platform, statically typed, general-purpose programming language with type inference.', image: 'https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin-logo.svg', posts: 2109 },
    { id: 15, title: 'Flutter', description: 'An open-source UI software development kit created by Google.', image: 'https://upload.wikimedia.org/wikipedia/commons/1/17/Google-flutter-logo.png', posts: 8765 },
    { id: 16, title: 'Dart', description: 'A programming language optimized for building mobile, desktop, server, and web applications.', image: 'https://upload.wikimedia.org/wikipedia/commons/7/7e/Dart-logo.png', posts: 5432 },
    { id: 17, title: 'React', description: 'A JavaScript library for building user interfaces.', image: 'https://upload.wikimedia.org/wikipedia/commons/a/a7/React-icon.svg', posts: 2109 },
    { id: 18, title: 'Vue.js', description: 'An open-source model–view–viewmodel front end JavaScript framework for building user interfaces and single-page applications.', image: 'https://upload.wikimedia.org/wikipedia/commons/5/53/Vue.js_Logo.svg', posts: 8765 },
  ];

  searchTerm: string = '';
  filteredTags: Tag[] = [];

  ngOnInit(): void {
    this.filteredTags = this.tags;
  }

  filterTags(): void {
    this.filteredTags = this.tags.filter(tag =>
      tag.title.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

}
