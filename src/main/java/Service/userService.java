package Service;

import Dao.UserDAO;
import Model.User;

import java.sql.SQLException;

public class userService {
    public static Integer saveUser(User user){
        try{
            return UserDAO.saveUser(user);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
