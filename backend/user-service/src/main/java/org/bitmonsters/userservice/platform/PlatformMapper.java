package org.bitmonsters.userservice.platform;

import org.bitmonsters.userservice.user.Platform;
import org.springframework.stereotype.Service;

@Service
public class PlatformMapper {


    public Platform toPlatform(NewPlatformDto newPlatformDto) {
        return Platform.builder()
                .name(newPlatformDto.name())
                .baseUrl(newPlatformDto.baseUrl())
                .description(newPlatformDto.description())
                .build();
    }

    public PlatformDto toPlatformDto(Platform platform) {
        return PlatformDto.builder()
                .id(platform.getId())
                .name(platform.getName())
                .baseUrl(platform.getBaseUrl())
                .description(platform.getDescription())
                .build();
    }
}
