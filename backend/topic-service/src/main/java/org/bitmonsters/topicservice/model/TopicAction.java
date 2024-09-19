package org.bitmonsters.topicservice.model;

import lombok.Getter;

@Getter
public enum TopicAction {
    NAME_CHANGED(1),
    DESCRIPTION_CHANGED(2),
    PARENT_CHANGED(4),
    NAME_DESCRIPTION_CHANGED(3),
    NAME_PARENT_CHANGED(5),
    DESCRIPTION_PARENT_CHANGED(6),
    NAME_DESCRIPTION_PARENT_CHANGED(7),
    TOPIC_CREATED(0),
    TOPIC_REMOVED(8);

    private final int id;

    TopicAction(int id) {
        this.id = id;
    }

    public static TopicAction fromValue(int value) {
        return switch (value) {
            case 1 -> NAME_CHANGED;
            case 2 -> DESCRIPTION_CHANGED;
            case 3 -> NAME_DESCRIPTION_CHANGED;
            case 4 -> PARENT_CHANGED;
            case 5 -> NAME_PARENT_CHANGED;
            case 6 -> DESCRIPTION_PARENT_CHANGED;
            case 7 -> NAME_DESCRIPTION_PARENT_CHANGED;
            case 8 -> TOPIC_REMOVED;
            default -> TOPIC_CREATED;
        };
    }

    public TopicAction addAction(TopicAction topicAdded) {
        return TopicAction.fromValue(id + topicAdded.getId());
    }
}
