package finder;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ElementComparator {
    private MapComparator mapComparator;
    private static Logger LOGGER = LoggerFactory.getLogger(ElementComparator.class);

    private Function<Boolean, Float> binaryMark = condition -> (condition) ? 100f : 0f;

    public ElementComparator(MapComparator collectionComparator) {
        this.mapComparator = collectionComparator;
    }

    public Float compare(Element anElement, Element otherElement) {
        Float tagMark = this.compareTag(anElement, otherElement) * 0.3f;
        Float attributesMark = this.compareAttributtes(anElement, otherElement) * 0.2f;
        Float textMark = this.compareText(anElement, otherElement) * 0.2f;
        Float childrensMark =  this.compareChildrens(anElement, otherElement) * 0.2f;
        Float cssMark =  this.compareCss(anElement, otherElement) * 0.1f;

        return tagMark + attributesMark + textMark + childrensMark + cssMark;
    }

    private Float compareChildrens(Element anElement, Element otherElement) {
        Elements childs1 = anElement.children();
        Elements childs2 = otherElement.children();
        if(childs1.isEmpty() && childs2.isEmpty()) return 100f;

        Double occurrences = IntStream.range(0, Math.min(anElement.children().size(), otherElement.children().size()))
                .mapToDouble(i -> this.compare(childs1.get(i), childs2.get(i)))
                .sum();

        return occurrences.floatValue() / Math.max(anElement.children().size(), otherElement.children().size());

    }

    private Float compareText(Element anElement, Element otherElement) {
        return binaryMark.apply(anElement.ownText().equals(otherElement.ownText()));
    }

    private Float compareAttributtes(Element anElement, Element otherElement) {
        return mapComparator.compareMap(anElement.dataset(), otherElement.dataset());
    }

    private Float compareCss(Element anElement, Element otherElement) {
        HashSet<String> mergeStyles = new HashSet<>();
        Set<String> anElementStyles = anElement.classNames();
        Set<String> otherElementStyles = otherElement.classNames();
        mergeStyles.addAll(anElementStyles);
        mergeStyles.addAll(otherElementStyles);

        //TODO refactor, same behaviour in mapComparator
        //Two empty set are equal
        if (mergeStyles.size() == 0) return 100f;

        Integer occurrences = mergeStyles.stream()
                .mapToInt(mergeClass ->
                        (anElementStyles.contains(mergeClass)
                                && otherElementStyles.contains(mergeClass))
                                ? 1 : 0)
                .sum();

        return occurrences * 100f / mergeStyles.size();
    }

    private Float compareTag(Element anElement, Element otherElement) {
        return binaryMark.apply(anElement.tagName().equals(otherElement.tagName()));
    }

}
