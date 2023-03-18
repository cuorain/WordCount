package com.example.wordcount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.wordcount.model.ArticleForm;
import com.example.wordcount.service.WordCountService;

@Controller
public class HomeController {
  @Autowired
  WordCountService wordCountService;

  @GetMapping("/")
  private String index(@ModelAttribute("form") ArticleForm form) {
      return "index";
  }

  @PostMapping("/")
  private String getWordCount(@ModelAttribute("form") ArticleForm form, Model model) {
      try {
        final int wordCount = wordCountService.getWordCount(form.getUrl());
        model.addAttribute("wordCount", wordCount);
      }catch(Exception ex) {
        model.addAttribute("status", "500");
        model.addAttribute("message", ex.getMessage());
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getClass()).append(":").append(ex.getMessage()).append("\n");
        StackTraceElement[] stackTrace = ex.getStackTrace();
        for( StackTraceElement s : stackTrace ) {
          sb.append(s.toString()).append("\n");
        }
        model.addAttribute("stacktrace", sb.toString());
        return "error/error";
      }
      return "index";
  }
}
