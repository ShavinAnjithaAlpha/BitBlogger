package org.bitmonsters.contentservice.repository;

import org.bitmonsters.contentservice.model.PostDraft;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDraftRepository extends MongoRepository<PostDraft, String> {
}
