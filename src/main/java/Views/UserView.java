package Views;

import Dao.DataDAO;
import Model.Data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    private String name;

    public UserView(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public void home(){
        do{
            System.out.println("Logged In! Welcome Back, "+this.name+"!");
            System.out.println("Press 1 to show Hidden Files");
            System.out.println("Press 2 to Hide a new File");
            System.out.println("Press 3 to Unhide a File");
            System.out.println("Press 0 to Exit");
            Scanner sc = new Scanner(System.in);
            String ch = sc.nextLine();
            if(ch.isEmpty()){
                ch="-1";
            }
            int choice = Integer.parseInt(ch);
            switch (choice){
                case 1:
                    try {
                        List<Data> files = DataDAO.getAllFiles(email);
                        if(files.isEmpty()){
                            System.out.println("No files found");
                        }else{
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("FileID          Name        Path");
                            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                            for(Data file : files){
                                System.out.println(file.getId()+"           "+file.getFineName()+"          "+file.getPath());
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.print("\nEnter the File Path : ");
                    String path = sc.nextLine();
                    try{
                        File file = new File(path);
                        int response = DataDAO.hideFiles(new Data(file.getName(),path,email));
                        if(response == 1){
                            System.out.print("\nFile Hided Successfully");
                        }else{
                            System.out.print("\nError Hiding File");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    List<Data> files = null;
                    try {
                        files = DataDAO.getAllFiles(email);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("FileID          Name        Path");
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
                    for(Data file : files){
                        System.out.println(file.getId()+"           "+file.getFineName()+"          "+file.getPath());
                    }
                    System.out.println("\nEnter File ID to Unhide : ");
                    int id = Integer.parseInt(sc.nextLine());
                    boolean isValid = false;
                    for(Data file : files){
                        if(file.getId() == id){
                            isValid = true;
                            break;
                        }
                    }
                    if(isValid){
                        try {
                            DataDAO.unhide(id);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        System.out.println("\nInvalid File ID");
                    }
                    break;

                case 0:
                    System.exit(0);

                case -1:
            }
        }while(true);
    }
}
