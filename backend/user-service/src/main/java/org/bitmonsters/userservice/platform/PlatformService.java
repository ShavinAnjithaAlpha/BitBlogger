package org.bitmonsters.userservice.platform;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.user.Platform;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository repository;
    private final PlatformMapper mapper;

    public Platform registerNewPlatform(NewPlatformDto newPlatformDto) {
        return repository.save(mapper.toPlatform(newPlatformDto));
    }

    public List<PlatformDto> getPlatforms() {
        return repository.findAll().stream()
                .map(mapper::toPlatformDto)
                .collect(Collectors.toList());
    }

    public boolean isPlatformExists(Integer platformId) {
        Optional<Platform> platform = repository.findById(platformId);
        return platform.isPresent();
    }
}
