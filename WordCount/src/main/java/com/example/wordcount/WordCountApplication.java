package com.example.wordcount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WordCountApplication extends SpringBootServletInitializer{

  public static void main(String[] args) {
    SpringApplication.run(WordCountApplication.class, args);
  }
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    //war作成のための設定
    return application.sources(WordCountApplication.class);
  }
}
