package ru.kpfu.itis.group11408.semester1.tester;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by World on 04.04.2015.
 */
public class Engine {
    private List<Question> questions;
    private float score = 0;
    private float score_incrementation;
    public void startEngine(){
        System.out.println("Здравствуйте!\n\tЧтобы начать, напишите -start\n\tоткрыть редактор -editor");
        COMMAND_START type;
        do{
            type = Editor.getStartCommand();
            if(type == COMMAND_START.START)
            try {
                long start = System.currentTimeMillis();
                questions = QuestionParser.getQuestions("data.xml");
                score_incrementation = 100 / questions.size();
                randomize();
                showQuestion();
                long end =System.currentTimeMillis();
                System.out.println("Время: " + countTime(start, end) + " секунд");
            } catch (Exception ex){
                continue;
            }
            else if(type == COMMAND_START.EDITOR) {
                Editor editor = new Editor();
                editor.startEditor();
            }
        }while(type != COMMAND_START.EXIT);
    }

    private void showQuestion(){
        for(int i=0; i<questions.size(); i++){
            Question q = questions.get(i);
            System.out.println("Вопрос № " + (i+1) + ":");
            System.out.println(q.getBody());
            for(int a=0; a<q.getAnswers().size(); a++)
                System.out.println((a+1) + ") " + q.getAnswers().get(a));
            int get_answer = getAnswer();

            if((get_answer-1) == q.getCorrectAnswer()) {
                score += score_incrementation;
            }
        }
        showResults();

    }

    private void showResults(){
        String output = "\n\nВы завершили тест.\nВаш общий балл:  " + this.score;
        System.out.println(output);
    }
    private int getAnswer(){
        try {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextLine()) {
                String result = scanner.nextLine().toLowerCase();
                return Integer.parseInt(result);
            }
            scanner.close();
            return 0;
        }catch (Exception ex){ return 100; }
    }
    private void randomize(){
        Collections.shuffle(this.questions);
    }

    public  double countTime(long start, long end){
        long delta = end - start;
        return delta/1000.0;
    }
}
