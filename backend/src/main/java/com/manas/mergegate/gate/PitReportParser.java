package com.manas.mergegate.gate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** Parses PIT's own mutations.xml output. No PIT internals are reimplemented;
 * this only reads the report PIT already produced from real mutation testing
 * against real compiled corpus classes and their real JUnit tests. */
public final class PitReportParser {

    public List<Mutation> parse(File mutationsXml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Document doc = factory.newDocumentBuilder().parse(mutationsXml);
            NodeList nodes = doc.getElementsByTagName("mutation");
            List<Mutation> out = new ArrayList<>();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element el = (Element) nodes.item(i);
                String status = el.getAttribute("status");
                String mutatedClass = text(el, "mutatedClass");
                int lineNumber = Integer.parseInt(text(el, "lineNumber"));
                String mutator = text(el, "mutator");
                out.add(new Mutation(mutatedClass, lineNumber, status, mutator));
            }
            return out;
        } catch (Exception e) {
            throw new RuntimeException("failed to parse " + mutationsXml, e);
        }
    }

    private static String text(Element parent, String tag) {
        NodeList list = parent.getElementsByTagName(tag);
        if (list.getLength() == 0) {
            return "";
        }
        return list.item(0).getTextContent();
    }
}
