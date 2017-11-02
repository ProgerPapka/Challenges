package task_eight;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationXmlByXsdTest {
    @Test
    public void validateXml() throws Exception {
        String pathXML = "src\\main\\resources\\thebestofpublisher.xml";
        String pathXSD = "src\\main\\resources\\thebestofpublisher.xsd";
        assertTrue(ValidationXmlByXsd.validateXml(pathXSD,pathXML));
    }

}