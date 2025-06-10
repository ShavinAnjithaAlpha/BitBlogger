import Navbar from '@/components/NavBar';
import ArticleCard from '@/components/ArticleCard';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { MapPin, Calendar, Link as LinkIcon, Users, BookOpen } from 'lucide-react';

const Profile = () => {
  // Mock user data
  const user = {
    name: 'John Doe',
    username: 'johndoe',
    avatar: '/placeholder.svg',
    bio: 'Senior Frontend Developer passionate about React, TypeScript, and modern web technologies. Sharing knowledge through code and stories.',
    location: 'San Francisco, CA',
    website: 'johndoe.dev',
    joinedDate: 'January 2022',
    followers: 1250,
    following: 324,
    articles: 47
  };

  // Mock articles data
  const articles = [
    {
      id: '1',
      title: 'Building Modern React Applications with TypeScript',
      excerpt: 'Learn how to create scalable and maintainable React applications using TypeScript.',
      content: 'Full article content...',
      author: user,
      publishedAt: '2 hours ago',
      readTime: 8,
      tags: ['React', 'TypeScript', 'Web Development'],
      likes: 142,
      comments: 23,
      imageUrl: '/placeholder.svg'
    },
    {
      id: '2',
      title: 'The Future of Web Development',
      excerpt: 'Explore the emerging trends and technologies that will shape web development.',
      content: 'Full article content...',
      author: user,
      publishedAt: '1 day ago',
      readTime: 12,
      tags: ['Web Development', 'Trends', 'Technology'],
      likes: 89,
      comments: 15
    }
  ];

  return (
    <div className="min-h-screen bg-background">
      <Navbar isAuthenticated={true} />
      
      <div className="container mx-auto px-4 py-8">
        {/* Profile Header */}
        <div className="bg-card/50 rounded-lg border border-border/50 p-8 mb-8 animate-fade-in">
          <div className="flex flex-col md:flex-row items-start md:items-center space-y-6 md:space-y-0 md:space-x-8">
            <Avatar className="w-24 h-24">
              <AvatarImage src={user.avatar} />
              <AvatarFallback className="text-2xl">{user.name.charAt(0)}</AvatarFallback>
            </Avatar>
            
            <div className="flex-1">
              <h1 className="text-3xl font-playfair font-bold mb-2">{user.name}</h1>
              <p className="text-xl text-muted-foreground mb-4">@{user.username}</p>
              <p className="text-muted-foreground mb-4 max-w-2xl">{user.bio}</p>
              
              <div className="flex flex-wrap items-center gap-4 text-sm text-muted-foreground mb-4">
                <div className="flex items-center">
                  <MapPin className="w-4 h-4 mr-1" />
                  {user.location}
                </div>
                <div className="flex items-center">
                  <LinkIcon className="w-4 h-4 mr-1" />
                  <a href={`https://${user.website}`} className="text-primary hover:text-primary/80">
                    {user.website}
                  </a>
                </div>
                <div className="flex items-center">
                  <Calendar className="w-4 h-4 mr-1" />
                  Joined {user.joinedDate}
                </div>
              </div>

              <div className="flex items-center space-x-6 mb-4">
                <div className="flex items-center space-x-1">
                  <Users className="w-4 h-4" />
                  <span className="font-medium">{user.followers.toLocaleString()}</span>
                  <span className="text-muted-foreground">followers</span>
                </div>
                <div className="flex items-center space-x-1">
                  <span className="font-medium">{user.following.toLocaleString()}</span>
                  <span className="text-muted-foreground">following</span>
                </div>
                <div className="flex items-center space-x-1">
                  <BookOpen className="w-4 h-4" />
                  <span className="font-medium">{user.articles}</span>
                  <span className="text-muted-foreground">articles</span>
                </div>
              </div>
            </div>

            <div className="flex space-x-3">
              <Button>Follow</Button>
              <Button variant="outline">Message</Button>
            </div>
          </div>
        </div>

        {/* Content Tabs */}
        <Tabs defaultValue="articles" className="space-y-6">
          <TabsList className="bg-secondary/50">
            <TabsTrigger value="articles">Articles</TabsTrigger>
            <TabsTrigger value="drafts">Drafts</TabsTrigger>
            <TabsTrigger value="liked">Liked</TabsTrigger>
            <TabsTrigger value="about">About</TabsTrigger>
          </TabsList>

          <TabsContent value="articles" className="space-y-6">
            <div className="grid gap-6">
              {articles.map((article, index) => (
                <div key={article.id} className="animate-fade-in" style={{ animationDelay: `${index * 0.1}s` }}>
                  <ArticleCard article={article} />
                </div>
              ))}
            </div>
          </TabsContent>

          <TabsContent value="drafts" className="space-y-6">
            <div className="text-center py-12">
              <BookOpen className="w-12 h-12 text-muted-foreground mx-auto mb-4" />
              <h3 className="text-lg font-medium mb-2">No drafts yet</h3>
              <p className="text-muted-foreground">Start writing your next article</p>
            </div>
          </TabsContent>

          <TabsContent value="liked" className="space-y-6">
            <div className="text-center py-12">
              <BookOpen className="w-12 h-12 text-muted-foreground mx-auto mb-4" />
              <h3 className="text-lg font-medium mb-2">No liked articles yet</h3>
              <p className="text-muted-foreground">Articles you like will appear here</p>
            </div>
          </TabsContent>

          <TabsContent value="about" className="space-y-6">
            <div className="bg-card/50 rounded-lg border border-border/50 p-6">
              <h3 className="text-lg font-semibold mb-4">About {user.name}</h3>
              <p className="text-muted-foreground mb-6">{user.bio}</p>
              
              <div className="space-y-4">
                <div>
                  <h4 className="font-medium mb-2">Interests</h4>
                  <div className="flex flex-wrap gap-2">
                    {['React', 'TypeScript', 'Web Development', 'UI/UX', 'JavaScript'].map((interest) => (
                      <Badge key={interest} variant="secondary">{interest}</Badge>
                    ))}
                  </div>
                </div>
                
                <div>
                  <h4 className="font-medium mb-2">Skills</h4>
                  <div className="flex flex-wrap gap-2">
                    {['Frontend Development', 'React', 'TypeScript', 'Node.js', 'GraphQL'].map((skill) => (
                      <Badge key={skill} variant="outline">{skill}</Badge>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  );
};

export default Profile;
