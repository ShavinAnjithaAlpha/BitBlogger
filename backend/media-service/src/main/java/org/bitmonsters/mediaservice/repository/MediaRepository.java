package org.bitmonsters.mediaservice.repository;

import org.bitmonsters.mediaservice.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MediaRepository extends MongoRepository<Media, String> {


    Media findByFileUrl(String url);

    void deleteByFileUrl(String url);
}
