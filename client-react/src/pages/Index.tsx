import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '@/components/ui/button';
import { BookOpen, TrendingUp, Users, Zap } from 'lucide-react';
import { Link } from 'react-router-dom';

const Index = () => {
  const navigate = useNavigate();

  // Redirect to home for demo purposes
  useEffect(() => {
    const timer = setTimeout(() => {
      navigate('/home');
    }, 3000);

    return () => clearTimeout(timer);
  }, [navigate]);

  return (
    <div className="min-h-screen bg-background flex items-center justify-center">
      <div className="text-center space-y-8 animate-fade-in">
        {/* Logo */}
        <div className="flex justify-center mb-8">
          <div className="w-16 h-16 bg-gradient-to-r from-primary to-accent rounded-lg flex items-center justify-center animate-float">
            <BookOpen className="w-10 h-10 text-primary-foreground" />
          </div>
        </div>

        <div className="space-y-4">
          <h1 className="text-5xl font-playfair font-bold text-gradient">
            BlogSphere
          </h1>
          <p className="text-xl text-muted-foreground max-w-md mx-auto">
            Where stories come to life and knowledge finds its voice
          </p>
        </div>

        {/* Features */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 max-w-3xl mx-auto mt-12">
          <div className="bg-card/50 p-6 rounded-lg border border-border/50 glass">
            <TrendingUp className="w-8 h-8 text-primary mx-auto mb-3" />
            <h3 className="font-semibold mb-2">Trending Content</h3>
            <p className="text-sm text-muted-foreground">Discover the most engaging stories and trending topics</p>
          </div>
          
          <div className="bg-card/50 p-6 rounded-lg border border-border/50 glass">
            <Users className="w-8 h-8 text-primary mx-auto mb-3" />
            <h3 className="font-semibold mb-2">Community</h3>
            <p className="text-sm text-muted-foreground">Connect with writers and readers from around the world</p>
          </div>
          
          <div className="bg-card/50 p-6 rounded-lg border border-border/50 glass">
            <Zap className="w-8 h-8 text-primary mx-auto mb-3" />
            <h3 className="font-semibold mb-2">Modern Experience</h3>
            <p className="text-sm text-muted-foreground">Enjoy a sleek, fast, and intuitive blogging platform</p>
          </div>
        </div>

        <div className="flex flex-col sm:flex-row gap-4 justify-center mt-8">
          <Button size="lg" asChild>
            <Link to="/signup">Get Started</Link>
          </Button>
          <Button size="lg" variant="outline" asChild>
            <Link to="/login">Sign In</Link>
          </Button>
        </div>

        <p className="text-sm text-muted-foreground mt-8">
          Redirecting to demo in 3 seconds...
        </p>
      </div>
    </div>
  );
};

export default Index;
