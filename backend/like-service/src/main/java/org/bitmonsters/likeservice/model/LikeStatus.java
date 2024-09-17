package org.bitmonsters.likeservice.model;

import lombok.Getter;

@Getter
public enum LikeStatus {
    LIKE(0),
    HEART(1),
    FIRE(2),
    INFORMATIVE(3),
    WOW(4);

    private final Integer value;

    LikeStatus(Integer value) {
        if (value > 4) this.value = 0;
        else this.value = value;
    }

    public static LikeStatus fromValue(Integer value) {
        return switch (value) {
            case 1 -> HEART;
            case 2 -> FIRE;
            case 3 -> INFORMATIVE;
            case 4 -> WOW;
            default -> LIKE;
        };
    }
}
