package finder;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComponentFinderTest {
    private static final String ORIGINAL_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-0-origin.html";
    private static final String DIFF_1_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html";
    private static final String DIFF_2_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-2-container-and-clone.html";
    private static final String DIFF_3_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-3-the-escape.html";
    private static final String DIFF_4_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-4-the-mash.html";

    private ComponentFinder finder;
    private DocumentReader reader;
    private Document originalHtml;

    @Before
    public void setUp(){
        this.finder = new ComponentFinder(new ElementComparator(new MapComparator()));
        this.reader = new DocumentReader();
        this.originalHtml = this.reader.read(ORIGINAL_HTML_PATH);
    }

    @Test
    public void originVsDiff1ShouldHaveTextNodeMakeEverythingOk(){
        Document diffHtml = this.reader.read(DIFF_1_HTML_PATH);

        Assert.assertEquals("Make everything OK", this.finder.find("make-everything-ok-button", originalHtml, diffHtml).ownText());
    }

    @Test
    public void originVsDiff2ShouldHaveTextNodeMakeEverythingOk(){
        Document diffHtml = this.reader.read(DIFF_2_HTML_PATH);

        Assert.assertEquals("Make everything OK", this.finder.find("make-everything-ok-button", originalHtml, diffHtml).ownText());
    }

    @Test
    public void originVsDiff3ShouldHaveTextNodeDoAnythingPerfect(){
        Document diffHtml = this.reader.read(DIFF_3_HTML_PATH);

        Assert.assertEquals("Do anything perfect", this.finder.find("make-everything-ok-button", originalHtml, diffHtml).ownText());
    }

    @Test
    public void originVsDiff4ShouldHaveTextNodeDoAllGreat(){
        Document diffHtml = this.reader.read(DIFF_4_HTML_PATH);

        Assert.assertEquals("Do all GREAT", this.finder.find("make-everything-ok-button", originalHtml, diffHtml).ownText());
    }

}
