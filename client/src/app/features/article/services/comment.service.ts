import { Injectable } from "@angular/core";
import { Comment } from "../../../shared/models/comment.model";
import { Observable, of } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private comments: Comment[] = [
    {
      id: 21,
      postId: "2",
      author: {
        id: 1,
        username: 'johnd',
        firstname: 'John',
        lastname: 'Doe',
        email: 'johndoe@gmail.com',
        avatar: 'https://img.freepik.com/free-photo/portrait',

      },
      content: 'Great article! I really enjoyed reading it.',
      createdAt: 'Aug 24, 2025',
      updatedAt: new Date(),
      likes: 12,
      replies: [
        {
          id: 21,
          postId: "4fd45fd",
          author: {
            id: 1,
            username: 'johnd',
            firstname: 'John',
            lastname: 'Doe',
            email: 'johndoe@gmail.com',
            avatar: 'https://img.freepik.com/free-photo/portrait',

          },
          content: 'Great article! I really enjoyed reading it.',
          createdAt: 'Aug 24, 2025',
          updatedAt: new Date(),
          likes: 12,
          replies: []
        },
        {
          id: 23,
          postId: "4fd45fd",
          author: {
            id: 1,
            username: 'johnd',
            firstname: 'John',
            lastname: 'Doe',
            email: 'johndoe@gmail.com',
            avatar: 'https://img.freepik.com/free-photo/portrait',

          },
          content: 'Great article! I really enjoyed reading it.',
          createdAt: 'Aug 24, 2025',
          updatedAt: new Date(),
          likes: 12,
          replies: []
        }
      ]
    },

    {
      id: 21,
      postId: "2",
      author: {
        id: 1,
        username: 'johnd',
        firstname: 'John',
        lastname: 'Doe',
        email: 'johndoe@gmail.com',
        avatar: 'https://img.freepik.com/free-photo/portrait',

      },
      content: 'Great article! I really enjoyed reading it.',
      createdAt: 'Aug 24, 2025',
      updatedAt: new Date(),
      likes: 12,
      replies: [
        {
          id: 21,
          postId: "4fd45fd",
          author: {
            id: 1,
            username: 'johnd',
            firstname: 'John',
            lastname: 'Doe',
            email: 'johndoe@gmail.com',
            avatar: 'https://img.freepik.com/free-photo/portrait',

          },
          content: 'Great article! I really enjoyed reading it.',
          createdAt: 'Aug 24, 2025',
          updatedAt: new Date(),
          likes: 12,
          replies: []
        }
      ]
    },
    {
      id: 21,
      postId: "2",
      author: {
        id: 1,
        username: 'johnd',
        firstname: 'John',
        lastname: 'Doe',
        email: 'johndoe@gmail.com',
        avatar: 'https://img.freepik.com/free-photo/portrait',

      },
      content: 'Great article! I really enjoyed reading it.',
      createdAt: 'Aug 24, 2025',
      updatedAt: new Date(),
      likes: 12,
      replies: [
        {
          id: 21,
          postId: "4fd45fd",
          author: {
            id: 1,
            username: 'johnd',
            firstname: 'John',
            lastname: 'Doe',
            email: 'johndoe@gmail.com',
            avatar: 'https://img.freepik.com/free-photo/portrait',

          },
          content: 'Great article! I really enjoyed reading it.',
          createdAt: 'Aug 24, 2025',
          updatedAt: new Date(),
          likes: 12,
          replies: []
        }
      ]
    }
  ]

  getCommentsByPostId(postId: string): Observable<Comment[]> {
    const comments = this.comments.filter((comment) => comment.postId === postId);
    return of(comments);
  }
}
