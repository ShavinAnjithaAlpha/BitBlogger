package org.bitmonsters.userservice.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USERS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "username"})},
        indexes = {@Index(name = "idx_email", columnList = "email", unique = true),
                    @Index(name = "idx_username", columnList = "username", unique = true)})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]{3,50}$", message = "username must be 3-50 characters")
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
    @Column(nullable = false)
    private String email;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    @ColumnDefault("FALSE")
    private Boolean displayEmail;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+.[A-Za-z]+$", message = "Invalid URL format")
    @Column(nullable = true)
    private String websiteUrl;

    @Column(nullable = true)
    @Embedded
    private Location location;

    @Column(nullable = true)
    @Embedded
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<UserLink> links;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = true, insertable = false)
    private LocalDateTime modifiedAt;
}
