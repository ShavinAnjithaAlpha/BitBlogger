import { Component } from '@angular/core';
import { ArticleCardComponent } from "../../../article/components/article-card/article-card.component";
import { Article } from '../../../article/models/article.model';
import { ProfileCardComponent } from '../../../article/components/profile-card/profile-card.component';
import { ProfileBlockComponent } from '../../../article/components/profile-block/profile-block.component';
import { CommonModule } from '@angular/common';
import { TagComponent } from '../../../../shared/components/tag/tag.component';
import { Tag } from '../../../../shared/models/tag.model';
import { TagCardComponent } from '../../../../shared/components/tag-card/tag-card.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ArticleCardComponent, ProfileCardComponent, ProfileBlockComponent, CommonModule, TagComponent, TagCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  tags: string[] = ['Technology', 'Quantum Computing', 'Privacy', 'Security', 'Future'];

  articles: Article[] = [
    {
      id: '1',
      author: {
        id: 1,
        username: 'johndoe',
        firstname: 'John',
        lastname: 'Doe',
        email: 'john.doe@example.com',
        avatar: 'https://img.freepik.com/free-photo/portrait-white-man-isolated_53876-40306.jpg'
      },
      title: 'Trusted Computing and Digital Privacy',
      preview: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. In ex recusandae voluptate reiciendis numquam excepturi eaque obcaecati non mollitia a...',
      coverImage: 'https://wallpapers.com/images/featured/vector-art-6ttd2h971c0ivqyh.jpg',
      publishedAt: 'Aug 24, 2025',
      tags: ['Computing', 'Privacy', 'Security'],
      likes: 47,
      comments: 455
    },
    {
      id: '2',
      author: {
        id: 2,
        username: 'janesmith',
        firstname: 'Jane',
        lastname: 'Smith',
        email: 'jane.smith@example.com',
        avatar: 'https://img.freepik.com/free-photo/portrait-white-woman-isolated_53876-40306.jpg'
      },
      title: 'The Future of Quantum Computing',
      preview: 'Quantum computing is poised to revolutionize the tech industry. In this article, we explore the potential applications and challenges of this emerging technology...',
      coverImage: 'https://wallpapers.com/images/featured/quantum-computing-6ttd2h971c0ivqyh.jpg',
      publishedAt: 'Sep 10, 2025',
      tags: ['Quantum Computing', 'Technology', 'Future'],
      likes: 89,
      comments: 123
    }
  ];

}
