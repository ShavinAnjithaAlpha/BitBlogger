import { useState } from 'react';
import Navbar from '@/components/NavBar';
import ArticleCard from '@/components/ArticleCard';
import Sidebar from '@/components/Sidebar';
import { Button } from '@/components/ui/button';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Filter } from 'lucide-react';

const Home = () => {
  const [filterBy, setFilterBy] = useState('latest');

  // Mock data
  const articles = [
    {
      id: '1',
      title: 'Building Modern React Applications with TypeScript',
      excerpt: 'Learn how to create scalable and maintainable React applications using TypeScript, exploring best practices and advanced patterns.',
      content: 'Full article content...',
      author: {
        name: 'John Doe',
        username: 'johndoe',
        avatar: '/placeholder.svg'
      },
      publishedAt: '2 hours ago',
      readTime: 8,
      tags: ['React', 'TypeScript', 'Web Development'],
      likes: 142,
      comments: 23,
      imageUrl: 'https://images7.alphacoders.com/136/1365835.png'
    },
    {
      id: '2',
      title: 'The Future of Web Development: Trends to Watch in 2024',
      excerpt: 'Explore the emerging trends and technologies that will shape web development in the coming year.',
      content: 'Full article content...',
      author: {
        name: 'Jane Smith',
        username: 'janesmith',
        avatar: '/placeholder.svg'
      },
      publishedAt: '5 hours ago',
      readTime: 12,
      tags: ['Web Development', 'Trends', 'Technology'],
      likes: 89,
      comments: 15
    },
    {
      id: '3',
      title: 'Mastering CSS Grid: A Complete Guide',
      excerpt: 'Deep dive into CSS Grid layout system and learn how to create complex, responsive layouts with ease.',
      content: 'Full article content...',
      author: {
        name: 'Alex Johnson',
        username: 'alexj',
        avatar: '/placeholder.svg'
      },
      publishedAt: '1 day ago',
      readTime: 15,
      tags: ['CSS', 'Frontend', 'Layout'],
      likes: 234,
      comments: 41,
      imageUrl: 'https://lede-admin.aftermath.site/wp-content/uploads/sites/55/2023/12/image-3.png'
    }
  ];

  return (
    <div className="min-h-screen bg-background">
      <Navbar isAuthenticated={true} />
      
      <div className="container mx-auto px-4 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
          {/* Main Content */}
          <div className="lg:col-span-3 space-y-6">
            {/* Header */}
            <div className="flex items-center justify-between">
              <div>
                <h1 className="text-3xl font-montserrat font-bold text-gradient mb-2">
                  Latest Articles
                </h1>
                <p className="text-muted-foreground">
                  Discover amazing stories and insights from our community
                </p>
              </div>
              
              <Button variant="outline" size="sm">
                <Filter className="w-4 h-4 mr-2" />
                Filter
              </Button>
            </div>

            {/* Filter Tabs */}
            <Tabs value={filterBy} onValueChange={setFilterBy}>
              <TabsList className="bg-secondary/50">
                <TabsTrigger value="latest">Latest</TabsTrigger>
                <TabsTrigger value="trending">Trending</TabsTrigger>
                <TabsTrigger value="following">Following</TabsTrigger>
                <TabsTrigger value="bookmarks">Bookmarks</TabsTrigger>
              </TabsList>

              <TabsContent value={filterBy} className="space-y-6 mt-6">
                {articles.map((article, index) => (
                  <div key={article.id} className="animate-fade-in" style={{ animationDelay: `${index * 0.1}s` }}>
                    <ArticleCard article={article} />
                  </div>
                ))}
              </TabsContent>
            </Tabs>
          </div>

          {/* Sidebar */}
          <div className="lg:col-span-1">
            <div className="sticky top-24">
              <Sidebar />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
