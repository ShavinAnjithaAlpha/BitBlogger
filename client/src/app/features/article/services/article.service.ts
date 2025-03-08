// filepath: /d:/Dev/WebApps/BitBlogger/client/src/app/features/article/services/article.service.ts
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { FullArticle } from '../models/fullarticle.model';
import { Article } from '../models/article.model';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private articles: FullArticle[] = [
    {
      id: '1',
      title: 'Sample Article 1',
      content: `
      <h1>AI in Cybersecurity: The Next Frontier</h1>
    <p>As cyber threats grow in complexity, artificial intelligence (AI) is becoming an essential tool in the battle against cybercrime. AI-driven security solutions are revolutionizing the way organizations detect and respond to cyber threats.</p>

    <h2>1. The Growing Cybersecurity Threat Landscape</h2>
    <p>Cyberattacks are increasing at an alarming rate, targeting businesses, governments, and individuals. Traditional security methods struggle to keep up with new and evolving threats.</p>
    <img src="https://www.example.com/cyber-threats.png" alt="Cyber Threats">

    <h2>2. How AI Enhances Cybersecurity</h2>
    <p>AI-powered cybersecurity solutions use machine learning and deep learning to detect anomalies, prevent attacks, and improve response times.</p>

    <ul>
        <li>Real-time threat detection using AI algorithms.</li>
        <li>Automated responses to mitigate security breaches.</li>
        <li>Enhanced behavioral analytics to identify suspicious activities.</li>
    </ul>

    <blockquote>"Artificial Intelligence is not the future of cybersecurity. It is the present." - Cybersecurity Expert</blockquote>

    <h2>3. AI-Based Threat Detection</h2>
    <p>Traditional antivirus software relies on known signatures, while AI can identify previously unknown threats by recognizing unusual patterns.</p>

    <h3>Machine Learning in Threat Detection:</h3>
    <ol>
        <li>Supervised Learning: Trained on historical data to identify threats.</li>
        <li>Unsupervised Learning: Detects anomalies without prior knowledge.</li>
        <li>Reinforcement Learning: Continuously improves threat identification models.</li>
    </ol>

    <h2>4. The Future of AI in Cybersecurity</h2>
    <p>AI will continue to play a crucial role in cybersecurity by providing smarter, faster, and more efficient defense mechanisms.</p>

    <h3>Future Trends:</h3>
    <ul>
        <li>AI-driven penetration testing.</li>
        <li>Blockchain-enhanced security measures.</li>
        <li>Quantum computing threats and AI-based defenses.</li>
    </ul>

    <p>AI is revolutionizing cybersecurity, helping organizations stay ahead of attackers. However, as AI evolves, so do the threats, making cybersecurity an ongoing battle.</p>
    `,
      author: {
        id: 1,
        firstname: 'John',
        lastname: 'Doe',
        email: 'johndoe@gmail.com',
        username: 'johndoe',
        avatar: 'https://via.placeholder.com/150',
      },
      preview: 'This is the preview of the sample article 1.',
      coverImage: 'https://via.placeholder.com/150',
      tags: ['tag1', 'tag2'],
      likes: 10,
      comments: 5,
      views: 100,
      publishedAt: '2025-03-02',
    },
    {
      id: '2',
      title: 'The Evolution of Web Development',
      content: `<h1>The Evolution of Web Development: A Deep Dive</h1>
    <p>Web development has significantly evolved over the past three decades. From simple static pages to dynamic, AI-driven web applications, the landscape of web technology continues to advance at an unprecedented pace.</p>

    <h2>1. The Birth of the World Wide Web</h2>
    <p>In 1989, Tim Berners-Lee proposed the concept of the World Wide Web. The first-ever website was launched in 1991, consisting of simple HTML pages.</p>
    <img src="https://images.unsplash.com/photo-1741377772075-5f0f0d21d6b4?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="First web page screenshot">

    <h2>2. The Rise of JavaScript and Interactivity</h2>
    <p>JavaScript was introduced in 1995 by Netscape, enabling developers to add interactive elements to web pages. This era saw the rise of DHTML, making websites more dynamic.
    <h3>JavaScript Frameworks and Libraries:</h3>
    <p>The introduction of frameworks and libraries like jQuery, AngularJS, and React.js has further enhanced the capabilities of JavaScript, making it easier to build complex web applications.</p>
    <ul>
      <li><strong>jQuery:</strong> Simplified DOM manipulation and event handling.</li>
      <li><strong>AngularJS:</strong> Introduced two-way data binding and dependency injection.</li>
      <li><strong>React.js:</strong> Brought the concept of a virtual DOM and component-based architecture.</li>
    </ul>
    </p>

    <blockquote>"JavaScript is the duct tape of the internet." - Unknown Developer</blockquote>

    <h2>3. The Advent of CSS and Responsive Design</h2>
    <p>CSS became a fundamental part of web development, allowing developers to separate content from design. Responsive web design emerged as a necessity with the proliferation of mobile devices.</p>
    <img src="https://images.unsplash.com/photo-1741335661631-439871f2b3b6?q=80&w=1963&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Responsive Design Example">

    <h2>4. The Era of Web Frameworks</h2>
    <p>Frameworks like Angular, React, and Vue revolutionized front-end development. Backend technologies also advanced with Node.js, Django, and Ruby on Rails.</p>

    <ul>
        <li>React - Developed by Facebook, widely used for building UI components.</li>
        <li>Vue.js - A progressive framework for UI development.</li>
        <li>Angular - A complete front-end framework developed by Google.</li>
    </ul>

    <h2>5. The Future: AI and Web Development</h2>
    <p>AI is poised to transform web development, with AI-powered assistants helping to generate code, automate testing, and personalize user experiences.</p>

    <h3>What the Future Holds:</h3>
    <ol>
        <li>AI-assisted coding tools.</li>
        <li>Augmented reality in web applications.</li>
        <li>Blockchain for enhanced security.</li>
    </ol>

    <p>Web development is an ever-evolving field, and staying updated with the latest trends is crucial for developers.</p>`,
      preview: 'This is the preview of the sample article 2.',
      coverImage: 'https://via.placeholder.com/150',
      author: {
        id: 1,
        firstname: 'John',
        lastname: 'Doe',
        email: 'johndoe@gmail.com',
        username: 'johndoe',
        avatar: 'https://via.placeholder.com/150',
      },
      tags: ['web', 'coding', 'dev', 'framework'],
      likes: 458,
      comments: 120,
      views: 2005,
      publishedAt: '2025-03-02',
    },
    {
      id: '3',
      title: 'The Rise of Artificial Intelligence',
      content: `
        <h1>The Future of Cloud Computing: Trends and Innovations</h1>
    <p>Cloud computing has revolutionized the way businesses operate, offering scalable and cost-effective solutions. As we move forward, cloud technologies continue to evolve, bringing new capabilities and challenges.</p>

    <h2>1. The Rise of Multi-Cloud and Hybrid Cloud Solutions</h2>
    <p>Organizations are increasingly adopting multi-cloud strategies to optimize performance, reduce costs, and enhance security.</p>
    <ul>
        <li><strong>Multi-cloud strategy:</strong> Using multiple cloud providers to avoid vendor lock-in.</li>
        <li><strong>Hybrid cloud:</strong> Combining public and private clouds for better flexibility.</li>
    </ul>
    <img src="https://www.example.com/multi-cloud-diagram.png" alt="Multi-Cloud Architecture">

    <blockquote>"Hybrid cloud adoption is no longer a choice, but a necessity for enterprises looking for resilience and agility." - Cloud Architect</blockquote>

    <h2>2. Edge Computing: Bringing the Cloud Closer</h2>
    <p>Edge computing reduces latency by processing data closer to the source. This is crucial for applications like IoT, self-driving cars, and real-time analytics.</p>

    <h3>Benefits of Edge Computing:</h3>
    <ol>
        <li>Lower latency for real-time processing.</li>
        <li>Reduced bandwidth costs.</li>
        <li>Enhanced security with localized data handling.</li>
    </ol>

    <h2>3. AI and Machine Learning in Cloud Computing</h2>
    <p>Cloud platforms are integrating AI and machine learning to provide automated solutions and intelligent analytics.</p>
    <ul>
        <li>AI-driven cybersecurity monitoring.</li>
        <li>Machine learning-based data analytics.</li>
        <li>AI-powered cloud automation for infrastructure management.</li>
    </ul>

    <h2>4. Quantum Computing and Its Impact on the Cloud</h2>
    <p>Quantum computing is set to disrupt cloud services by offering exponential computational power. Companies like IBM, Google, and Microsoft are investing heavily in quantum cloud platforms.</p>
    <img src="https://www.example.com/quantum-cloud.png" alt="Quantum Computing in the Cloud">

    <h2>5. Serverless Computing: The Next Big Shift</h2>
    <p>Serverless computing eliminates the need for managing infrastructure, allowing developers to focus on code rather than hardware.</p>
    <h3>Advantages of Serverless Computing:</h3>
    <ul>
        <li>Cost efficiency with pay-as-you-go pricing.</li>
        <li>Scalability without infrastructure management.</li>
        <li>Faster deployment of applications.</li>
    </ul>

    <h2>6. Enhanced Cloud Security and Zero Trust Architecture</h2>
    <p>With increasing cyber threats, cloud security is a top priority. Zero trust architecture (ZTA) ensures strict access control and verification.</p>
    <blockquote>"In the cloud era, security is not an option; it is a necessity." - Cybersecurity Analyst</blockquote>

    <h2>7. Cloud-Native Development and DevOps</h2>
    <p>Cloud-native technologies, including microservices, Kubernetes, and containerization, are reshaping modern development practices.</p>
    <pre>
        <code>
        kubectl apply -f deployment.yaml
        </code>
    </pre>

    <p>DevOps and CI/CD pipelines are accelerating cloud application deployment and improving software reliability.</p>

    <h2>8. The Future of Cloud Computing: What's Next?</h2>
    <p>The cloud will continue to evolve with innovations in AI, automation, security, and edge computing.</p>
    <h3>Upcoming Trends:</h3>
    <ul>
        <li>5G-powered cloud applications.</li>
        <li>Decentralized cloud computing using blockchain.</li>
        <li>Self-healing cloud infrastructures.</li>
    </ul>

    <p>Cloud computing remains a key driver of digital transformation, shaping the future of businesses worldwide.</p>
      `,
      preview: 'This is the preview of the sample article 3.',
      coverImage: 'https://via.placeholder.com/150',
      publishedAt: '2025-03-02',
      likes: 9854,
      comments: 874,
      views: 10000,
      tags: ['cloud', 'computing', 'AI', 'security'],
      author: {
        id: 1,
        firstname: 'John',
        lastname: 'Doe',
        email: 'johndoe@gmail.com',
        username: 'johndoe',
        avatar: 'https://via.placeholder.com/150',
      }
    }
  ];

  nextArticles: Article[] = [
    {
      id: '1',
      author: {
        id: 1,
        username: 'johndoe',
        firstname: 'John',
        lastname: 'Doe',
        email: 'john.doe@example.com',
        avatar: 'https://img.freepik.com/free-photo/portrait-white-man-isolated_53876-40306.jpg'
      },
      title: 'Trusted Computing and Digital Privacy',
      preview: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. In ex recusandae voluptate reiciendis numquam excepturi eaque obcaecati non mollitia a...',
      coverImage: 'https://wallpapers.com/images/featured/vector-art-6ttd2h971c0ivqyh.jpg',
      publishedAt: 'Aug 24, 2025',
      tags: ['Computing', 'Privacy', 'Security'],
      likes: 47,
      comments: 455
    },
    {
      id: '2',
      author: {
        id: 2,
        username: 'janesmith',
        firstname: 'Jane',
        lastname: 'Smith',
        email: 'jane.smith@example.com',
        avatar: 'https://img.freepik.com/free-photo/portrait-white-woman-isolated_53876-40306.jpg'
      },
      title: 'The Future of Quantum Computing',
      preview: 'Quantum computing is poised to revolutionize the tech industry. In this article, we explore the potential applications and challenges of this emerging technology...',
      coverImage: 'https://wallpapers.com/images/featured/quantum-computing-6ttd2h971c0ivqyh.jpg',
      publishedAt: 'Sep 10, 2025',
      tags: ['Quantum Computing', 'Technology', 'Future'],
      likes: 89,
      comments: 123
    }
  ];

  getArticleById(id: string): Observable<FullArticle> {
    const article = this.articles.find((article) => article.id === id);
    return of(article!);
  }

  getRecommendedArticles(articleId: string): Observable<Article[]> {
    return of(this.nextArticles);
  }
}
