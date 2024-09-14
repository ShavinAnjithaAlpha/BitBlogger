package org.bitmonsters.userservice.platform;

import org.bitmonsters.userservice.user.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    Optional<Platform> findByNameOrBaseUrl(String name, String baseUrl);

}
