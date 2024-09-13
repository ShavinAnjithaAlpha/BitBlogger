package org.bitmonsters.userservice.platform;

import org.bitmonsters.userservice.user.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {

}
