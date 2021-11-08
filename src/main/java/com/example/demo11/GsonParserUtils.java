package com.example.demo11;


import com.google.gson.Gson;

public class GsonParserUtils {
   public static String parseObjectToString(Object object) {
       return new Gson().toJson(object);
   }

}
