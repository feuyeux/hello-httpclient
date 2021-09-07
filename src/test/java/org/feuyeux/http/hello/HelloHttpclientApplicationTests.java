package org.feuyeux.http.hello;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloHttpclientApplicationTests {

  @Test
  void testHello() throws Exception {
    MockWebServer mockWebServer = new MockWebServer();
    mockWebServer.start();

    MockResponse mockResponse = new MockResponse()
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .setBody("{\"id\": 1, \"name\":\"duke\"}")
        .throttleBody(10, 3, TimeUnit.SECONDS);
    mockWebServer.enqueue(mockResponse);
    mockWebServer.enqueue(mockResponse);

    Hello  hello = new Hello(mockWebServer.url("/").toString());
    hello.asynchronous();
    hello.synchronous();
    mockWebServer.shutdown();
  }
}
