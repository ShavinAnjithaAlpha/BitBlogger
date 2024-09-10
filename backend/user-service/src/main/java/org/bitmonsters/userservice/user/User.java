package org.bitmonsters.userservice.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(columnDefinition = "boolean default FALSE")
    private Boolean display_email;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+.[A-Za-z]+$", message = "Invalid URL format")
    @Column(nullable = true)
    private String website_url;

    @Column(nullable = true)
    @Embedded
    private Location location;

    @Column(nullable = true)
    @Embedded
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<UserLink> links;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = true, insertable = false)
    private LocalDateTime modifiedAt;
}
