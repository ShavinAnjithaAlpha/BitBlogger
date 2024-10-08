package org.bitmonsters.userservice.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile implements Serializable {

    @Column(length = 255)
    private String bio;

    @Column(length = 255)
    private String skills;

    @Column(length = 255)
    private String availableFor;

    @Column(length = 255)
    private String learnings;

    @Column(length = 100)
    private String pronouns;

    @Column(length = 255)
    private String work;

    @Column(length = 100)
    private String education;
}
