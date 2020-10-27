package com.feazesa.infrastructure.factory;

import com.feazesa.infrastructure.utils.FactoryUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;

@Log4j2
@Component
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class URLReaderFactory {

    private FactoryUtils factoryUtils;

    // Generic method to read content of a given url
    // it is currently used for user and url extraction
    Stream<String> readContentOfURL(String stringURL) {
        try {
            final URL url = new URL(stringURL);
            return new BufferedReader(new InputStreamReader(url.openStream())).lines();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Searches root urls inside the source code so they can be user for content reading later
    List<String> urls() {

        return factoryUtils.getRootUrls();
//        .stream()
//        .filter(factoryUtils::matchPartsOfHTMLFileToExtractURLS)
//        .map(factoryUtils::getURLFromStringLine)
//        .collect(Collectors.toList());
    }
}
