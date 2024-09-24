package org.bitmonsters.tagservice.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.client.feign.UserClient;
import org.bitmonsters.tagservice.client.feign.UserResponse;
import org.bitmonsters.tagservice.dto.NewTagDto;
import org.bitmonsters.tagservice.dto.TagDto;
import org.bitmonsters.tagservice.dto.TagHistoryDto;
import org.bitmonsters.tagservice.dto.TagHistoryRecord;
import org.bitmonsters.tagservice.model.PostTag;
import org.bitmonsters.tagservice.model.Tag;
import org.bitmonsters.tagservice.model.TagAction;
import org.bitmonsters.tagservice.model.TagHistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagMapper {

    private final UserClient userClient;

    public Tag toTag(NewTagDto newTagDto) {
        return Tag.builder()
                .name(newTagDto.name())
                .description(newTagDto.description())
                .icon(newTagDto.icon())
                .postCount(0L)
                .build();
    }

    public TagHistory toTagHistory(Tag tag, TagAction tagAction, Long userId) {
        return TagHistory.builder()
                .tag(tag)
                .name(tag.getName())
                .description(tag.getDescription())
                .icon(tag.getIcon())
                .changedBy(userId)
                .action(tagAction)
                .build();
    }

    public TagDto toTagDto(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .description(tag.getDescription())
                .icon(tag.getIcon())
                .build();
    }

    public TagDto toTagDto(PostTag postTag) {
        return TagDto.builder()
                .id(postTag.getTag().getId())
                .name(postTag.getTag().getName())
                .description(postTag.getTag().getDescription())
                .icon(postTag.getTag().getIcon())
                .build();
    }

    public PostTag toPostTag(Long postId, Tag tag) {
        return PostTag.builder()
                .postId(postId)
                .tag(tag)
                .build();
    }

    public TagHistoryDto toTagHistoryDto(Tag tag, List<TagHistory> tagHistories) {
        return TagHistoryDto.builder()
                .tag(this.toTagDto(tag))
                .history(tagHistories.stream()
                        .map(this::toTagHistoryRecord)
                        .collect(Collectors.toList()))
                .build();
    }

    private TagHistoryRecord toTagHistoryRecord(TagHistory tagHistory) {

        UserResponse user = userClient.getUserByID(tagHistory.getChangedBy(), true);

        return TagHistoryRecord.builder()
                .id(tagHistory.getId())
                .name(tagHistory.getName())
                .description(tagHistory.getDescription())
                .icon(tagHistory.getIcon())
                .changedBy(user)
                .changedAt(tagHistory.getChangedAt())
                .action(tagHistory.getAction())
                .build();
    }
}
