package ru.geek;



import ru.geek.domain.HttpRequest;

import java.util.List;

public interface RequestParser {

    HttpRequest parse(List<String> rawRequest);
}
