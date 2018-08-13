package finder;

import finder.exception.AnyElementWasFoundSimilarException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComponentFinder {

    private static Logger LOGGER = LoggerFactory.getLogger(ComponentFinder.class);
    private final ElementComparator elementComparator;

    public ComponentFinder(ElementComparator elementComparator) {
        this.elementComparator = elementComparator;
    }

    public Element find(String elementId, Document originalHtml, Document diffHtml) {
        Element originalElement = this.findElementById(elementId, originalHtml);

        List<Element> possibleElements = getSimilarElements(originalElement, diffHtml);

        Element similarElement = possibleElements.stream()
                .collect(Collectors.maxBy(
                        Comparator.comparing(possibleElement -> this.elementComparator.compare(originalElement, possibleElement))))
                .orElseThrow(() ->
                        new AnyElementWasFoundSimilarException(
                                String.format("Element: %s was not found any similar component in document: %s"
                                        , originalElement.html()
                                        , diffHtml.baseUri())));

        return similarElement;
    }

    private List<Element> getSimilarElements(Element originalElement, Document diffHtml) {
        //Elements should be only in body Element
        Elements allElements = diffHtml.body().getAllElements();
        return allElements.stream()
                .filter(possibleElement -> this.sameChildrenSize(originalElement, possibleElement))
                .filter(possibleElement -> this.sameFilledTextNodeSize(originalElement, possibleElement))
                .collect(Collectors.toList());
    }

    private Boolean sameChildrenSize(Element anElement, Element otherElement) {
        return anElement.children().size() == otherElement.children().size();
    }

    private Boolean sameFilledTextNodeSize(Element anElement, Element otherElement) {
        return this.filledTextNodeSize(anElement).equals(this.filledTextNodeSize(otherElement));
    }

    private Long filledTextNodeSize(Element anElement) {
        return anElement.textNodes().stream().filter(textNode -> !textNode.isBlank()).count();
    }

    public Element findElementById(String id, Document document) {
        Element foundElement = document.getElementById(id);
        if(foundElement == null) {
            LOGGER.error("Must provide an existing id: {} in document: {}", id, document.baseUri());
            throw new IllegalArgumentException(String.format("Must provide an existing id: %s in document: %s", id, document.baseUri()));
        }
        return foundElement;
    }
}
