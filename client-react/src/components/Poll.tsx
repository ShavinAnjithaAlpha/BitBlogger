import { useState } from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { RadioGroup, RadioGroupItem } from '@/components/ui/radio-group';
import { Badge } from '@/components/ui/badge';
import { BarChart3, Users, Clock } from 'lucide-react';

interface PollOption {
  id: string;
  text: string;
  votes: number;
}

interface PollProps {
  id: string;
  question: string;
  options: PollOption[];
  totalVotes: number;
  timeLeft: string;
  hasVoted: boolean;
  userVote?: string;
}

const Poll = ({ id, question, options, totalVotes, timeLeft, hasVoted, userVote }: PollProps) => {
  const [selectedOption, setSelectedOption] = useState<string>('');
  const [voted, setVoted] = useState(hasVoted);
  const [currentVote, setCurrentVote] = useState(userVote);

  const handleVote = () => {
    if (selectedOption) {
      console.log('Voting for option:', selectedOption);
      setVoted(true);
      setCurrentVote(selectedOption);
    }
  };

  const getPercentage = (votes: number) => {
    return totalVotes > 0 ? Math.round((votes / totalVotes) * 100) : 0;
  };

  return (
    <Card className="bg-card/50 border-border/50 backdrop-blur-sm">
      <CardHeader>
        <div className="flex items-start justify-between">
          <CardTitle className="text-lg font-montserrat">{question}</CardTitle>
          <Badge variant="secondary" className="flex items-center gap-1">
            <Clock className="w-3 h-3" />
            {timeLeft}
          </Badge>
        </div>
      </CardHeader>
      <CardContent className="space-y-4">
        {!voted ? (
          <div className="space-y-4">
            <RadioGroup value={selectedOption} onValueChange={setSelectedOption}>
              {options.map((option) => (
                <div key={option.id} className="flex items-center space-x-2">
                  <RadioGroupItem value={option.id} id={option.id} />
                  <label htmlFor={option.id} className="flex-1 cursor-pointer">
                    {option.text}
                  </label>
                </div>
              ))}
            </RadioGroup>
            <Button 
              onClick={handleVote} 
              disabled={!selectedOption}
              className="w-full"
            >
              Vote
            </Button>
          </div>
        ) : (
          <div className="space-y-3">
            {options.map((option) => {
              const percentage = getPercentage(option.votes);
              const isUserVote = currentVote === option.id;
              
              return (
                <div key={option.id} className="space-y-2">
                  <div className="flex justify-between items-center">
                    <span className={`${isUserVote ? 'font-medium text-primary' : ''}`}>
                      {option.text}
                      {isUserVote && ' ✓'}
                    </span>
                    <span className="text-sm text-muted-foreground">
                      {percentage}% ({option.votes})
                    </span>
                  </div>
                  <div className="w-full bg-secondary/30 rounded-full h-2">
                    <div
                      className={`h-2 rounded-full transition-all duration-300 ${
                        isUserVote ? 'bg-primary' : 'bg-secondary'
                      }`}
                      style={{ width: `${percentage}%` }}
                    />
                  </div>
                </div>
              );
            })}
          </div>
        )}
        
        <div className="flex items-center gap-4 text-sm text-muted-foreground pt-2 border-t border-border/50">
          <div className="flex items-center gap-1">
            <Users className="w-4 h-4" />
            {totalVotes} votes
          </div>
          <div className="flex items-center gap-1">
            <BarChart3 className="w-4 h-4" />
            Poll #{id}
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default Poll;
