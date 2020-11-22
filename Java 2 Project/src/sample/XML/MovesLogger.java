package sample.XML;

import Model.Move;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class MovesLogger {

    private Document movesLog;
    private Element root;

    public void createDocument() {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            movesLog = docBuilder.newDocument();
            Element moves = movesLog.createElement("GameMoves");
            movesLog.appendChild(moves);
            root = moves;
        }
        catch (ParserConfigurationException e) {
            System.out.println("Something went wrong while logging moves");
        }
    }

    public void appendMove(Move move) {

        Element moveDoc = movesLog.createElement("Move");
        moveDoc.setAttribute("figure", Integer.toString(move.getFigure()));

        Element moveX = movesLog.createElement("X");
        moveX.appendChild(movesLog.createTextNode(Integer.toString(move.getX())));
        moveDoc.appendChild(moveX);

        Element moveY = movesLog.createElement("Y");
        moveY.appendChild(movesLog.createTextNode(Integer.toString(move.getY())));
        moveDoc.appendChild(moveY);

        //movesLog.appendChild(moveDoc);
        root.appendChild(moveDoc);
    }

    public void saveDocument() throws TransformerConfigurationException, TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        int i = 1;
        String filename = "GameRepeats/game_rep_";
        while(true) {
            File f = new File(filename + i);
            if(!f.exists() && !f.isDirectory()) {

                DOMSource source = new DOMSource(movesLog);
                StreamResult result = new StreamResult(new File(filename + i));

                transformer.transform(source, result);
                transformer.transform(source, new StreamResult(System.out));

                break;
            }
            i++;
        }
    }
}
