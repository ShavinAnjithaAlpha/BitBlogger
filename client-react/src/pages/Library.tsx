import { useState } from 'react';
import Navbar from '@/components/NavBar';
import ArticleCard from '@/components/ArticleCard';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Search, Plus, BookOpen, Heart, Star, Filter } from 'lucide-react';

const Library = () => {
  const [searchQuery, setSearchQuery] = useState('');

  // Mock data for saved articles
  const savedArticles = [
    {
      id: '1',
      title: 'Understanding React Server Components',
      excerpt: 'A deep dive into the future of React development with server components.',
      content: 'Full article content...',
      author: {
        name: 'Alice Smith',
        username: 'alicesmith',
        avatar: '/placeholder.svg'
      },
      publishedAt: '3 days ago',
      readTime: 12,
      tags: ['React', 'Web Development', 'Server Components'],
      likes: 245,
      comments: 32,
      imageUrl: '/placeholder.svg'
    },
    {
      id: '2',
      title: 'The Art of API Design',
      excerpt: 'Best practices for creating maintainable and scalable APIs.',
      content: 'Full article content...',
      author: {
        name: 'Bob Johnson',
        username: 'bobjohnson',
        avatar: '/placeholder.svg'
      },
      publishedAt: '1 week ago',
      readTime: 8,
      tags: ['API', 'Backend', 'Design'],
      likes: 189,
      comments: 24
    }
  ];

  // Mock reading lists
  const readingLists = [
    {
      id: '1',
      name: 'React Learning Path',
      description: 'Articles to master React development',
      articleCount: 15,
      isPublic: true,
      createdAt: '2 weeks ago'
    },
    {
      id: '2',
      name: 'Design Inspiration',
      description: 'UI/UX articles and case studies',
      articleCount: 8,
      isPublic: false,
      createdAt: '1 month ago'
    },
    {
      id: '3',
      name: 'Backend Architecture',
      description: 'System design and backend patterns',
      articleCount: 12,
      isPublic: true,
      createdAt: '3 weeks ago'
    }
  ];

  return (
    <div className="min-h-screen bg-background">
      <Navbar isAuthenticated={true} />
      
      <div className="container mx-auto px-4 py-8">
        <div className="mb-8">
          <h1 className="text-4xl font-playfair font-bold mb-4 bg-gradient-to-r from-primary to-secondary bg-clip-text text-transparent">
            My Library
          </h1>
          <p className="text-muted-foreground text-lg">
            Organize and manage your saved articles and reading lists
          </p>
        </div>

        <Tabs defaultValue="saved" className="space-y-6">
          <TabsList className="bg-secondary/50 backdrop-blur-sm">
            <TabsTrigger value="saved" className="flex items-center gap-2">
              <BookOpen className="w-4 h-4" />
              Saved Articles
            </TabsTrigger>
            <TabsTrigger value="lists" className="flex items-center gap-2">
              <Star className="w-4 h-4" />
              Reading Lists
            </TabsTrigger>
            <TabsTrigger value="liked" className="flex items-center gap-2">
              <Heart className="w-4 h-4" />
              Liked Articles
            </TabsTrigger>
          </TabsList>

          <div className="flex flex-col md:flex-row gap-4 mb-6">
            <div className="relative flex-1">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-muted-foreground w-4 h-4" />
              <Input
                placeholder="Search your library..."
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="pl-10 bg-card/50 border-border/50"
              />
            </div>
            <Button variant="outline" className="flex items-center gap-2">
              <Filter className="w-4 h-4" />
              Filter
            </Button>
          </div>

          <TabsContent value="saved" className="space-y-6">
            <div className="grid gap-6">
              {savedArticles.map((article, index) => (
                <div key={article.id} className="animate-fade-in" style={{ animationDelay: `${index * 0.1}s` }}>
                  <ArticleCard article={article} />
                </div>
              ))}
            </div>
          </TabsContent>

          <TabsContent value="lists" className="space-y-6">
            <div className="flex justify-between items-center">
              <h2 className="text-2xl font-playfair font-semibold">Reading Lists</h2>
              <Button className="flex items-center gap-2">
                <Plus className="w-4 h-4" />
                Create List
              </Button>
            </div>
            
            <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
              {readingLists.map((list, index) => (
                <Card 
                  key={list.id} 
                  className="bg-card/50 border-border/50 backdrop-blur-sm hover:bg-card/70 transition-all duration-300 cursor-pointer animate-fade-in"
                  style={{ animationDelay: `${index * 0.1}s` }}
                >
                  <CardHeader>
                    <div className="flex justify-between items-start">
                      <CardTitle className="text-lg font-playfair">{list.name}</CardTitle>
                      <Badge variant={list.isPublic ? 'default' : 'secondary'}>
                        {list.isPublic ? 'Public' : 'Private'}
                      </Badge>
                    </div>
                    <p className="text-muted-foreground text-sm">{list.description}</p>
                  </CardHeader>
                  <CardContent>
                    <div className="flex justify-between items-center text-sm text-muted-foreground">
                      <span>{list.articleCount} articles</span>
                      <span>Created {list.createdAt}</span>
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          </TabsContent>

          <TabsContent value="liked" className="space-y-6">
            <div className="text-center py-12">
              <Heart className="w-12 h-12 text-muted-foreground mx-auto mb-4" />
              <h3 className="text-lg font-medium mb-2">No liked articles yet</h3>
              <p className="text-muted-foreground">Articles you like will appear here</p>
            </div>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  );
};

export default Library;
