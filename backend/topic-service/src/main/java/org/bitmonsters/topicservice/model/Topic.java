package org.bitmonsters.topicservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_topic", nullable = true)
    private Topic parentTopic;

    @OneToMany(mappedBy = "parentTopic")
    private List<Topic> childTopics;

    @OneToMany(mappedBy = "topic")
    private List<TopicHistory> history;
}
