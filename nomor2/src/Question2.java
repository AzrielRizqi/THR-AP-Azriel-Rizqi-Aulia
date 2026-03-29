import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

class Stand {
    protected Boolean expose(String name) {
        return false;
    }

    protected void arrest(String name) {

    }
}

class JosukeHigashikata extends Stand {
    @Override
    protected Boolean expose(String name) {
        final String tempName;
        tempName = name.toLowerCase();
        if (tempName.startsWith("k") || tempName.startsWith("kira")) {
            return true;
        }
        return false;
    }
}

class JotaroKujo extends Stand {
    @Override
    protected Boolean expose(String name) {
        final String tempName;
        tempName = name.toLowerCase();
        if (tempName.length() <= 3 || tempName.startsWith("kira")) {
            return true;
        }
        return false;
    }
}

class OkuyasuNijimura extends Stand {
    protected Boolean expose(String name) {
        final String tempName;
        tempName = name.toLowerCase();
        for (int i = 0; i < name.length() - 1; i++) {
            if (tempName.charAt(i) == tempName.charAt(i + 1) || tempName.startsWith("kira")) {
                return true;
            }
        }
        return false;
    }
}

class KoichiHirose extends Stand {
    protected Boolean expose(String name) {
        final String tempName;
        int vowelCount;
        tempName = name.toLowerCase();
        vowelCount = 0;
        for (int i = 0; i < name.length(); i++) {
            if (tempName.charAt(i) == 'a' || tempName.charAt(i) == 'i' || tempName.charAt(i) == 'u'
                    || tempName.charAt(i) == 'e' || tempName.charAt(i) == 'o' || tempName.startsWith("kira")) {
                vowelCount++;
                if (vowelCount >= 3) {
                    return true;
                }
            }
        }
        return false;
    }
}

class KishibeRohan extends Stand {
    protected Boolean expose(String name) {
        String tempName = name.toLowerCase();
        for (int i = 0; i < tempName.length(); i++) {
            if (tempName.charAt(i) != tempName.charAt(tempName.length() - 1 - i) || tempName.startsWith("kira")) {
                return false;
            }
        }
        return true;
    }
}

class detective extends Stand {
    Stack<String> prisonCell = new Stack<>();

    @Override
    protected void arrest(String name) {
        prisonCell.push(name);
    }
}

public class Question2 {// jujur saya tadi sakit hati karena ini soal kehapus pas mau push ke git
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {

            // System.out.println("Mori Mori Mori Mori Morioh Cho Radio... We love Morioh
            // Cho~");
            // System.out.println("There is a Mystery here, to solve it you need a partner
            // to solve it");
            // System.out.println("Select Partner:");
            // System.out.println("1. Josuke Higashikata");
            // System.out.println("2. Jotaro Kujo");
            // System.out.println("3. Okuyasu Nijimura");
            // System.out.println("4. Koichi Hirose");
            // System.out.println("5. Kishibe Rohan");
            // System.out.print("Enter your partner: ");
            String partnerChoice = sc.nextLine();

            Stand partnerStand = null;

            switch (partnerChoice) {
                case "Josuke":
                    partnerStand = new JosukeHigashikata();
                    break;
                case "Jotaro":
                    partnerStand = new JotaroKujo();
                    break;
                case "Okuyasu":
                    partnerStand = new OkuyasuNijimura();
                    break;
                case "Koichi":
                    partnerStand = new KoichiHirose();
                    break;
                case "Rohan":
                    partnerStand = new KishibeRohan();
                    break;
                default:
                    System.out.println("Invalid partner choice.");
                    return;
            }

            detective you = new detective();
            // System.out.print("Enter the names of the suspects: ");
            String[] names = sc.nextLine().split(" ");
            Queue<String> suspect = new LinkedList<>();
            for (String name : names) {
                suspect.add(name);
            }

            while (!suspect.isEmpty()) {
                String criminal = suspect.poll();
                if (criminal.equalsIgnoreCase(partnerChoice)) {
                    throw new Exception("Cannot arrest your own partner, Yare Yare Daze!");
                }
                if (partnerStand.expose(criminal)) {
                    you.arrest(criminal);
                }
            }

            if (!you.prisonCell.isEmpty()) {
                System.out.println(partnerChoice + " exposed someone!");
            } else {
                System.out.println(partnerChoice + " exposed no one...");
            }

            if (you.prisonCell.isEmpty()) {
                System.out.println("Arrested: []");
            } else {
                System.out.println("Arrested: " + you.prisonCell);
            }
        }
    }
}