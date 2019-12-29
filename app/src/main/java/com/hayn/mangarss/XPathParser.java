package com.hayn.mangarss;


import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

class XPathParser {


    DocumentBuilderFactory domFac;
    DocumentBuilder builder;
    Document doc;
    XPath xpath;
    XPathExpression expr;

    XPathParser(String str) {
        domFac = DocumentBuilderFactory.newInstance();
        domFac.setNamespaceAware(true);



        try {
            builder = domFac.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(str)));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        xpath = XPathFactory.newInstance().newXPath();
    }

    NodeList getResult(String strExpr){
        Object result = null;

        try{
            expr = xpath.compile(strExpr);
            result = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e){
            e.printStackTrace();
        }

        return (NodeList) result;
    }

    static String nodeListToString(NodeList nodes) throws TransformerException {
        DOMSource source = new DOMSource();
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        for (int i = 0; i < nodes.getLength(); ++i) {
            source.setNode(nodes.item(i));
            transformer.transform(source, result);
        }

        return writer.toString();
    }

}
