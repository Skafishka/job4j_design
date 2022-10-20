package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

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
        String question = input.nextLine();
        Random randomBotAnswer = new Random();
        List<String> answerOfBot = readPhrases();
        List<String> chatHistory = new ArrayList<>();
        String call = null;

        while (!OUT.equals(call)) {
            call = input.nextLine();
            String answer = answerOfBot.get(randomBotAnswer.nextInt(answerOfBot.size()));
            chatHistory.add(call);
            switch (call) {
                case STOP:
                    break;
                default:
                    if (CONTINUE.equals(call)) {
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
            return br.lines().collect(Collectors.toList());
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