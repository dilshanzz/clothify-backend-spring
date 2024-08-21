package org.example.controller;

import org.example.dto.DistanceMatrixDto;
import org.example.service.impl.DistanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
@RequestMapping("/api/distance")
public class DistanceController {

    private final DistanceServiceImpl distanceMatrixService;

    @Autowired
    public DistanceController(DistanceServiceImpl distanceMatrixService) {
        this.distanceMatrixService = distanceMatrixService;
    }

    @GetMapping
    public ResponseEntity<DistanceMatrixDto> getDistance(@RequestParam String origin, @RequestParam String destination) throws Exception {
        ResponseEntity<DistanceMatrixDto> response = distanceMatrixService.getDistance(origin, destination);
        return ResponseEntity.ok(response.getBody());
    }
}