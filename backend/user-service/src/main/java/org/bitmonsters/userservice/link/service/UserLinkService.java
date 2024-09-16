package org.bitmonsters.userservice.link.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.UserLinkException;
import org.bitmonsters.userservice.link.dto.UserLinkDto;
import org.bitmonsters.userservice.link.repository.UserLinkRepository;
import org.bitmonsters.userservice.platform.service.PlatformService;
import org.bitmonsters.userservice.dto.IdResponse;
import org.bitmonsters.userservice.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLinkService {

    private final UserLinkRepository linkRepository;
    private final UserRepository userRepository;
    private final UserLinkMapper mapper;
    private final PlatformService platformService;

    public final Integer MAX_LINKS_PER_USER = 10;

    public IdResponse addUserLink(Long userId, UserLinkDto userLinkDto) {

        if (!platformService.isPlatformExists(userLinkDto.platformId())) {
            throw new UserLinkException(String.format("invalid platform id %d", userLinkDto.platformId()));
        }

        if (isUserLinkExists(userId, userLinkDto.platformId())) {
            throw new UserLinkException(String.format("link with platform id %d is already associated with user with id %d", userLinkDto.platformId(), userId));
        }

        if (linkRepository.countByUserId(userId) > MAX_LINKS_PER_USER) {
            throw new UserLinkException("user has exceeded the maximum link count");
        }

        var link = linkRepository.save(
                mapper.toUserLink(userId, userLinkDto)
        );

        return  new IdResponse(link.getId(), "user link created successfully");
    }

    public boolean isUserLinkExists(Long userId, Integer platformId) {
        var link = linkRepository.findByUserIdAndPlatformId(userId, platformId);
        return link.isPresent();
    }

    public void updateUserLink(Long linkId, Long userId, UserLinkDto userLinkDto) {
        // fetch the user link from the repository
        var userLink = linkRepository.findById(linkId).orElse(null);

        if (userLink == null) {
            throw new UserLinkException(String.format("user's link with id %d of the user with id %d is not exists", linkId, userId));
        }

        // check if the user owns the link
        if (!userLink.getUser().getId().equals(userId)) {
            throw new UserLinkException(String.format("link with id %d is not belongs to user with id %d", linkId, userId));
        }

        // update the user link
        userLink.setUrl(userLinkDto.url());
        userLink.setCustom(userLinkDto.custom());
        linkRepository.save(userLink);
    }
}
