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

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL_NAME = "gpt-4o-mini";
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Executes a request to the OpenAI API and returns the model's response text.
     */
    public String executeOpenAiRequest(String name, String term, List<String> subjects,
                                       List<String> scores, List<String> descriptors) throws IOException {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = buildRequest(name, term, subjects, scores, descriptors);
            return client.execute(post, response -> {
                int status = response.getCode();
                if (status < 200 || status >= 300) {
                    throw new IOException("Unexpected response status: " + status);
                }
                String apiResponse = EntityUtils.toString(response.getEntity());
                JsonNode choice = extractFirstChoice(apiResponse);
                return extractContent(choice);
            });
        }
    }

    /**
     * Builds the HTTP POST request body for the pupil comment generation.
     */
    private HttpPost buildRequest(String name, String term, List<String> subjects,
                                  List<String> scores, List<String> descriptors) throws IOException {

        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new OpenAIException("OpenAI API key not found in environment variables.");
        }

        String prompt = String.format("""
                Student Name: %s
                Term: %s
                Subjects: %s
                Scores: %s
                Descriptors: %s
                
                As a professional teacher, write a concise, sponsor-appropriate report comment in 2–3 sentences.
                Maintain a formal and balanced tone that reflects the pupil’s academic performance and attitude during the term.
                Acknowledge areas of strong achievement and note aspects where further progress or consistency is encouraged.
                Avoid repeating the descriptors directly; instead, paraphrase them naturally.
                """, name, term, String.join(", ", subjects), String.join(", ", scores), String.join(", ", descriptors));

        ObjectNode requestBody = mapper.createObjectNode()
                .put("model", MODEL_NAME)
                .set("messages", buildMessages(prompt));

        HttpPost post = new HttpPost(API_URL);
        post.setHeader("Authorization", "Bearer " + apiKey);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(mapper.writeValueAsString(requestBody), ContentType.APPLICATION_JSON));

        return post;
    }

    /**
     * Builds the chat messages array for the API request.
     */
    private ArrayNode buildMessages(String prompt) {
        ArrayNode messages = mapper.createArrayNode();

        messages.add(createMessage("system",
                "You are a compassionate teacher who writes warm, sponsor-friendly report card comments."));
        messages.add(createMessage("user", prompt));

        return messages;
    }

    private ObjectNode createMessage(String role, String content) {
        ObjectNode message = mapper.createObjectNode();
        message.put("role", role);
        message.put("content", content);
        return message;
    }

    /**
     * Extracts the first valid choice node from the API response.
     */
    private JsonNode extractFirstChoice(String result) throws IOException {
        if (result == null || result.isBlank()) {
            throw new OpenAIException("Empty response from OpenAI API.");
        }

        JsonNode root = mapper.readTree(result);
        JsonNode choices = root.path("choices");

        if (!choices.isArray() || choices.isEmpty()) {
            throw new OpenAIException("No valid choice returned from OpenAI API.");
        }

        return choices.get(0);
    }

    /**
     * Extracts the message content from the choice node.
     */
    private String extractContent(JsonNode choice) {
        JsonNode message = choice.path("message");
        if (message.has("content")) {
            return message.get("content").asText().trim();
        }
        if (choice.has("text")) {
            return choice.get("text").asText().trim();
        }
        throw new OpenAIException("No content returned from OpenAI API.");
    }
}
