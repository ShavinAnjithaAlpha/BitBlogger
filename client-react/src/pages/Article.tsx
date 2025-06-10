import { useState } from 'react';
import { useParams } from 'react-router-dom';
import Navbar from '@/components/NavBar';
import CommentSection from '@/components/CommentSection';
import Poll from '@/components/Poll';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { Separator } from '@/components/ui/separator';
import { Heart, Share2, Bookmark, Eye, Calendar, Clock } from 'lucide-react';

const Article = () => {
  const { id } = useParams();
  const [isLiked, setIsLiked] = useState(false);
  const [isBookmarked, setIsBookmarked] = useState(false);

  // Mock article data
  const article = {
    id: id || '1',
    title: 'Building Modern React Applications with TypeScript',
    content: `
      <p>React has evolved significantly over the years, and with the introduction of TypeScript, developers now have powerful tools to build robust, scalable applications. In this comprehensive guide, we'll explore the best practices for combining React with TypeScript to create maintainable codebases.</p>
      
      <h2>Why TypeScript with React?</h2>
      <p>TypeScript brings static type checking to JavaScript, which helps catch errors early in the development process. When combined with React, it provides better IntelliSense, refactoring capabilities, and overall developer experience.</p>
      
      <h2>Setting Up Your Development Environment</h2>
      <p>Let's start by setting up a new React project with TypeScript support. The easiest way is to use Create React App with the TypeScript template:</p>
      
      <pre><code>npx create-react-app my-app --template typescript</code></pre>
      
      <h2>Component Patterns</h2>
      <p>One of the most important aspects of React development is understanding component patterns. With TypeScript, we can define clear interfaces for our component props, making our code more predictable and easier to maintain.</p>
    `,
    author: {
      name: 'John Doe',
      username: 'johndoe',
      avatar: '/placeholder.svg',
      bio: 'Senior Frontend Developer passionate about React and TypeScript'
    },
    publishedAt: '2 hours ago',
    readTime: 8,
    tags: ['React', 'TypeScript', 'Web Development'],
    likes: 142,
    views: 1250,
    comments: 23,
    imageUrl: '/placeholder.svg'
  };

  // Mock poll data
  const pollData = {
    id: 'poll-1',
    question: 'Which React pattern do you prefer for state management?',
    options: [
      { id: 'context', text: 'React Context', votes: 45 },
      { id: 'redux', text: 'Redux Toolkit', votes: 32 },
      { id: 'zustand', text: 'Zustand', votes: 28 },
      { id: 'jotai', text: 'Jotai', votes: 15 }
    ],
    totalVotes: 120,
    timeLeft: '5 days left',
    hasVoted: false
  };

  return (
    <div className="min-h-screen bg-background">
      <Navbar isAuthenticated={true} />
      
      <article className="container mx-auto px-4 py-8 max-w-4xl">
        {/* Article Header */}
        <div className="mb-8 animate-fade-in">
          <div className="mb-6">
            {article.imageUrl && (
              <img 
                src={article.imageUrl} 
                alt={article.title}
                className="w-full h-64 md:h-80 object-cover rounded-lg border border-border/50"
              />
            )}
          </div>
          
          <div className="flex flex-wrap gap-2 mb-4">
            {article.tags.map((tag) => (
              <Badge key={tag} variant="secondary" className="hover:bg-secondary/80 transition-colors">
                {tag}
              </Badge>
            ))}
          </div>
          
          <h1 className="text-4xl md:text-5xl font-playfair font-bold mb-6 leading-tight">
            {article.title}
          </h1>
          
          <div className="flex items-center justify-between flex-wrap gap-4 mb-6">
            <div className="flex items-center space-x-4">
              <Avatar className="w-12 h-12">
                <AvatarImage src={article.author.avatar} />
                <AvatarFallback>{article.author.name.charAt(0)}</AvatarFallback>
              </Avatar>
              <div>
                <p className="font-medium">{article.author.name}</p>
                <div className="flex items-center space-x-4 text-sm text-muted-foreground">
                  <span className="flex items-center">
                    <Calendar className="w-3 h-3 mr-1" />
                    {article.publishedAt}
                  </span>
                  <span className="flex items-center">
                    <Clock className="w-3 h-3 mr-1" />
                    {article.readTime} min read
                  </span>
                  <span className="flex items-center">
                    <Eye className="w-3 h-3 mr-1" />
                    {article.views}
                  </span>
                </div>
              </div>
            </div>
            
            <div className="flex items-center space-x-3">
              <Button
                variant="ghost"
                size="sm"
                onClick={() => setIsLiked(!isLiked)}
                className={isLiked ? 'text-red-500' : ''}
              >
                <Heart className={`w-4 h-4 mr-2 ${isLiked ? 'fill-current' : ''}`} />
                {article.likes}
              </Button>
              <Button variant="ghost" size="sm">
                <Share2 className="w-4 h-4 mr-2" />
                Share
              </Button>
              <Button
                variant="ghost"
                size="sm"
                onClick={() => setIsBookmarked(!isBookmarked)}
                className={isBookmarked ? 'text-primary' : ''}
              >
                <Bookmark className={`w-4 h-4 ${isBookmarked ? 'fill-current' : ''}`} />
              </Button>
            </div>
          </div>
        </div>

        <Separator className="mb-8" />

        {/* Article Content */}
        <div className="prose prose-lg max-w-none mb-12 animate-fade-in" style={{ animationDelay: '0.2s' }}>
          <div dangerouslySetInnerHTML={{ __html: article.content }} />
        </div>

        {/* Poll Section */}
        <div className="mb-12 animate-fade-in" style={{ animationDelay: '0.4s' }}>
          <h3 className="text-2xl font-playfair font-semibold mb-4">Community Poll</h3>
          <Poll {...pollData} />
        </div>

        <Separator className="mb-8" />

        {/* Author Bio */}
        <div className="bg-card/50 rounded-lg border border-border/50 p-6 mb-12 animate-fade-in" style={{ animationDelay: '0.6s' }}>
          <div className="flex items-start space-x-4">
            <Avatar className="w-16 h-16">
              <AvatarImage src={article.author.avatar} />
              <AvatarFallback className="text-lg">{article.author.name.charAt(0)}</AvatarFallback>
            </Avatar>
            <div className="flex-1">
              <h3 className="text-xl font-playfair font-semibold mb-2">{article.author.name}</h3>
              <p className="text-muted-foreground mb-4">{article.author.bio}</p>
              <div className="flex space-x-3">
                <Button>Follow</Button>
                <Button variant="outline">View Profile</Button>
              </div>
            </div>
          </div>
        </div>

        {/* Comments Section */}
        <div className="animate-fade-in" style={{ animationDelay: '0.8s' }}>
          <CommentSection articleId={article.id} />
        </div>
      </article>
    </div>
  );
};

export default Article;
