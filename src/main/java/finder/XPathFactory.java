package finder;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XPathFactory {
    public String xpath(Element htmlElement) {
        StringBuilder absPath = new StringBuilder();
        Elements parents = htmlElement.parents();

        for (int j = parents.size() - 1; j >= 0; j--) {
            Element element = parents.get(j);
            absPath.append("/");
            absPath.append(element.tagName());
            absPath.append("[");
            absPath.append(element.siblingIndex());
            absPath.append("]");
        }
        return absPath.toString();
    }
}
