
import com.mycompany.service.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class SinhVienTester {
    private static Connection conn;
    
    @BeforeAll
    public static void setUpClass() {
        try {
            conn = JdbcUtils.getConnect();
        } catch (SQLException ex) {
            Logger.getLogger(SinhVienTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void tearDownClass() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienTester.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
