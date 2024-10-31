package Sys;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public abstract class WordProviderImpl {
    private final String EASY_FILE_PATH = "/home/enigma/Documents/Data/Projek/Tebak Huruf di Kalimat/words/EasyQuest.csv";
    private final String MEDIUM_FILE_PATH = "/home/enigma/Documents/Data/Projek/Tebak Huruf di Kalimat/words/MediumQuest.csv";
    private final String HARD_FILE_PATH = "/home/enigma/Documents/Data/Projek/Tebak Huruf di Kalimat/words/HardQuest.csv";
    private final String EASY_PROGRAMMER = "/home/enigma/Documents/Data/Projek/Tebak Huruf di Kalimat/words/EasyProgrammer.csv";
    private final String MEDIUM_PROGRAMMER = "/home/enigma/Documents/Data/Projek/Tebak Huruf di Kalimat/words/MediumProgrammer.csv";
    private final String HARD_PROGRAMMER = "/home/enigma/Documents/Data/Projek/Tebak Huruf di Kalimat/words/HardProgrammer.csv";



    public Map<String, String> getWordsFromTxt(Level level, String type) {

        String filePathSelected;
        if(type.equals("Y")){
            filePathSelected = getFileProgrammer(level);
        }else {
            filePathSelected = getFileGeneral(level);
        }

        Map<String,String> quest = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathSelected))) {
            while(reader.ready()){
                String line = reader.readLine();
                if(line.startsWith("soal")) continue;
                String[] questData = line.split(",");
                quest.put(questData[0], questData[1]);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + filePathSelected);
            e.printStackTrace();
        }
        return quest;
    }

    private String getFileProgrammer(Level level){
        return switch (level) {
            case EASY -> EASY_PROGRAMMER;
            case NORMAL -> MEDIUM_PROGRAMMER;
            case HARD -> HARD_PROGRAMMER;
            default -> null;
        };
    }

    private String getFileGeneral(Level level){
        return switch (level) {
            case EASY -> EASY_FILE_PATH;
            case NORMAL -> MEDIUM_FILE_PATH;
            case HARD -> HARD_FILE_PATH;
            default -> null;
        };
    }


}
