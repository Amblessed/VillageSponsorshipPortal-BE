package com.villageportal.controller;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Oct-25
 */


import com.villageportal.request.PupilCommentRequest;
import com.villageportal.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
public class OpenAIController {

    private final OpenAIService openAIService;


    @PostMapping("/pupil")
    public ResponseEntity<String> getPupilComment(@RequestBody PupilCommentRequest request) throws IOException {
        String comment = openAIService.executeOpenAiRequest(
                request.getName(),
                request.getTerm(),
                request.getSubjects(),
                request.getScores(),
                request.getDescriptors()
        );
        return ResponseEntity.ok(comment);
    }
}
