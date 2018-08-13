package finder;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
    private static final String ORIGINAL_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-0-origin.html";
    private static final String DIFF_1_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-1-evil-gemini.html";
    private static final String DIFF_2_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-2-container-and-clone.html";
    private static final String DIFF_3_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-3-the-escape.html";
    private static final String DIFF_4_HTML_PATH = "./samples/startbootstrap-sb-admin-2-examples/sample-4-the-mash.html";


    @Test
    public void useOfCase1(){
        Main.main(ORIGINAL_HTML_PATH, DIFF_1_HTML_PATH);

    }

    @Test
    public void useOfCase2(){
        Main.main(ORIGINAL_HTML_PATH, DIFF_2_HTML_PATH);

    }

    @Test
    public void useOfCase3(){
        Main.main(ORIGINAL_HTML_PATH, DIFF_3_HTML_PATH);

    }

    @Test
    public void useOfCase4(){
        Main.main(ORIGINAL_HTML_PATH, DIFF_4_HTML_PATH);

    }

}

