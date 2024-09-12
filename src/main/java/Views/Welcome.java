package Views;

import Dao.UserDAO;
import Model.User;
import Service.SendOTPService;
import Service.generateOTP;
import Service.userService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {
    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nPress 1 to Login\nPress 2 to Signup\nPress 0 to Exit");
        int choice = 0;
        try{
            choice = Integer.parseInt(br.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }
        switch (choice){
            case 1 -> login();
            case 2 -> signup();
            case 0 -> System.exit(0);
        }
    }

    private void signup() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter Name : ");
        String name = sc.nextLine();

        System.out.print("\nEnter Email: ");
        String email = sc.nextLine();

        try {
            if(UserDAO.isExists(email)){
                System.out.println("\nEmail Already Exists");
            }else{
                String genOTP = generateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);

                System.out.println("\nAn OTP has been sent to "+email+"\nEnter OTP : ");
                String otp = sc.nextLine();

                if(otp.equals(genOTP)){
                    int response = userService.saveUser(new User(email,name));
                    switch (response){
                        case 0 -> System.out.println("\nUser already Exists!");
                        case 1 -> System.out.println("\nUser Successfully Registered!");
                    }
                }else{
                    System.out.println("\nInvalid OTP");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter Email : ");
        String email = scan.nextLine();
        try{
            if(UserDAO.isExists(email)){
                String genOTP = generateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("\nEnter OTP");
                String otp = scan.nextLine();
                if(genOTP.equals(otp)){
                    new UserView(email, UserDAO.getName(email)).home();
                }else{
                    System.out.println("\nInvalid OTP");
                }
            }else{
                System.out.println("\nNo User Found with Email : "+email);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
