package org.example;

import org.example.config.ConfigLoader;
import org.example.dto.Config;
import org.example.exceptions.QuestionNotFoundException;

public class Main {
   public static void main(String[] args) throws QuestionNotFoundException {
        QuestionService questionService = new QuestionService();
        questionService.initByConfig(new Config());
       System.out.println(questionService.getQuestionById(0).getTitle());
   }
}
