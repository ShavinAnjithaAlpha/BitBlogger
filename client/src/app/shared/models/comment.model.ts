import { Author } from "../../features/article/models/author.model";

export interface Comment {
  id: number,
  postId: string,
  author: Author,
  content: string,
  createdAt: string,
  updatedAt: Date,
  likes: number,
  replies: Comment[]
}
