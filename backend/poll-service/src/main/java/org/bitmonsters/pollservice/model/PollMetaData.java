package org.bitmonsters.pollservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollMetaData {

    @Column(name = "optional_answer_allowed")
    private Boolean optionalAnswerAllowed;

    @Column(name = "has_answer", nullable = false)
    private Boolean hasAnswer;

    @Column(name = "answer_visibility")
    private Boolean answerVisibility;

    @Column(name = "vote_visibility", nullable = false)
    private Boolean voteVisibility;

}
