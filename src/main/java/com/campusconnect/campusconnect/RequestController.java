package com.campusconnect.campusconnect;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RequestController {
    private final List<Map<String,Object>> requests = new ArrayList<>();
    private int nextId = 1;

    @GetMapping("/requests")
    public List<Map<String,Object>> getRequests() {
        return requests;
    }

    @PostMapping("/requests")
    public Map<String,Object> addRequest(@RequestBody Map<String,Object> request) {
        request.put("id", nextId++);
        request.put("status", "Pending");
        requests.add(request);
        return request;
    }

    @PutMapping("/requests/{id}")
    public Map<String,Object> update(@PathVariable int id, @RequestBody Map<String,String> body) {
        for (Map<String,Object> r : requests) {
            if ((int)r.get("id") == id) {
                r.put("status", body.get("status"));
                return r;
            }
        }
        return Map.of("error","Request not found");
    }
}
