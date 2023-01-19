package com.twinkles.talkamout.utils;
import org.springframework.web.client.RestTemplate;

public class TinyUrl {

        public static String shortenUrl(String longUrl) {
            String tinyUrl = "";
            try {
                RestTemplate restTemplate = new RestTemplate();
                tinyUrl = restTemplate.getForObject("http://tinyurl.com/api-create.php?url={longUrl}", String.class, longUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return tinyUrl;
        }

    }


