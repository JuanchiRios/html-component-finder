package finder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String originalHtmlPath = args[0];
        String diffHtmlPath = args[1];
        String elementId = (args.length == 3) ? args[2] : "make-everything-ok-button";

        DocumentReader reader = new DocumentReader();
        XPathFactory xPathFactory = new XPathFactory();

        Document originalHtml = reader.read(originalHtmlPath);
        Document diffHtml = reader.read(diffHtmlPath);

        ComponentFinder finder = new ComponentFinder(new ElementComparator(new MapComparator()));

        Element elementFound = finder.find(elementId, originalHtml, diffHtml);

        LOGGER.info("Component path is {} and html : {}", xPathFactory.xpath(elementFound) ,elementFound.html());


    }


}
