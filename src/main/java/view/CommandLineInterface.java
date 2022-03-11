package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class CommandLineInterface {

    public static void waitForUserToContinue(String message) {
        letUserChooseMenuItem(message, new String[] {"Continue"});
    }


    public static int letUserChooseMenuItem(String[] menuItemTexts) {
        return letUserChooseMenuItem("", menuItemTexts);
    }
    public static int letUserChooseMenuItem(String textBeforeItems, String[] menuItemTexts) {
        return letUserChooseMenuItem(textBeforeItems, menuItemTexts, "");
    }

    public static int letUserChooseMenuItem(String textBeforeItems, String[] menuItemTexts, String textAfterItems) {
        if (menuItemTexts.length == 0) {
            throw new IllegalArgumentException("there must be at least 1 menu item");
        }

        System.out.print(textBeforeItems);
        boolean validInputEntered = false;

        int userInputInt = -1;
        while (!validInputEntered) {
            validInputEntered = true;

            for (int i = 0; i < menuItemTexts.length; i++) {
                System.out.printf(" - " + menuItemTexts[i] + " (%d)\n", i);
            }
            System.out.print(textAfterItems);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String userInputString = br.readLine();

                userInputInt = Integer.parseInt(userInputString);
            } catch (Exception e) {
                validInputEntered = false;
            }
            if (userInputInt < 0 || userInputInt >= menuItemTexts.length) {
                validInputEntered = false;
            }

            if (!validInputEntered) {
                System.out.println("Sorry, your response wasn't valid apparently, please try again");
            }

        }
        return userInputInt;
    }



    public static void clearScreen() {
        // Print empty lines for output where char sequence does not work
        for (int i = 0; i < 50; ++i) System.out.println();
        // clear screne via char sequence
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static String askAndGetString(String message) {
        System.out.print(message);
        String userInputString = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            userInputString = br.readLine();
        } catch(Exception e) {

        }

        return userInputString;
    }

    public static int askAndGetNonNegativeInt(String message) {
        int userInputInt = -1;
        while (userInputInt < 0) {
            userInputInt = askAndGetInt(message);

            if (userInputInt < 0) {
                System.out.println("Sorry, you should enter a positive number, please try again");
            }
        }
        return userInputInt;
    }

    public static int askAndGetInt(String message) {
        int userInputInt = -1;
        boolean validInputEntered = false;
        while (!validInputEntered) {
            validInputEntered = true;

            System.out.print(message);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String userInputString = br.readLine();

                userInputInt = Integer.parseInt(userInputString);
            } catch (Exception e) {
                validInputEntered = false;
            }

            if (!validInputEntered) {
                System.out.println("Sorry, you should enter a number, please try again");
            }
        }
        return userInputInt;
    }

    public static String askAndGetImsi(String message) {
        String userInput = null;
        boolean validInputEntered = false;
        while (!validInputEntered) {
            validInputEntered = true;

            System.out.print(message);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                userInput = br.readLine();

                int stringAsIntHelper = Integer.parseInt(userInput);
                if (userInput.length() != 10 || stringAsIntHelper < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                validInputEntered = false;
            }

            if (!validInputEntered) {
                System.out.println("Sorry, you should enter a 10-digit number, please try again");
            }
        }
        return userInput;

    }


    public static BigDecimal askAndGetPositiveCurrencyValue(String message) {
        BigDecimal userInputInt = null;
        boolean validInputEntered = false;
        while (!validInputEntered) {
            validInputEntered = true;

            System.out.print(message);
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String userInputString = br.readLine();

                userInputInt = new BigDecimal(userInputString);

                if (userInputString.split(".").length > 1 && userInputString.split(".")[1].length() > 2) {
                    throw new IllegalArgumentException("Only a maximum of 2 decimal placed is allowed");
                }

                if (  userInputInt.compareTo(  new BigDecimal(0)  )  < 0  ) {
                    throw new IllegalArgumentException("Please enter a positive number or 0 !");
                }
            } catch (Exception e) {
                validInputEntered = false;
            }

            if (!validInputEntered) {
                System.out.println("Sorry, you should enter a number, please try again");
            }
        }
        return userInputInt;
    }



}
