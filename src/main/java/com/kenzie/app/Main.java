package com.kenzie.app;

// import necessary libraries
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.gamedto.Clues;
import com.kenzie.app.gamedto.GameDTO;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String URL = "https://jservice.kenzie.academy/api/clues";

    public static void main(String[] args) throws JsonProcessingException {
        int score = 0;
        Scanner scanner = new Scanner(System.in);
        GameDTO gameDTO;
        ObjectMapper mapper = new ObjectMapper();
        Random r = new Random();
        List<Clues> cluesList;
        try {
            // get list of questions with client
            String response = CustomHttpClient.sendGET(URL);
            gameDTO = mapper.readValue(response, GameDTO.class);
            cluesList = gameDTO.getClues();
            // start game
            for (int i = 0; i < 10; i++) {
                // ask user a random question
                int randNum = r.nextInt(100);
                // retrieve category, question, and answer from random index
                String category = cluesList.get(randNum).getCategory().getTitle();
                String question = cluesList.get(randNum).getQuestion();
                String answer = cluesList.get(randNum).getAnswer();
                System.out.println("Category: " + category);
                System.out.println("Question: " + question);
                // take in user input
                String guess = scanner.nextLine();
                // if correct, add point (case insensitive)
                if (guess.equalsIgnoreCase(answer)) {
                    score++;
                    System.out.println("Good job, that was correct! Your score is: " + score);
                } else {
                    System.out.println("Incorrect. The correct answer was: " + answer);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
        // print results
        System.out.println("Your final score: " + score);
    }

}

