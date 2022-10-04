package ru.geek;



import ru.geek.domain.HttpRequest;
import ru.geek.domain.HttpResponse;
import ru.geek.logger.ConsoleLogger;
import ru.geek.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler implements Runnable, RequestParser, ResponseSerializer {

    private static final String WWW = "/Users/bogdan/IdeaProjects/geek-arhcitecture/www";

    private static final Logger logger = new ConsoleLogger();

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    @Override
    public void run() {

        List<String> request = socketService.readRequest();
        HttpRequest httpRequest = parse(request);


        // TODO use here implementation of interface RequestParser
     //   String[] parts = request.get(0).split(" ");



      //  Path path = Paths.get(WWW, parts[1]);
        Path path = Paths.get(WWW, httpRequest.getPath());
        if (!Files.exists(path)) {
            // TODO use implementation of interface ResponseSerializer
         logger.info(serialize(new HttpResponse(404, "Content-Type: text/html; charset=utf-8\n")));
                  //  socketService.writeResponse(
            return;
        }

        // TODO use implementation of interface ResponseSerializer

          /*  socketService.writeResponse("HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "\n",
                    Files.newBufferedReader(path));*/
        logger.info(serialize(new HttpResponse(200, "Content-Type: text/html; charset=utf-8\n")));

        logger.info("Client disconnected!");
    }

    @Override
    public String serialize(HttpResponse httpResponse) {

        return  httpResponse.response();
    }

    //  private String method;
    //
    //    private String path;
    //
    //    private Map<String, String> headers;
    //
    //    private String body;
    @Override
    public HttpRequest parse(List<String> rawRequest) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setMethod(rawRequest.get(0));
        httpRequest.setPath(rawRequest.get(1));
        httpRequest.setBody(rawRequest.get(2));
        for (int i = 3; i < rawRequest.size(); i++) {
          //  httpRequest.setHeaders(rawRequest.get(i), rawRequest.get(i));
            Map<String, String> map = new HashMap<>();
            map.put(rawRequest.get(i), rawRequest.get(i));
            httpRequest.setHeaders(map);
        }
        return httpRequest;
    }

}
