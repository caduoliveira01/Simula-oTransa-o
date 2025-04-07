package com.dev.simular_transacao.infrastucture.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url ="https://util.devi.tools/api/v2/authorize",name="authorization")
public interface AuthorizationClient {

    @GetMapping
    AuthorizationDTO validateAuthorization();
}
