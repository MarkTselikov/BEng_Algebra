package sample.XML;

import java.util.LinkedList;
import java.util.List;
import Model.Move;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {

    private List<Move> movesList = new LinkedList<>();

    private Move currentMove;
    private String currentElement;

    public List<Move> getMovesList() { return movesList; }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        currentElement = qName;

        if ("Move".equals(currentElement)) {
            currentMove = new Move();
            currentMove.setFigure(Integer.parseInt(attributes.getValue("figure")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("Move".equals(qName)) {
            movesList.add(currentMove);
            currentMove = null;
            return;
        }
        currentElement = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();
        if(value.length() == 0) return;

        switch(currentElement) {
            case "X" : currentMove.setX(Integer.parseInt(value)); break;
            case "Y" : currentMove.setY(Integer.parseInt(value)); break;
        }
    }
}
