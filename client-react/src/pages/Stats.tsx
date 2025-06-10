import { useState } from 'react';
import Navbar from '@/components/NavBar';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Badge } from '@/components/ui/badge';
import { ChartContainer, ChartTooltip, ChartTooltipContent } from '@/components/ui/chart';
import { BarChart, Bar, XAxis, YAxis, ResponsiveContainer, LineChart, Line, PieChart, Pie, Cell } from 'recharts';
import { TrendingUp, Eye, Heart, MessageCircle, Users, Calendar, Trophy, Target } from 'lucide-react';

const Stats = () => {
  const [selectedPeriod, setSelectedPeriod] = useState('30d');

  // Mock analytics data
  const overviewStats = [
    { label: 'Total Views', value: '24.8K', change: '+12%', icon: Eye, color: 'text-blue-500' },
    { label: 'Total Likes', value: '3.2K', change: '+8%', icon: Heart, color: 'text-red-500' },
    { label: 'Comments', value: '892', change: '+15%', icon: MessageCircle, color: 'text-green-500' },
    { label: 'Followers', value: '1.5K', change: '+23%', icon: Users, color: 'text-purple-500' }
  ];

  const viewsData = [
    { date: 'Jan', views: 1200, likes: 180 },
    { date: 'Feb', views: 1900, likes: 250 },
    { date: 'Mar', views: 2400, likes: 320 },
    { date: 'Apr', views: 2100, likes: 290 },
    { date: 'May', views: 2800, likes: 380 },
    { date: 'Jun', views: 3200, likes: 420 }
  ];

  const topArticles = [
    { title: 'Building Modern React Applications', views: 5240, likes: 324, comments: 89 },
    { title: 'The Future of Web Development', views: 4180, likes: 287, comments: 76 },
    { title: 'Understanding TypeScript Generics', views: 3920, likes: 245, comments: 54 },
    { title: 'CSS Grid vs Flexbox', views: 3640, likes: 198, comments: 42 },
    { title: 'API Design Best Practices', views: 3200, likes: 176, comments: 38 }
  ];

  const trafficSources = [
    { name: 'Direct', value: 35, color: '#8884d8' },
    { name: 'Social Media', value: 28, color: '#82ca9d' },
    { name: 'Search', value: 22, color: '#ffc658' },
    { name: 'Referrals', value: 15, color: '#ff7c7c' }
  ];

  return (
    <div className="min-h-screen bg-background">
      <Navbar isAuthenticated={true} />
      
      <div className="container mx-auto px-4 py-8">
        <div className="mb-8">
          <h1 className="text-4xl font-montserrat font-bold mb-4 bg-gradient-to-r from-primary to-secondary bg-clip-text text-transparent">
            Analytics Dashboard
          </h1>
          <p className="text-muted-foreground text-lg">
            Track your content performance and audience engagement
          </p>
        </div>

        {/* Overview Cards */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {overviewStats.map((stat, index) => (
            <Card 
              key={stat.label} 
              className="bg-card/50 border-border/50 backdrop-blur-sm animate-fade-in"
              style={{ animationDelay: `${index * 0.1}s` }}
            >
              <CardContent className="p-6">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm text-muted-foreground">{stat.label}</p>
                    <p className="text-2xl font-bold">{stat.value}</p>
                    <Badge variant="secondary" className="mt-1">
                      {stat.change}
                    </Badge>
                  </div>
                  <stat.icon className={`w-8 h-8 ${stat.color}`} />
                </div>
              </CardContent>
            </Card>
          ))}
        </div>

        <Tabs defaultValue="overview" className="space-y-6">
          <TabsList className="bg-secondary/50 backdrop-blur-sm">
            <TabsTrigger value="overview">Overview</TabsTrigger>
            <TabsTrigger value="articles">Top Articles</TabsTrigger>
            <TabsTrigger value="audience">Audience</TabsTrigger>
            <TabsTrigger value="engagement">Engagement</TabsTrigger>
          </TabsList>

          <TabsContent value="overview" className="space-y-6">
            <div className="grid lg:grid-cols-2 gap-6">
              {/* Views Chart */}
              <Card className="bg-card/50 border-border/50 backdrop-blur-sm">
                <CardHeader>
                  <CardTitle className="flex items-center gap-2">
                    <TrendingUp className="w-5 h-5" />
                    Views & Engagement
                  </CardTitle>
                </CardHeader>
                <CardContent>
                  <ChartContainer
                    config={{
                      views: { label: "Views", color: "hsl(var(--primary))" },
                      likes: { label: "Likes", color: "hsl(var(--secondary))" }
                    }}
                    className="h-[300px]"
                  >
                    <ResponsiveContainer width="100%" height="100%">
                      <LineChart data={viewsData}>
                        <XAxis dataKey="date" />
                        <YAxis />
                        <ChartTooltip content={<ChartTooltipContent />} />
                        <Line type="monotone" dataKey="views" stroke="hsl(var(--primary))" strokeWidth={2} />
                        <Line type="monotone" dataKey="likes" stroke="hsl(var(--secondary))" strokeWidth={2} />
                      </LineChart>
                    </ResponsiveContainer>
                  </ChartContainer>
                </CardContent>
              </Card>

              {/* Traffic Sources */}
              <Card className="bg-card/50 border-border/50 backdrop-blur-sm">
                <CardHeader>
                  <CardTitle className="flex items-center gap-2">
                    <Target className="w-5 h-5" />
                    Traffic Sources
                  </CardTitle>
                </CardHeader>
                <CardContent>
                  <ChartContainer
                    config={{
                      traffic: { label: "Traffic" }
                    }}
                    className="h-[300px]"
                  >
                    <ResponsiveContainer width="100%" height="100%">
                      <PieChart>
                        <Pie
                          data={trafficSources}
                          cx="50%"
                          cy="50%"
                          outerRadius={80}
                          dataKey="value"
                          label={({ name, value }) => `${name}: ${value}%`}
                        >
                          {trafficSources.map((entry, index) => (
                            <Cell key={`cell-${index}`} fill={entry.color} />
                          ))}
                        </Pie>
                        <ChartTooltip />
                      </PieChart>
                    </ResponsiveContainer>
                  </ChartContainer>
                </CardContent>
              </Card>
            </div>
          </TabsContent>

          <TabsContent value="articles" className="space-y-6">
            <Card className="bg-card/50 border-border/50 backdrop-blur-sm">
              <CardHeader>
                <CardTitle className="flex items-center gap-2">
                  <Trophy className="w-5 h-5" />
                  Top Performing Articles
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="space-y-4">
                  {topArticles.map((article, index) => (
                    <div key={index} className="flex items-center justify-between p-4 bg-secondary/20 rounded-lg">
                      <div className="flex-1">
                        <h3 className="font-medium">{article.title}</h3>
                        <div className="flex items-center gap-4 mt-2 text-sm text-muted-foreground">
                          <span className="flex items-center gap-1">
                            <Eye className="w-3 h-3" />
                            {article.views.toLocaleString()}
                          </span>
                          <span className="flex items-center gap-1">
                            <Heart className="w-3 h-3" />
                            {article.likes}
                          </span>
                          <span className="flex items-center gap-1">
                            <MessageCircle className="w-3 h-3" />
                            {article.comments}
                          </span>
                        </div>
                      </div>
                      <Badge variant="outline">#{index + 1}</Badge>
                    </div>
                  ))}
                </div>
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="audience" className="space-y-6">
            <div className="text-center py-12">
              <Users className="w-12 h-12 text-muted-foreground mx-auto mb-4" />
              <h3 className="text-lg font-medium mb-2">Audience Analytics</h3>
              <p className="text-muted-foreground">Detailed audience insights coming soon</p>
            </div>
          </TabsContent>

          <TabsContent value="engagement" className="space-y-6">
            <div className="text-center py-12">
              <MessageCircle className="w-12 h-12 text-muted-foreground mx-auto mb-4" />
              <h3 className="text-lg font-medium mb-2">Engagement Metrics</h3>
              <p className="text-muted-foreground">Advanced engagement analytics coming soon</p>
            </div>
          </TabsContent>
        </Tabs>
      </div>
    </div>
  );
};

export default Stats;
