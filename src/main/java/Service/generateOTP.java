package Service;

import java.util.Random;

public class generateOTP {
    public static String getOTP(){
        Random rand = new Random();
        return String.format("%04d",rand.nextInt(10000));
    }

}
