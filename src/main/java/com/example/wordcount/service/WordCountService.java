package com.example.wordcount.service;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class WordCountService {
  public int getWordCount(String httpUrl) throws Exception{
    if(httpUrl.isBlank()) {
      //TODO:この辺適当すぎるので直す
      throw new IOException("URLを入力してください。");
    }
    Document doc = Jsoup.connect(httpUrl).timeout(1000 * 1000).get();
    Elements scripts = doc.select("p").not("p.ta-c, p.buttons");
    // 1つの文字列に統合
    final String combinedScripts = scripts.text();
    if(combinedScripts == null || combinedScripts.isBlank()) {
      throw new IOException("対象のテキストが見つかりませんでした。");
    }
    String cutScripts = combinedScripts;
    if(combinedScripts.indexOf("__") > 0) {
        // 語句解説がある時はカット(連続アンスコ以降が語句解説）
        cutScripts = cutScripts.substring(0, combinedScripts.indexOf("__"));
    }
    final int wordCount = cutScripts.split(" ").length;
    return wordCount;
  }
}
