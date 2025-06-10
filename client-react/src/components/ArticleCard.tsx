import { Card, CardContent } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import { Heart, MessageCircle, Bookmark, Share } from 'lucide-react';
import { Link } from 'react-router-dom';

interface ArticleCardProps {
  article: {
    id: string;
    title: string;
    excerpt: string;
    content: string;
    author: {
      name: string;
      avatar: string;
      username: string;
    };
    publishedAt: string;
    readTime: number;
    tags: string[];
    likes: number;
    comments: number;
    imageUrl?: string;
  };
}

const ArticleCard = ({ article }: ArticleCardProps) => {
  return (
    <Card className="group hover:glow transition-all duration-300 bg-card/50 border-border/50 overflow-hidden">
      {article.imageUrl && (
        <div className="aspect-video overflow-hidden">
          <img 
            src={article.imageUrl} 
            alt={article.title}
            className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-300"
          />
        </div>
      )}
      
      <CardContent className="p-6">
        <div className="flex items-center justify-between mb-4">
          <div className="flex items-center space-x-3">
            <Avatar className="w-8 h-8">
              <AvatarImage src={article.author.avatar} />
              <AvatarFallback>{article.author.name.charAt(0)}</AvatarFallback>
            </Avatar>
            <div>
              <Link 
                to={`/author/${article.author.username}`}
                className="text-sm font-medium hover:text-primary transition-colors"
              >
                {article.author.name}
              </Link>
              <div className="text-xs text-muted-foreground">
                {article.publishedAt} · {article.readTime} min read
              </div>
            </div>
          </div>
        </div>

        <Link to={`/article/${article.id}`}>
          <h3 className="text-xl font-playfair font-semibold mb-3 group-hover:text-primary transition-colors line-clamp-2">
            {article.title}
          </h3>
          <p className="text-muted-foreground mb-4 line-clamp-3">
            {article.excerpt}
          </p>
        </Link>

        <div className="flex flex-wrap gap-2 mb-4">
          {article.tags.slice(0, 3).map((tag) => (
            <Badge key={tag} variant="secondary" className="text-xs">
              {tag}
            </Badge>
          ))}
        </div>

        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-4">
            <Button variant="ghost" size="sm" className="p-2 h-auto">
              <Heart className="w-4 h-4 mr-1" />
              <span className="text-sm">{article.likes}</span>
            </Button>
            <Button variant="ghost" size="sm" className="p-2 h-auto">
              <MessageCircle className="w-4 h-4 mr-1" />
              <span className="text-sm">{article.comments}</span>
            </Button>
          </div>
          
          <div className="flex items-center space-x-2">
            <Button variant="ghost" size="sm" className="p-2 h-auto">
              <Bookmark className="w-4 h-4" />
            </Button>
            <Button variant="ghost" size="sm" className="p-2 h-auto">
              <Share className="w-4 h-4" />
            </Button>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default ArticleCard;
