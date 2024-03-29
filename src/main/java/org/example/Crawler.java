package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {

    HashSet<String> urlSet;

    int MAX_DEPTH= 2;

    //initialine urlSet using Constructor
    Crawler(){
        urlSet = new HashSet<String>();
    }


    //Recursive crawler bot
    public void getPageTextsAndLinks(String url,int dep){
        if(urlSet.contains(url)){
            return;
        }

        if(dep>=MAX_DEPTH){
            return;
        }

        if(urlSet.add(url)){
            System.out.println(url);
        }
        dep++;
        try {

            //parsing html pages and jsoup coverting html obj to java obj
            Document document = Jsoup.connect(url).timeout(5000).get();

            //Indexer Work Starts Here
            Indexer indexer = new Indexer(document,url);
            System.out.println(document.title());

            //
            Elements availableLinksOnPage = document.select("a[href]");

            //selecting all the available links in page
            for(Element currentLink: availableLinksOnPage){
                getPageTextsAndLinks(currentLink.attr("abs:href"), dep);
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPageTextsAndLinks("http://www.javatpoint.com",0);
    }
}