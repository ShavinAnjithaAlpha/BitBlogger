import { Author } from "./author.model";

export interface Article {
  id: string,
  author: Author,
  title: string,
  preview: string,
  coverImage: string,
  publishedAt: string,
  tags: string[],
  likes: number,
  comments: number,
}
