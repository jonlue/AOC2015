package main.days;

import main.AOCRiddle;

public class Day11 extends AOCRiddle {
    public Day11(String in, int part) {
        super(in, part);
    }

    public String solve1() {
        return findNextSecurePassword(getInput());
    }

    public String solve2() {
        return findNextSecurePassword(findNextSecurePassword(getInput()));
    }


    private String findNextSecurePassword(String password){
        do {
            password = getNextPassword(password);
        } while (!isSecure(password));

        return password;
    }

    private String getNextPassword(String password) {
        do {
            password = changeChar(password, password.length() - 1);
        }
        while (!checkIllegalCharacter(password));
        return password;
    }

    private String changeChar(String password, int index) {
        if (password.charAt(index) == 'z') {
            password = password.substring(0, index) + 'a' + password.substring(index + 1);
            if (index != 0) {
                return changeChar(password, index - 1);
            } else {
                password = 'a' + password.substring(1);
            }
        }
        return password.substring(0, index) + ((char) (password.charAt(index) + 1)) + password.substring(index + 1);
    }

    private boolean isSecure(String password) {
        return checkSequence(password) && checkPair(password) && checkIllegalCharacter(password);
    }

    private boolean checkIllegalCharacter(String password) {
        return !(password.contains("i") || password.contains("o") || password.contains("l"));
    }

    private boolean checkPair(String password) {
        char pair1 = 0;
        for (int i = 0; i < password.length() - 1; i++) {
            char c = password.charAt(i);
            if (c == password.charAt(i + 1)) {
                if (pair1 == 0) {
                    pair1 = c;
                }
                if (pair1 != 0 && c != pair1) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSequence(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            char c = password.charAt(i);
            if (c + 1 == password.charAt(i + 1) && c + 2 == password.charAt(i + 2)) {
                return true;

            }
        }
        return false;
    }
}
