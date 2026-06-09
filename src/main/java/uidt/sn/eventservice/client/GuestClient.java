package uidt.sn.eventservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "guest-service", url = "http://localhost:8082")
public interface GuestClient {

    @GetMapping("/guests")
    List<String> getGuests(
            @RequestHeader("X-User") String user,
            @RequestHeader("X-Role") String role
    );
}