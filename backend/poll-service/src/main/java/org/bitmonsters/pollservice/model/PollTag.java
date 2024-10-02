package org.bitmonsters.pollservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "poll_tags")
public class PollTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_tag_id_seq")
    @SequenceGenerator(name = "poll_tag_id_seq", sequenceName = "poll_tags_seq", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @Column(name = "tag_id", nullable = false)
    private Integer tagId;

}
