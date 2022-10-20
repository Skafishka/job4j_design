package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "out";
    private static final String STOP = "stop";
    private static final String CONTINUE = "continue";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("Lets start");
        Random randomBotAnswer = new Random();
        List<String> answerOfBot = readPhrases();
        List<String> chatHistory = new ArrayList<>();
        String call;
        boolean isOut = false;
        boolean isContinue = true;

        while (!isOut) {
            call = input.nextLine();
            String answer = answerOfBot.get(randomBotAnswer.nextInt(answerOfBot.size()));
            chatHistory.add(call);
            switch (call) {
                case OUT:
                    isOut = true;
                    break;
                case STOP:
                    isContinue = false;
                    break;
                case CONTINUE:
                    isContinue = true;
                    chatHistory.add(answer);
                    System.out.println(answer);
                    break;
                default:
                    if (isContinue) {
                        System.out.println(answer);
                        chatHistory.add(answer);
                    }
            }
        }
        saveLog(chatHistory);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chat.txt", "./data/answerOfBot.txt");
        cc.run();
    }

}