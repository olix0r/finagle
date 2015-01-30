package com.twitter.finagle.javaapi;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.jboss.netty.buffer.ChannelBuffers;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.httpx.Http;
import com.twitter.finagle.httpx.Ask;
import com.twitter.finagle.httpx.Response;
import com.twitter.util.Future;

public class HttpServerTest {
  private static void runServer() {
    Service<Ask, Response> service =
      new Service<Ask, Response>() {
        public Future<Response> apply(Ask request) {
          Response response = Response.apply();
          // Respond right away.
          response.setContent(ChannelBuffers.wrappedBuffer("yo".getBytes()));

          Future<Response> future = Future.value(response);
          return future;
        }
      };

    ServerBuilder.safeBuild(
      service,
        ServerBuilder.get()
            .codec(Http.get())
            .bindTo(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0))
      .name("HttpServer"));
  }

  public static void main(String args[]) {
    try {
      runServer();
    } catch (Throwable e) {
      System.err.println("Caught top level exception: " + e);
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
