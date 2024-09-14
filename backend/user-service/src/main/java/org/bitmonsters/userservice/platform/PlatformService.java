package org.bitmonsters.userservice.platform;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.UserPlatformException;
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
        // check if the platform already exists in the system
        if (isPlatformExists(newPlatformDto.name(), newPlatformDto.baseUrl())) {
            throw new UserPlatformException(
                    String.format("platform with name %s or base url %s is already exists", newPlatformDto.name(), newPlatformDto.baseUrl())
            );
        }

        return repository.save(mapper.toPlatform(newPlatformDto));
    }

    public List<PlatformDto> getPlatforms() {
        return repository.findAll().stream()
                .map(mapper::toPlatformDto)
                .collect(Collectors.toList());
    }

    private boolean isPlatformExists(String name, String baseUrl) {
        var platform = repository.findByNameOrBaseUrl(name, baseUrl);
        return platform.isPresent();
    }

    public boolean isPlatformExists(Integer platformId) {
        Optional<Platform> platform = repository.findById(platformId);
        return platform.isPresent();
    }
}
