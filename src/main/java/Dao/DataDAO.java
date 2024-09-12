package Dao;

import Model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public static List<Data> getAllFiles(String email) throws ClassNotFoundException, SQLException {
        Connection conn = Database.Connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from data where email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        List<Data> files = new ArrayList<Data>();
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String path = rs.getString(3);
            files.add(new Data(id,name,path));
        }
        return files;
    }

    public static int hideFiles(Data file) throws ClassNotFoundException, SQLException, IOException {
        Connection conn = Database.Connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into data (name,path,email,bin_data) values(?,?,?,?)");
        ps.setString(1, file.getFineName());
        ps.setString(2, file.getPath());
        ps.setString(3, file.getEmail());

        File f = new File(file.getPath());
        FileReader fr = new FileReader(f);
        ps.setCharacterStream(4,fr,f.length());

        int ans = ps.executeUpdate();
        fr.close();
        f.delete();
        return ans;
    }

    public static int unhide(int id) throws IOException, SQLException {
        Connection conn = Database.Connection.getConnection();
        PreparedStatement ps = conn.prepareStatement("select path,bin_data from data where id=?");
        ps.setInt(1, id);
        ResultSet re = ps.executeQuery();
        re.next();
        String path = re.getString("path");
        Clob clob = re.getClob("bin_data");

        Reader reader = clob.getCharacterStream();
        FileWriter fw = new FileWriter(path);

        int i;
        while((i=reader.read())!=-1){
            fw.write((char)i);
        }
        fw.close();
        reader.close();

        ps = conn.prepareStatement("delete from data where id = ?");
        ps.setInt(1, id);
        int response = ps.executeUpdate();
        System.out.println("File Unhidden at : " + path);
        return response;
    }
}
