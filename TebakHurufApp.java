import Sys.GameSys;

import java.util.Scanner;

public class TebakHurufApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameSys game = new GameSys();
        game.typing("\n------- Selamat Datang Quiz Game --------\n");
        game.typing("By mudieb_UB <<--------\n");

        game.typing("---> Klick Enter Untuk Memulai <---");
        scanner.nextLine();

        String n;

        while (true) {
            game.start();
            if (game.getScore() > 100) {
                System.out.println("----------- > Selamat, Anda memenangkan game ini !!! < -----------");
                System.out.println("\n Anda Sangat Pintar \n Score Anda: " + game.getScore() + "\n");
                return;
            }

            System.out.println("--> Klick Y atau Enter Untuk Memulai Lagi.");
            System.out.println("--> Klick N atau Q Untuk Keluar. ");
            n = scanner.nextLine().toUpperCase();



            if(n.equals("Y") || n.isEmpty()) {
                continue;
            }else if(n.equals("N") || n.equals("Q") ){
                System.out.println("Terima Kasih");
                return;
            }

        }
    }
}
