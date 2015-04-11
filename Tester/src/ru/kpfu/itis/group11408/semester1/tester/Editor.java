package ru.kpfu.itis.group11408.semester1.tester;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by World on 04.04.2015.
 */
public class Editor {

    public static Map<String, COMMAND_TYPE> commands = new HashMap<String, COMMAND_TYPE>(){{
        put("-add", COMMAND_TYPE.ADD);
        put("-break", COMMAND_TYPE.BREAK);
        put("-delete", COMMAND_TYPE.DELETE);
        put("-edit", COMMAND_TYPE.EDIT);
        put("-exit", COMMAND_TYPE.EXIT);
        put("-unknown", COMMAND_TYPE.UNKNOWN);
    }};
    public static Map<String, COMMAND_START> startCommands = new  HashMap<String, COMMAND_START>(){{
        put("-start", COMMAND_START.START);
        put("-editor", COMMAND_START.EDITOR);
        put("-exit", COMMAND_START.EXIT);
    }};
    public void startEditor(){
        System.out.println("Commands:");
        for(String s : commands.keySet()){
            if(s!="-unknown")
            System.out.println("\t" + s);
        }

        COMMAND_TYPE command_type;
        do{
            System.out.println("Напишите команду:");
            if((command_type = getCommand()) == null) {
                command_type = COMMAND_TYPE.UNKNOWN;
                continue;
            }
            if (command_type == COMMAND_TYPE.ADD) {
                addMode();
            } else if (command_type == COMMAND_TYPE.DELETE) {
                deleteMode();
            } else if (command_type == COMMAND_TYPE.EDIT) {
                editMode();
            } else if (command_type == COMMAND_TYPE.EXIT) {
                System.out.println("Вы вышли из редактора");
                break;
            } else
                System.out.println();
        }while(!command_type.equals(COMMAND_TYPE.EXIT));
    }

    private void addQuestion(String _body, List<String> _answers, String _correctAnswer) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("data.xml");
        Element root = document.getDocumentElement();
        Element question = document.createElement("question");
        question.setAttribute("id", generateID());
        Element body = document.createElement("body");
        body.appendChild(document.createTextNode(_body));
        question.appendChild(body);

        for(String s : _answers){
            Element answer = document.createElement("answer");
            answer.appendChild(document.createTextNode(s));
            question.appendChild(answer);
        }

        Element correctAnswer = document.createElement("correctAnswer");
        int ca = Integer.parseInt(_correctAnswer);
        ca--;
        correctAnswer.appendChild(document.createTextNode(Integer.toString(ca)));
        question.appendChild(correctAnswer);
        root.appendChild(question);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult("data.xml");
        transformer.transform(source, result);
    }
    private void addMode(){
        System.out.println("Напишите вопрос:");
        String body = getString();
        System.out.println("Напишите ответы:");
        List<String> answers = new ArrayList<String>();
        while(true){
            String s = getString();
            if(s.equals("-break"))
                break;
            answers.add(s);
        }
        System.out.println("Напишите правильный вопрос:");
        String correctAnswer = getString();
        try {
            addQuestion(body, answers, correctAnswer);
        } catch (Exception ex){
            System.out.println("Не удалось добавить вопрос");
        }
    }
    private void deleteMode() {
        List<Question> questions=new ArrayList<Question>();
        try{
            questions = QuestionParser.getQuestions("data.xml");
        }catch(Exception ex){
            System.out.println("Что-то пошло не так");
        }
        for(Question q : questions){
            System.out.println("ID вопроса: " + q.getId());
            System.out.println(q.getBody());
        }
        while(true) {
            System.out.println("Напишите ID вопроса, чтобы удалить его:");
            String id = getString();
            if(id.equals("-break"))
                break;
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).getId().equals(id)) {
                    questions.remove(i);

                    System.out.println("удалено!" + questions.size());
                }
            }
        }
        try{
            PrintWriter pw = new PrintWriter("data.xml");
            pw.flush();
            pw.write("<questions></questions>");
            pw.close();
        }catch (Exception ex){}
        for(Question q : questions){
            try {
                addQuestion(q.getBody(), q.getAnswers(), Integer.toString(q.getCorrectAnswer()));
            } catch (Exception ex){ continue;}
        }
    }
    private void editMode(){
        List<Question> questions=new ArrayList<Question>();
        try{
            questions = QuestionParser.getQuestions("data.xml");
        }catch(Exception ex){
            System.out.println("Что-то пошло не так");
        }
        for(int i=0; i<questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("ID вопроса: " + q.getId());
            System.out.println("Вопрос № " + (i + 1) + ":");
            System.out.println(q.getBody());
            for(int a=0; a<q.getAnswers().size(); a++)
                System.out.println((a+1) + ") " + q.getAnswers().get(a));
        }

        while(true) {
            System.out.println("Напишите ID вопроса, чтобы изменить его:");
            String id = getString();
            if(id.equals("-break"))
                break;
            for (int i = 0; i < questions.size(); i++) {
                if (questions.get(i).getId().equals(id)) {
                    Question n = questions.remove(i);
                    System.out.println("Напишите вопрос:");
                    String body = getString();
                    System.out.println("Напишите ответы:");
                    List<String> answers = new ArrayList<String>();
                    while(true){
                        String s = getString();
                        if(s.equals("-break"))
                            break;
                        answers.add(s);
                    }
                    System.out.println("Напишите правильный ответ:");
                    String correctAnswer = getString();
                    int ca = Integer.parseInt(correctAnswer);
                    ca--;
                    questions.add(new Question(n.getId(),body, answers, ca));
                }
            }
        }
        try{
            PrintWriter pw = new PrintWriter("data.xml");
            pw.flush();
            pw.write("<questions></questions>");
            pw.close();
        }catch (Exception ex){}
        for(Question q : questions){
            try {
                addQuestion(q.getBody(), q.getAnswers(), Integer.toString(q.getCorrectAnswer()));
            } catch (Exception ex){ }
        }
    }
    private String generateID(){
        return UUID.randomUUID().toString();
    }
    public static COMMAND_START getStartCommand(){
        String command = getString().toLowerCase();
        COMMAND_START result = startCommands.get(command);
        if(result == null) {
            return COMMAND_START.UNKNOWN;
        }
        return result;
    }
    public static COMMAND_TYPE getCommand(){
        String command = getString().toLowerCase();
        COMMAND_TYPE result = commands.get(command);
        if(result == null) {
            return COMMAND_TYPE.UNKNOWN;
        }
        return result;
    }
    private static String getString(){
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()) {
            String result = scanner.nextLine();
            return result;
        }
        scanner.close();
        return null;
    }
}
