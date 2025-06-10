import { useState } from 'react';
import Navbar from '@/components/NavBar';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { Input } from '@/components/ui/input';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Search, Code, Database, Palette, Smartphone, Globe, Cpu, Users, Check } from 'lucide-react';

interface Tag {
  id: string;
  name: string;
  description: string;
  icon: React.ComponentType<{ className?: string }>;
  followerCount: number;
  articleCount: number;
  isFollowing: boolean;
  color: string;
}

const Tags = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [followedTags, setFollowedTags] = useState<Set<string>>(new Set(['react', 'typescript']));

  const allTags: Tag[] = [
    {
      id: 'react',
      name: 'React',
      description: 'A JavaScript library for building user interfaces. Learn about components, hooks, state management, and modern React patterns.',
      icon: Code,
      followerCount: 125400,
      articleCount: 3200,
      isFollowing: true,
      color: 'bg-blue-500/20 text-blue-400 border-blue-500/30'
    },
    {
      id: 'typescript',
      name: 'TypeScript',
      description: 'TypeScript is a typed superset of JavaScript that compiles to plain JavaScript. Discover type safety and enhanced developer experience.',
      icon: Code,
      followerCount: 89300,
      articleCount: 2100,
      isFollowing: true,
      color: 'bg-blue-600/20 text-blue-300 border-blue-600/30'
    },
    {
      id: 'database',
      name: 'Database',
      description: 'Everything about databases - SQL, NoSQL, design patterns, optimization, and data modeling best practices.',
      icon: Database,
      followerCount: 67800,
      articleCount: 1800,
      isFollowing: false,
      color: 'bg-green-500/20 text-green-400 border-green-500/30'
    },
    {
      id: 'design',
      name: 'UI/UX Design',
      description: 'User interface and user experience design principles, tools, trends, and methodologies for creating exceptional digital experiences.',
      icon: Palette,
      followerCount: 94200,
      articleCount: 2800,
      isFollowing: false,
      color: 'bg-pink-500/20 text-pink-400 border-pink-500/30'
    },
    {
      id: 'mobile',
      name: 'Mobile Development',
      description: 'Mobile app development for iOS and Android. React Native, Flutter, Swift, Kotlin, and cross-platform solutions.',
      icon: Smartphone,
      followerCount: 56700,
      articleCount: 1400,
      isFollowing: false,
      color: 'bg-purple-500/20 text-purple-400 border-purple-500/30'
    },
    {
      id: 'web-dev',
      name: 'Web Development',
      description: 'Frontend and backend web development. HTML, CSS, JavaScript, frameworks, and modern web technologies.',
      icon: Globe,
      followerCount: 156900,
      articleCount: 4200,
      isFollowing: false,
      color: 'bg-orange-500/20 text-orange-400 border-orange-500/30'
    },
    {
      id: 'ai-ml',
      name: 'AI & Machine Learning',
      description: 'Artificial Intelligence and Machine Learning concepts, algorithms, frameworks, and practical applications.',
      icon: Cpu,
      followerCount: 78500,
      articleCount: 1900,
      isFollowing: false,
      color: 'bg-red-500/20 text-red-400 border-red-500/30'
    },
    {
      id: 'career',
      name: 'Career Development',
      description: 'Professional growth, career advice, interview tips, skill development, and workplace insights for developers.',
      icon: Users,
      followerCount: 43200,
      articleCount: 1100,
      isFollowing: false,
      color: 'bg-indigo-500/20 text-indigo-400 border-indigo-500/30'
    }
  ];

  const filteredTags = allTags.filter(tag =>
    tag.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
    tag.description.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleToggleFollow = (tagId: string) => {
    setFollowedTags(prev => {
      const newSet = new Set(prev);
      if (newSet.has(tagId)) {
        newSet.delete(tagId);
      } else {
        newSet.add(tagId);
      }
      return newSet;
    });
  };

  const formatNumber = (num: number) => {
    if (num >= 1000) {
      return (num / 1000).toFixed(1) + 'k';
    }
    return num.toString();
  };

  return (
    <div className="min-h-screen bg-background">
      <Navbar isAuthenticated={true} />
      
      <div className="container mx-auto px-4 py-8">
        {/* Header */}
        <div className="mb-8 animate-fade-in">
          <h1 className="text-4xl font-playfair font-bold mb-4">Explore Tags</h1>
          <p className="text-muted-foreground text-lg mb-6">
            Discover topics you're interested in and follow them to see related articles in your feed.
          </p>
          
          {/* Search */}
          <div className="relative max-w-md">
            <Search className="absolute left-3 top-3 h-4 w-4 text-muted-foreground" />
            <Input
              placeholder="Search tags..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="pl-10"
            />
          </div>
        </div>

        {/* Following Summary */}
        {followedTags.size > 0 && (
          <div className="mb-8 p-6 bg-card/50 rounded-lg border border-border/50 animate-scale-in">
            <h2 className="text-xl font-semibold mb-3">Following ({followedTags.size})</h2>
            <div className="flex flex-wrap gap-2">
              {allTags
                .filter(tag => followedTags.has(tag.id))
                .map((tag) => (
                  <Badge key={tag.id} variant="secondary" className="px-3 py-1">
                    <tag.icon className="w-3 h-3 mr-1" />
                    {tag.name}
                  </Badge>
                ))}
            </div>
          </div>
        )}

        {/* Tags Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 animate-slide-up">
          {filteredTags.map((tag) => {
            const isFollowing = followedTags.has(tag.id);
            const IconComponent = tag.icon;
            
            return (
              <Card key={tag.id} className="bg-card/50 border-border/50 hover:glow transition-all duration-300 group">
                <CardHeader className="pb-4">
                  <div className="flex items-start justify-between">
                    <div className={`p-3 rounded-lg ${tag.color} group-hover:scale-110 transition-transform duration-300`}>
                      <IconComponent className="w-6 h-6" />
                    </div>
                    <Button
                      variant={isFollowing ? "default" : "outline"}
                      size="sm"
                      onClick={() => handleToggleFollow(tag.id)}
                      className={isFollowing ? "bg-primary text-primary-foreground" : ""}
                    >
                      {isFollowing ? (
                        <>
                          <Check className="w-3 h-3 mr-1" />
                          Following
                        </>
                      ) : (
                        "Follow"
                      )}
                    </Button>
                  </div>
                  <CardTitle className="text-xl">{tag.name}</CardTitle>
                  <CardDescription className="text-sm leading-relaxed">
                    {tag.description}
                  </CardDescription>
                </CardHeader>
                <CardContent className="pt-0">
                  <div className="flex items-center justify-between text-sm text-muted-foreground">
                    <span>{formatNumber(tag.followerCount)} followers</span>
                    <span>{formatNumber(tag.articleCount)} articles</span>
                  </div>
                </CardContent>
              </Card>
            );
          })}
        </div>

        {filteredTags.length === 0 && (
          <div className="text-center py-12 animate-fade-in">
            <Search className="w-12 h-12 text-muted-foreground mx-auto mb-4" />
            <h3 className="text-lg font-semibold mb-2">No tags found</h3>
            <p className="text-muted-foreground">
              Try adjusting your search terms to find the tags you're looking for.
            </p>
          </div>
        )}
      </div>
    </div>
  );
};

export default Tags;
