import { Author } from "./author.model";

export interface FullArticle {
  id: string,
  title: string,
  content: string,
  preview: string,
  coverImage: string,
  publishedAt: string,
  author: Author,
  tags: string[],
  likes: number,
  comments: number
  views: number
}
