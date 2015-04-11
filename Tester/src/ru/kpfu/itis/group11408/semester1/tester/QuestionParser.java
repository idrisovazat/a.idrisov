package ru.kpfu.itis.group11408.semester1.tester;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class QuestionParser {
    public static List<Question> getQuestions(String Filename) throws ParserConfigurationException, IOException, SAXException {
        List<Question> questions = new ArrayList<Question>();
        File file = new File("data.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        NodeList nodeList = doc.getElementsByTagName("question");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String id = element.getAttribute("id");
                String body = element.getElementsByTagName("body").item(0).getTextContent();
                NodeList nl = element.getElementsByTagName("answer");
                List<String> answers = new ArrayList<String>();
                for(int a=0; a<nl.getLength(); a++) {
                    answers.add(nl.item(a).getTextContent());
                }

                String correctAnswer = element.getElementsByTagName("correctAnswer").item(0).getTextContent();
                questions.add(new Question(id, body, answers, Integer.parseInt(correctAnswer)));
            }
        }
        return questions;
    }


}
