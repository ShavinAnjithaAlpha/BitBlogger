import { useState } from 'react';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import { Textarea } from '@/components/ui/textarea';
import { Separator } from '@/components/ui/separator';
import { Heart, MessageCircle, MoreHorizontal } from 'lucide-react';

interface Comment {
  id: string;
  author: {
    name: string;
    username: string;
    avatar: string;
  };
  content: string;
  publishedAt: string;
  likes: number;
  replies: Comment[];
}

interface CommentSectionProps {
  articleId: string;
}

const CommentSection = ({ articleId }: CommentSectionProps) => {
  const [newComment, setNewComment] = useState('');
  const [replyingTo, setReplyingTo] = useState<string | null>(null);
  const [replyContent, setReplyContent] = useState('');

  // Mock comments data
  const [comments] = useState<Comment[]>([
    {
      id: '1',
      author: {
        name: 'Sarah Johnson',
        username: 'sarahj',
        avatar: '/placeholder.svg'
      },
      content: 'Great article! The TypeScript examples are really helpful. I especially liked the section about component patterns.',
      publishedAt: '2 hours ago',
      likes: 12,
      replies: [
        {
          id: '1-1',
          author: {
            name: 'Mike Chen',
            username: 'mikechen',
            avatar: '/placeholder.svg'
          },
          content: 'I agree! The patterns section really clarified some concepts for me.',
          publishedAt: '1 hour ago',
          likes: 3,
          replies: []
        }
      ]
    },
    {
      id: '2',
      author: {
        name: 'Alex Rivera',
        username: 'alexr',
        avatar: '/placeholder.svg'
      },
      content: 'Thanks for sharing this! Do you have any recommendations for testing TypeScript React components?',
      publishedAt: '4 hours ago',
      likes: 8,
      replies: []
    }
  ]);

  const handleSubmitComment = () => {
    if (newComment.trim()) {
      console.log('Submitting comment:', newComment);
      setNewComment('');
    }
  };

  const handleSubmitReply = (commentId: string) => {
    if (replyContent.trim()) {
      console.log('Submitting reply to:', commentId, replyContent);
      setReplyContent('');
      setReplyingTo(null);
    }
  };

  const CommentItem = ({ comment, isReply = false }: { comment: Comment; isReply?: boolean }) => (
    <div className={`${isReply ? 'ml-12' : ''} mb-6`}>
      <div className="flex items-start space-x-3">
        <Avatar className="w-8 h-8">
          <AvatarImage src={comment.author.avatar} />
          <AvatarFallback>{comment.author.name.charAt(0)}</AvatarFallback>
        </Avatar>
        <div className="flex-1">
          <div className="flex items-center space-x-2 mb-1">
            <span className="font-medium text-sm">{comment.author.name}</span>
            <span className="text-xs text-muted-foreground">@{comment.author.username}</span>
            <span className="text-xs text-muted-foreground">·</span>
            <span className="text-xs text-muted-foreground">{comment.publishedAt}</span>
          </div>
          <p className="text-sm mb-3">{comment.content}</p>
          <div className="flex items-center space-x-4">
            <Button variant="ghost" size="sm" className="h-8 px-2">
              <Heart className="w-3 h-3 mr-1" />
              {comment.likes}
            </Button>
            {!isReply && (
              <Button 
                variant="ghost" 
                size="sm" 
                className="h-8 px-2"
                onClick={() => setReplyingTo(replyingTo === comment.id ? null : comment.id)}
              >
                <MessageCircle className="w-3 h-3 mr-1" />
                Reply
              </Button>
            )}
            <Button variant="ghost" size="sm" className="h-8 w-8 p-0">
              <MoreHorizontal className="w-3 h-3" />
            </Button>
          </div>
          
          {replyingTo === comment.id && (
            <div className="mt-4 space-y-3">
              <Textarea
                placeholder="Write a reply..."
                value={replyContent}
                onChange={(e) => setReplyContent(e.target.value)}
                className="min-h-[80px] text-sm"
              />
              <div className="flex space-x-2">
                <Button size="sm" onClick={() => handleSubmitReply(comment.id)}>
                  Reply
                </Button>
                <Button variant="ghost" size="sm" onClick={() => setReplyingTo(null)}>
                  Cancel
                </Button>
              </div>
            </div>
          )}
        </div>
      </div>
      
      {comment.replies.length > 0 && (
        <div className="mt-4">
          {comment.replies.map((reply) => (
            <CommentItem key={reply.id} comment={reply} isReply={true} />
          ))}
        </div>
      )}
    </div>
  );

  return (
    <div className="mt-12">
      <h2 className="text-2xl font-playfair font-semibold mb-6">
        Comments ({comments.length + comments.reduce((acc, c) => acc + c.replies.length, 0)})
      </h2>
      
      {/* New Comment Form */}
      <div className="mb-8 p-6 bg-card/50 rounded-lg border border-border/50">
        <div className="flex items-start space-x-4">
          <Avatar className="w-10 h-10">
            <AvatarImage src="/placeholder.svg" />
            <AvatarFallback>YU</AvatarFallback>
          </Avatar>
          <div className="flex-1 space-y-4">
            <Textarea
              placeholder="Share your thoughts..."
              value={newComment}
              onChange={(e) => setNewComment(e.target.value)}
              className="min-h-[100px]"
            />
            <div className="flex justify-end">
              <Button onClick={handleSubmitComment} disabled={!newComment.trim()}>
                Post Comment
              </Button>
            </div>
          </div>
        </div>
      </div>

      <Separator className="mb-8" />

      {/* Comments List */}
      <div className="space-y-6">
        {comments.map((comment) => (
          <CommentItem key={comment.id} comment={comment} />
        ))}
      </div>
    </div>
  );
};

export default CommentSection;
