package org.bitmonsters.contentservice.repository;

import org.bitmonsters.contentservice.model.PostReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostReportRepository extends MongoRepository<PostReport, String> {
}
