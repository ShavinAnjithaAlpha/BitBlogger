package org.bitmonsters.userservice.interest;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.me.MessageResponse;
import org.bitmonsters.userservice.user.IdResponse;
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
            @RequestHeader("userId") Long userId,
            @Validated @RequestBody NewInterestDto newInterestDto) {
        var interest = service.addInterest(userId, newInterestDto);
        return new IdResponse(
                interest.getId(),
                "interest added successfully"
        );
    }

    @GetMapping
    public List<Integer> getAllInterestOfAuthenticatedUser(
            @RequestHeader("userId") Long userId
    ) {
        return service.getInterestOfUser(userId);
    }

    @DeleteMapping("/{tagId}")
    public MessageResponse removeInterest(
            @RequestHeader("userId") Long userId,
            @PathVariable("tagId") Integer tagId
    ) {
        service.removeInterest(userId, tagId);
        return new MessageResponse("interest removed successfully");
    }


}
