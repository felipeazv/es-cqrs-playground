package com.feazesa.infrastructure.utils;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Log4j2
@Component
public class FactoryUtils {
    // Root URLS contain more urls inside them with more information to be retrieved
    private List<String> rootUrls = List.of(
            "http://imigrantesdaeuropa.blogspot.com/2013/10/lista-de-imigrantes-que-desembarcaram.html"//,
//            "http://imigrantesdaeuropa.blogspot.com/2014/11/lista-26-de-imigrantes-que.html",
//            "http://imigrantesdaeuropa.blogspot.com/2014/12/lista-31-de-imigrantes-que.html",
//            "http://imigrantesdaeuropa.blogspot.com/2015/11/lista-38-de-imigrantes-que.html",
//            "http://imigrantesdaeuropa.blogspot.com/2016/03/lista-66-de-imigrantes-que.html",
//            "http://imigrantesdaeuropa.blogspot.com/2016/04/lista-91-de-imigrantes-que.html"
    );

    private List<String> listOfInterestingPartsOfHTMLFileToExtractUserInfo = List.of(
            "<td align=\""
    );

    private List<String> listOfInterestingPartsOfHTMLFileToExtractURLS = List.of(
            "<li><a href='http://imigrantesdaeuropa.blogspot.com/2"
    );

    public boolean matchPartsOfHTMLFileToExtractUserInfo(String line) {
        return matchPartsOfHTMLFile(listOfInterestingPartsOfHTMLFileToExtractUserInfo, line);
    }

    public boolean matchPartsOfHTMLFileToExtractURLS(String line) {
        return matchPartsOfHTMLFile(listOfInterestingPartsOfHTMLFileToExtractURLS, line);
    }

    private boolean matchPartsOfHTMLFile(List<String> list, String line) {
        return list.stream()
                .anyMatch(line::contains);
    }

    public String getUserInfoFromStringLine(String line) {
        final var endFirstTag = line.indexOf(">") + 1;
        final var endTag = line.indexOf("</td>");
        return line.substring(endFirstTag, endTag);
    }

    public String getURLFromStringLine(String line) {
        final var endFirstTag = line.indexOf("href='") + 6;
        final var endTag = line.indexOf("'>");
        return line.substring(endFirstTag, endTag);
    }
}
