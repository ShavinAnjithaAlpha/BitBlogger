package org.bitmonsters.userservice.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PLATFORMS")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Pattern(regexp = "^[A-Za-z0-9+_.-:/]+.[A-Za-z]+$", message = "Invalid URL format")
    private String baseUrl;

    @Column(length = 250)
    private String description;
}
