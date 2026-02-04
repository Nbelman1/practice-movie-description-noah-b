package com.example.Movie.Description.services;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.apache.http.HttpException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeminiService {
    private final Client client = new Client();

    // query the Gemini API, return response
    public String generateDescription(String movieTitle) throws HttpException, IOException {
        String query = "Write a one-sentence description for this movie title: " + movieTitle;
        GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash-lite", query, null);

        return response.text();
    }
}
