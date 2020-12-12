import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class validation_algorithm1 {

    static char[][] grid;
    static int gridSize;
    static String registeredPswd;
    static int horizontal;
    static int vertical;
    static int horSteps;
    static int verSteps;

    public static char findCharAtPos(int x, int y) {

        int upd_x = (x + vertical*verSteps + gridSize) % gridSize;
        int upd_y = (y + horizontal*horSteps + gridSize) % gridSize;

        return grid[upd_x][upd_y];
    }


    /* Generate a list of all alphabets */
    /* Generate a list of unique characters in the registeredPswd*/
    /* Randomly # select new letters from the list - gridSize*gridSize - len(registeredPswd) */
    /* make a combined list, shuffle it*/

    public static ArrayList<Character> uniqueCharacters(String test){
        ArrayList<Character> vowels = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < test.length(); i++){
            char current = test.charAt(i);
            if (temp.indexOf(current) < 0){
                temp = temp + current;
                vowels.add(current);
            }
        }

        System.out.println(temp + " ");
        return vowels;

    }
    public static char[][] create_random_matrix(String registeredPswd, int gridSize){
        char[][] arr = new char[gridSize][gridSize];

        // Get all unique characters from the registered password
        ArrayList<Character> uniq_char = uniqueCharacters(registeredPswd);
        System.out.print("Unique characters in registered password = ");
        System.out.println(uniq_char);

        // Get all the rest of the unique characters in the alphabet
        ArrayList<Character> restOfUnique = new ArrayList<>();
        for(char c = 'A'; c <= 'Z'; ++c) {
            int i;
            boolean check = true;
            for (i = 0; i < uniq_char.size(); i++) {
                if (c == uniq_char.get(i)) {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                restOfUnique.add(c);
            }
        }
        // Shuffle the rest of the unique characters
        Collections.shuffle(restOfUnique);
        System.out.print("Rest of the unique characters in registered password = ");
        System.out.println(restOfUnique);

        int empty_slots_left = gridSize * gridSize - uniq_char.size();
        System.out.print("The grid must contain the unique characters of the registered password = ");
        System.out.println(empty_slots_left);

        for(int j = 0;j < empty_slots_left; j++){
            uniq_char.add(restOfUnique.get(j));
        }
        System.out.print("Letters in the grid = ");
        System.out.println(uniq_char);
        Collections.shuffle(uniq_char);

        for(int i=0; i < gridSize;i++) {
            for (int j = 0; j < gridSize; j++) {
                arr[i][j] = uniq_char.get(j * gridSize + i);
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        return arr;
    }

    public static boolean check(String enteredPswd){

        /**
         * @param enteredPswd		Password entered by the user
         * @param registeredPswd	Registered password of the user
         * @param grid				Grid generated by the system
         * @param horizontal		Registered Horizontal direction; -1 indicates left; 1 indicates right
         * @param vertical			Registered Vertical direction; -1 indicates up; 1 indicates down
         * @param horSteps			Number of registered steps in horizontal direction
         * @param verSteps			Number of registered steps in vertical direction
         * @return 					The entered password is correct or not
         */

        // ASSUMPTION: All grid characters are distinct

        if(enteredPswd.length()!=registeredPswd.length()) {
            return false;
        }

        gridSize = grid.length;

        for(int p=0;p<registeredPswd.length();p++) {

            char chReg = registeredPswd.charAt(p);

            for(int i=0;i<gridSize;i++) {

                boolean found = false;

                for(int j=0;j<gridSize;j++) {

                    if(grid[i][j]==chReg) {

                        char chAct = findCharAtPos(i,j);

                        if(chAct!=enteredPswd.charAt(p)) {
                            return false;
                        }

                        found = true;
                        break;
                    }

                }

                if(found) {
                    break;
                }
            }

        }

        return true;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        create_random_matrix("nirani", 5);
//        System.exit(0);

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter grid size: ");
        // Grid Size
        int gridSize = sc.nextInt();
        char[][] arr = new char[gridSize][gridSize];

        System.out.print("Enter Registered Password: ");
        // Registered password for the user
        registeredPswd = sc.next();
        arr = create_random_matrix(registeredPswd, gridSize);

//        System.out.println("Enter grid: ");
//        for(int x=0;x<n;x++) {
//
//            String inp = sc.next();
//
//            for(int y=0;y<n;y++) {
//                char gch = inp.charAt(y);
//                arr[x][y] = gch;
//            }
//        }
//        System.out.print("Enter Registered Password: ");
//        // Registered password for the user
//        registeredPswd = sc.next();

        System.out.print("Enter Password provided by user: ");
        // Password entered by user
        String enteredPswd = sc.next();

        grid = arr;
        horizontal = 1;
        vertical = 1;
        horSteps = 1;
        verSteps = 3;

        boolean result = check(enteredPswd);
        if(result) {
            System.out.println("Password entered is CORRECT");
        }
        else {
            System.out.println("Password is entered is WRONG");
        }

        sc.close();
    }

}

/*
INPUT
5
BVTAD
ORYMU
PQNFZ
XLGHE
IJWKC
RAMBUTAN
WECLIHEA
Expected Output: CORRECT
*/