import Views.Welcome;

public class Main {
    public static void main(String[] args) {
        Welcome welcome = new Welcome();
        System.out.println("Welcome To FileHider\n");
        do {
            welcome.welcomeScreen();
        }while(true);
    }
}
