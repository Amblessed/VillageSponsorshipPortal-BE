package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 13-Oct-25
 */


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.villageportal.exception.OpenAIException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class OpenAIService {

    private final ObjectMapper mapper = new ObjectMapper();

    public String executeOpenAiRequest(String name, String term, List<String> subjects, List<String> scores, List<String> descriptors) throws IOException {
        HttpPost post = getHttpPupilCommentPost(name, term, subjects, scores, descriptors);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            return client.execute(post, response -> {
                int status = response.getCode();
                if (status >= 200 && status < 300) {
                    if (response.getEntity() != null) {
                        String apiResponse = EntityUtils.toString(response.getEntity());
                        JsonNode firstChoice = extractFirstChoice(apiResponse);
                        return extractContentFromChoice(firstChoice);
                    }
                    return null;
                }
                else {
                    throw new IOException("Unexpected response status: " + status);
                }
            });
        }
    }


    public HttpPost getHttpPupilCommentPost (String name, String term, List<String> subjects, List<String> scores, List<String> descriptors) throws IOException {
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String apiKey = System.getenv("OPENAI_API_KEY");
        String prompt = String.format("""
            Name: %s
            Term: %s
            Subjects: %s
            Scores: %s
            Descriptors: %s

            Write a warm, sponsor-friendly comment in 2–3 sentences. Be honest but encouraging. Avoid repeating descriptors directly.
            Look at the scores in each subject and commend her where she excels and where she needs improvement.
            """, name, term, String.join(", ", subjects), String.join(", ", scores), String.join(", ", descriptors));

        ObjectNode systemMessage = mapper.createObjectNode();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a compassionate teacher who writes warm, sponsor-friendly report card comments.");

        ObjectNode userMessage = mapper.createObjectNode();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        ArrayNode messagesArray = mapper.createArrayNode();
        messagesArray.add(systemMessage);
        messagesArray.add(userMessage);

        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.set("messages", messagesArray);
        String jsonBody =  mapper.writeValueAsString(requestBody);

        HttpPost post = new HttpPost(apiUrl);
        post.setHeader("Authorization", "Bearer " + apiKey);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
        return post;
    }

    public String extractContentFromChoice(JsonNode firstChoice) {
        JsonNode message = firstChoice.path("message");
        if (message.isMissingNode() || !message.has("content")) {
            if (firstChoice.has("text")) {
                return firstChoice.get("text").asText().trim();
            } else {
                throw new OpenAIException("No content returned from OpenAI API.");
            }
        } else {
            return message.get("content").asText().trim();
        }
    }

    public JsonNode extractFirstChoice(String result) throws IOException {
        if (result == null) {
            throw new OpenAIException("Empty response from OpenAI API");
        }
        JsonNode root = mapper.readTree(result);
        JsonNode choices = root.path("choices");

        if (!choices.isArray() || choices.isEmpty() || choices.get(0) == null) {
            throw new OpenAIException("No valid choice returned from OpenAI API.");
        }
        return choices.get(0);
    }

}
