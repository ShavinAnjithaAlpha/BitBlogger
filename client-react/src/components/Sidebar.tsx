import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import { TrendingUp, Users, Tag } from 'lucide-react';

const Sidebar = () => {
  const trendingTags = [
    { name: 'JavaScript', count: 1234 },
    { name: 'React', count: 987 },
    { name: 'TypeScript', count: 765 },
    { name: 'Web Development', count: 654 },
    { name: 'UI/UX', count: 432 },
    { name: 'Machine Learning', count: 321 },
  ];

  const suggestedAuthors = [
    { name: 'Sarah Chen', username: 'sarahc', followers: 12500, avatar: '/placeholder.svg' },
    { name: 'Mike Johnson', username: 'mikej', followers: 8900, avatar: '/placeholder.svg' },
    { name: 'Emily Davis', username: 'emilyd', followers: 6700, avatar: '/placeholder.svg' },
  ];

  return (
    <div className="space-y-6">
      {/* Trending Tags */}
      <Card className="bg-card/50 border-border/50">
        <CardHeader>
          <CardTitle className="flex items-center text-lg">
            <TrendingUp className="w-5 h-5 mr-2 text-primary" />
            Trending Tags
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-3">
          {trendingTags.map((tag) => (
            <div key={tag.name} className="flex items-center justify-between">
              <Badge variant="secondary" className="cursor-pointer hover:bg-primary/20">
                <Tag className="w-3 h-3 mr-1" />
                {tag.name}
              </Badge>
              <span className="text-sm text-muted-foreground">{tag.count}</span>
            </div>
          ))}
        </CardContent>
      </Card>

      {/* Suggested Authors */}
      <Card className="bg-card/50 border-border/50">
        <CardHeader>
          <CardTitle className="flex items-center text-lg">
            <Users className="w-5 h-5 mr-2 text-primary" />
            Suggested Authors
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          {suggestedAuthors.map((author) => (
            <div key={author.username} className="flex items-center justify-between">
              <div className="flex items-center space-x-3">
                <Avatar className="w-8 h-8">
                  <AvatarImage src={author.avatar} />
                  <AvatarFallback>{author.name.charAt(0)}</AvatarFallback>
                </Avatar>
                <div>
                  <div className="font-medium text-sm">{author.name}</div>
                  <div className="text-xs text-muted-foreground">
                    {author.followers.toLocaleString()} followers
                  </div>
                </div>
              </div>
              <Button size="sm" variant="outline">
                Follow
              </Button>
            </div>
          ))}
        </CardContent>
      </Card>
    </div>
  );
};

export default Sidebar;
