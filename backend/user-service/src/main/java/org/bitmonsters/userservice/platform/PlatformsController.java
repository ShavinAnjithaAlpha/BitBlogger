package org.bitmonsters.userservice.platform;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.user.IdResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/platforms")
@RequiredArgsConstructor
public class PlatformsController {

    private final PlatformService service;

    @PostMapping
    public IdResponse registerNewPlatform(
            @Validated @RequestBody NewPlatformDto newPlatformDto
    ) {
        var platform = service.registerNewPlatform(newPlatformDto);
        return new IdResponse(Long.valueOf(platform.getId()), "platform added to the system successfully");
    }

    @GetMapping
    public List<PlatformDto> getPlatforms() {
        return service.getPlatforms();
    }

}
