package finder;

import finder.exception.DocumentReaderException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentReader {

    private static final String CHARSET_NAME = "utf8";
    private static Logger LOGGER = LoggerFactory.getLogger(DocumentReader.class);

    public Document read(String path) {
        File htmlFile = new File(path);

        try {
            return Jsoup.parse(htmlFile, CHARSET_NAME, htmlFile.getAbsolutePath());
        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", htmlFile.getAbsolutePath(), e);
            throw new DocumentReaderException("Error reading file: " + htmlFile.getAbsolutePath());
        }
    }
}