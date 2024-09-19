package org.bitmonsters.userservice.platform.service;

import org.bitmonsters.userservice.platform.Platform;
import org.bitmonsters.userservice.platform.dto.NewPlatformDto;
import org.bitmonsters.userservice.platform.dto.PlatformDto;
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
