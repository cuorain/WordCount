package com.example.wordcount.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.wordcount.model.ArticleForm;

@Controller
public class TestController {
  @GetMapping("/test")
  private String index(@ModelAttribute("form") ArticleForm form, Model model) {
    try {
      HttpRequest request = HttpRequest
          .newBuilder(URI.create("http://127.0.0.1/"))
          .build();

      BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8);
      HttpResponse<String> response = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build().send(request, bodyHandler);
      System.out.println(response.body());
    } catch (Exception e) {
      StringBuilder sb = new StringBuilder();
      sb.append(e.getClass()).append(":").append(e.getMessage()).append("\n");
      StackTraceElement[] stackTrace = e.getStackTrace();
      for( StackTraceElement s : stackTrace ) {
        sb.append(s.toString()).append("\n");
      }
      model.addAttribute("stacktrace", sb.toString());
      return "error/error";
    }
    model.addAttribute("wordCount", 111);
    return "index";
  }
}
