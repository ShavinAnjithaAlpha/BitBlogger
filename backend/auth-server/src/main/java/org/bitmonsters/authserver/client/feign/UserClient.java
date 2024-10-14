package org.bitmonsters.authserver.client.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {



}
