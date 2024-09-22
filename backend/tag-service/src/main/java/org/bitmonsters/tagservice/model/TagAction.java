package org.bitmonsters.tagservice.model;

import lombok.Getter;

@Getter
public enum TagAction {
    NAME_CHANGED(1),
    DESCRIPTION_CHANGED(2),
    ICON_CHANGED(4),
    NAME_DESCRIPTION_CHANGED(3),
    NAME_ICON_CHANGED(5),
    DESCRIPTION_ICON_CHANGED(6),
    NAME_DESCRIPTION_ICON_CHANGED(7),
    TAG_CREATED(0),
    TAG_REMOVED(8);

    private final int id;

    TagAction(int id) {
        this.id = id;
    }

    public static TagAction fromValue(int value) {
        return switch (value) {
            case 1 -> NAME_CHANGED;
            case 2 -> DESCRIPTION_CHANGED;
            case 3 -> NAME_DESCRIPTION_CHANGED;
            case 4 -> ICON_CHANGED;
            case 5 -> NAME_ICON_CHANGED;
            case 6 -> DESCRIPTION_ICON_CHANGED;
            case 7 -> NAME_DESCRIPTION_ICON_CHANGED;
            case 8 -> TAG_REMOVED;
            default -> TAG_CREATED;
        };
    }

    public TagAction addAction(TagAction topicAdded) {
        return TagAction.fromValue(id + topicAdded.getId());
    }
}
