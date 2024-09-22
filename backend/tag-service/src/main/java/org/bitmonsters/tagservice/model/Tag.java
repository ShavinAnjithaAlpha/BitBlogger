package org.bitmonsters.tagservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "description", length = 1024, nullable = false)
    private String description;

    @Column(name = "icon", length = 1024)
    private String icon;

    @Column(name = "post_count", nullable = false)
    private Long postCount = 0L;

    @OneToMany(mappedBy = "tag")
    private List<TagHistory> history;

}
