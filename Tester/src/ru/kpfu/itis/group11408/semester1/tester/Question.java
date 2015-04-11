package ru.kpfu.itis.group11408.semester1.tester;

import java.util.List;

public class Question {
    private String id;
    private String body;
    private List<String> answers;
    private int correctAnswer;
    public Question(String _id, String _body, List<String> _answers, int _correctAnswer){
        this.id = _id;
        this.body = _body;
        this.answers = _answers;
        this.correctAnswer = _correctAnswer;
    }

    public String getId(){return id;}
    public String getBody(){ return body; }
    public List<String> getAnswers(){ return answers; }
    public int getCorrectAnswer(){return correctAnswer;}

}
