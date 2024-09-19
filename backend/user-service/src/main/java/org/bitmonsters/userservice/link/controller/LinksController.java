package org.bitmonsters.userservice.link.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.link.dto.UserLinkDto;
import org.bitmonsters.userservice.link.service.UserLinkService;
import org.bitmonsters.userservice.dto.MessageResponse;
import org.bitmonsters.userservice.dto.IdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/me/links")
@RequiredArgsConstructor
public class LinksController {

    private final UserLinkService linkService;

    @PostMapping
    public ResponseEntity<IdResponse> addUserLink(
            @RequestHeader(name = "userId") Long userId,
            @Validated @RequestBody UserLinkDto userLinkDto
    ) {
        var link = linkService.addUserLink(userId, userLinkDto);
        return ResponseEntity.ok(link);
    }

    @PutMapping("/{linkId}")
    public ResponseEntity<MessageResponse> updateUserLink(
            @RequestHeader("userId") Long userId,
            @PathVariable("linkId") Long linkId,
            @RequestBody UserLinkDto userLinkDto
    ) {
        linkService.updateUserLink(linkId, userId, userLinkDto);
        return ResponseEntity.ok(new MessageResponse("user link updated successfully"));
    }

}
