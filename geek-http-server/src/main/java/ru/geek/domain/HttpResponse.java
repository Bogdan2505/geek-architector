package ru.geek.domain;

public class HttpResponse {

    private int statusCode;

    private String headers;
    // TODO


    public HttpResponse(int statusCode, String headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public String response(){
        if ( statusCode == 404) {
            return "HTTP/1.1" +  statusCode+ " NOT_FOUND\n" +
                    headers + "\n" +
                    "<h1>Файл не найден!</h1>\n";
        }else if ( statusCode == 200){
            return "HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "\n";
        }
        return null;
    }
}
