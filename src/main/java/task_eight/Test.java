package task_eight;

import org.apache.log4j.Logger;
import task_eight.dom.DomParser;
import task_eight.sax.SaxParser;
import task_four.second.domain.Publisher;

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);

    public void process() {
        String pathToXML = "src\\main\\resources\\thebestofpublisher.xml";
        String pathToXSD = "src\\main\\resources\\thebestofpublisher.xsd";
        ValidationXmlByXsd.validateXml(pathToXSD,pathToXML);
        DomParser domParser = new DomParser();
        SaxParser saxParser = new SaxParser();
        Publisher publisherDom = domParser.parsePublisher(pathToXML);
        Publisher publisherSax = saxParser.parsePublisher(pathToXML);
        logger.info("DOM: " + publisherDom);
        logger.info("SAX: " + publisherSax);
    }

}
