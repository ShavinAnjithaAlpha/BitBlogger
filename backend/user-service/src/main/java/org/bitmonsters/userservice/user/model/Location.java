package org.bitmonsters.userservice.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String country;

}
