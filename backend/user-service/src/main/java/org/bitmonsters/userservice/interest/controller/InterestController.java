package org.bitmonsters.userservice.interest.controller;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.dto.IdResponse;
import org.bitmonsters.userservice.dto.MessageResponse;
import org.bitmonsters.userservice.interest.dto.NewInterestDto;
import org.bitmonsters.userservice.interest.service.InterestService;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/me/interests")
@RequiredArgsConstructor
public class InterestController {

    private final InterestService service;

    @PostMapping
    public IdResponse addInterest(
            Authentication authentication,
            @Validated @RequestBody NewInterestDto newInterestDto) {
        var interest = service.addInterest((Long) authentication.getPrincipal(), newInterestDto);
        return new IdResponse(
                interest.getId(),
                "interest added successfully"
        );
    }

    @GetMapping
    public List<Integer> getAllInterestOfAuthenticatedUser(
            Authentication authentication
    ) {
        return service.getInterestOfUser((Long) authentication.getPrincipal());
    }

    @DeleteMapping("/{tagId}")
    public MessageResponse removeInterest(
            Authentication authentication,
            @PathVariable("tagId") Integer tagId
    ) {
        service.removeInterest((Long) authentication.getPrincipal(), tagId);
        return new MessageResponse("interest removed successfully");
    }


}
