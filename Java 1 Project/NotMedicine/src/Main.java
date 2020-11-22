public class Main {
    public static void main(String [] args) {

       boolean isGUI = true;

        if(isGUI) {
            GUI gui = new GUI();
        }
        else{
            Terminal.mainMenu();
        }
    }
}