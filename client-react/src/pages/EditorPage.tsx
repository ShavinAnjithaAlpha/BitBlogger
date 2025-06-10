import { useEditor, EditorContent } from '@tiptap/react'
import StarterKit from '@tiptap/starter-kit'
import Heading from '@tiptap/extension-heading'
import Paragraph from '@tiptap/extension-paragraph'
import Text from '@tiptap/extension-text'
import Bold from '@tiptap/extension-bold'
import Italic from '@tiptap/extension-italic'
import Strike from '@tiptap/extension-strike'
import Code from '@tiptap/extension-code'
import Blockquote from '@tiptap/extension-blockquote'
import Link from '@tiptap/extension-link'
import ListItem from '@tiptap/extension-list-item'
import BulletList from '@tiptap/extension-bullet-list'
import OrderedList from '@tiptap/extension-ordered-list'
import React from 'react'

const MenuBar: React.FC<{ editor: any }> = ({ editor }) => {
  if (!editor) {
    return null
  }

  return (
    <div className="flex flex-wrap gap-2 p-2 border-b border-border bg-card rounded-t-md">
      <button
        onClick={() => editor.chain().focus().toggleBold().run()}
        disabled={!editor.can().chain().focus().toggleBold().run()}
        className={`px-3 py-1 rounded ${editor.isActive('bold') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Bold
      </button>
      <button
        onClick={() => editor.chain().focus().toggleItalic().run()}
        disabled={!editor.can().chain().focus().toggleItalic().run()}
        className={`px-3 py-1 rounded ${editor.isActive('italic') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Italic
      </button>
      <button
        onClick={() => editor.chain().focus().toggleStrike().run()}
        disabled={!editor.can().chain().focus().toggleStrike().run()}
        className={`px-3 py-1 rounded ${editor.isActive('strike') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Strike
      </button>
      <button
        onClick={() => editor.chain().focus().toggleCode().run()}
        disabled={!editor.can().chain().focus().toggleCode().run()}
        className={`px-3 py-1 rounded ${editor.isActive('code') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Code
      </button>
      <button
        onClick={() => editor.chain().focus().setParagraph().run()}
        className={`px-3 py-1 rounded ${editor.isActive('paragraph') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Paragraph
      </button>
      <button
        onClick={() => editor.chain().focus().toggleHeading({ level: 1 }).run()}
        className={`px-3 py-1 rounded ${editor.isActive('heading', { level: 1 }) ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        H1
      </button>
      <button
        onClick={() => editor.chain().focus().toggleHeading({ level: 2 }).run()}
        className={`px-3 py-1 rounded ${editor.isActive('heading', { level: 2 }) ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        H2
      </button>
      <button
        onClick={() => editor.chain().focus().toggleHeading({ level: 3 }).run()}
        className={`px-3 py-1 rounded ${editor.isActive('heading', { level: 3 }) ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        H3
      </button>
      <button
        onClick={() => editor.chain().focus().toggleBulletList().run()}
        className={`px-3 py-1 rounded ${editor.isActive('bulletList') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Bullet List
      </button>
      <button
        onClick={() => editor.chain().focus().toggleOrderedList().run()}
        className={`px-3 py-1 rounded ${editor.isActive('orderedList') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Ordered List
      </button>
      <button
        onClick={() => editor.chain().focus().toggleBlockquote().run()}
        className={`px-3 py-1 rounded ${editor.isActive('blockquote') ? 'bg-primary text-primary-foreground' : 'bg-secondary text-secondary-foreground'}`}
      >
        Blockquote
      </button>
      <button
        onClick={() => editor.chain().focus().undo().run()}
        disabled={!editor.can().chain().focus().undo().run()}
        className="px-3 py-1 rounded bg-secondary text-secondary-foreground"
      >
        Undo
      </button>
      <button
        onClick={() => editor.chain().focus().redo().run()}
        disabled={!editor.can().chain().focus().redo().run()}
        className="px-3 py-1 rounded bg-secondary text-secondary-foreground"
      >
        Redo
      </button>
    </div>
  )
}

const EditorPage: React.FC = () => {
  const editor = useEditor({
    extensions: [
      StarterKit,
      Heading.configure({
        levels: [1, 2, 3],
      }),
      Paragraph,
      Text,
      Bold,
      Italic,
      Strike,
      Code,
      Blockquote,
      Link,
      ListItem,
      BulletList,
      OrderedList,
    ],
    content: `<p>Start writing your article here...</p>`,
    editorProps: {
      attributes: {
        class: 'prose dark:prose-invert max-w-none focus:outline-none p-4 min-h-[300px] border-b border-l border-r border-border rounded-b-md bg-card text-card-foreground',
      },
    },
  })

  return (
    <div className="container mx-auto p-4 md:p-8">
      <h1 className="text-4xl font-montserrat text-primary text-center mb-8">Create New Article</h1>
      <div className="bg-popover border border-border rounded-md shadow-lg glow">
        <input
          type="text"
          placeholder="Article Title"
          className="w-full p-4 bg-input text-foreground border-b border-border rounded-t-md focus:outline-none font-montserrat text-xl"
        />
        <MenuBar editor={editor} />
        <EditorContent editor={editor} />
        <div className="p-4 flex justify-end gap-4">
          <button className="px-6 py-2 bg-secondary text-secondary-foreground rounded-md hover:bg-secondary/80 transition-colors">
            Save Draft
          </button>
          <button className="px-6 py-2 bg-primary text-primary-foreground rounded-md hover:bg-primary/80 transition-colors">
            Publish Article
          </button>
        </div>
      </div>
    </div>
  )
}

export default EditorPage