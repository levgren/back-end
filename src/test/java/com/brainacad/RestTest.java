package com.brainacad;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class RestTest{

    private static final String URL="https://reqres.in/";

    @Test//GET метод
    public void checkGetResponseStatusCode() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
       // Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        //headers.put("User-Agent", "My-Test-User-Agent");

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//GET метод
    public void checkGetResponseBodyNotNull() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
       // Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        //headers.put("User-Agent", "My-Test-User-Agent");

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test//POST метод
    public void checkPostResponseStatusCode() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
       // Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
       // headers.put("User-Agent", "My-Test-User-Agent");

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 201", 201, statusCode);
    }

    @Test//POST метод
    public void checkPostResponseBodyNotNull() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
       //Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        //headers.put("User-Agent", "My-Test-User-Agent");

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    //TODO: напишите по тесткейсу на каждый вариант запроса на сайте https://reqres.in
    //TODO: в тескейсах проверьте Result Code и несколько параметров из JSON ответа (если он есть)

    @Test//GET метод
    public void SingleUser() throws IOException {
        String endpoint="/api/users/2";

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint, null);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
        Assert.assertEquals("First name should be Janet","Janet",JsonUtils.stringFromJSONByPath(body,"$.data.first_name"));
        Assert.assertEquals("Last name should be Weaver","Weaver",JsonUtils.stringFromJSONByPath(body,"$.data.last_name"));

    }

    @Test
    public void UPDATE() throws IOException {
        String endpoint="/api/users/2";
        String patchBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";
        HttpResponse patch = HttpClientHelper.patch(URL + endpoint, patchBody);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(patch);
        System.out.println(body);
        int statusCode = (patch).getStatusLine().getStatusCode();
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
        String dateStr=JsonUtils.stringFromJSONByPath(body,"$.updatedAt");
        String date=dateStr; //|сконвертировать к данным
        Assert.assertEquals("Name should be zion morpheus","morpheus",JsonUtils.stringFromJSONByPath(body,"$.updatedAt"));

    }
}
