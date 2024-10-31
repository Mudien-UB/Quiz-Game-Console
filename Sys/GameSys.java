package Sys;

import java.util.*;

public class GameSys extends WordProviderImpl {
    private final Scanner scanner = new Scanner(System.in);
    private Map<String, String> questdata = new HashMap<>();
    private final Set<String> questPrev = new HashSet<>();
    private int score = 0;

    public void start() {
        Level level = selectLevel();
        String isProgammer;

        while(true) {
            System.out.println("Are you a Programmer? (Y/N/Enter is yes)");
            String confirm = scanner.nextLine().toUpperCase();

            if(confirm.equals("Y") || confirm.isEmpty()) {
                isProgammer = "Y";
                break;
            }else if(confirm.equals("N")) {
                isProgammer = "N";
                break;
            }


        }
        questdata = getWordsFromTxt(level, isProgammer);
        Map.Entry<String, String> entry = getWord();
        if (entry == null) {
            System.out.println("Tidak ada soal yang tersedia.");
            return;
        }

        String question = entry.getKey();
        String answer = entry.getValue().toLowerCase();

        String generatedQuestion = generateQuestion(answer, level);

        System.out.println("--------------->> Score: " + score);
        System.out.println("Soal: " + question);
        System.out.println("Hint : \n" + generatedQuestion);
        System.out.print(" ---> ");
        String userAnswer = scanner.nextLine();

        isCorrect(answer, userAnswer);
    }

    public Map.Entry<String, String> getWord() {
        Random random = new Random();
        List<Map.Entry<String, String>> entryList = new ArrayList<>(questdata.entrySet());

        if (entryList.isEmpty()) {
            return null;
        }

        Map.Entry<String, String> entry;
        do {
            entry = entryList.get(random.nextInt(entryList.size()));
        } while (questPrev.contains(entry.getKey()));

        questPrev.add(entry.getKey());
        return entry;
    }

    private void isCorrect(String word, String answer) {
        if (answer.isEmpty()) {
            System.out.println("Jawaban: " + word);
            return;
        }
        if (answer.equals(word)) {
            score += 10;
            System.out.println("Benar");
        } else {
            score -= 5;
            System.out.println("Salah, Jawaban: " + word);
        }
    }

    private String generateQuestion(String word, Level level) {
        char[] c = word.toCharArray();
        Random random = new Random();

        int charLevel = level == Level.EASY ? 7 : level == Level.NORMAL ? 9 : 12;
        for (int i = 0; i < charLevel; i++) {
            int missingWord = random.nextInt(c.length);
            c[missingWord] = '_';
        }

        return String.valueOf(c);
    }

    public Level selectLevel() {
        int sa = score;
        int choice;

        if(sa < 30){
            choice =  1;
        }else if(sa < 70){
            choice = 2;
        }else{
            choice = 3;
        }
        try {
            return switch (choice) {
                case 1 -> Level.EASY;
                case 2 -> Level.NORMAL;
                case 3 -> Level.HARD;
                default -> null;
            };
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Silakan masukkan angka 1-3.");
            return null;
        }
    }


    public void typing(String word) {
        for (int i = 0; i < word.length(); i++) {
            System.out.print(word.charAt(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public int getScore(){
        return score;
    }

}
