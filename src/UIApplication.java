import com.studentscrud.factory.ConnectionFactory;
import com.studentscrud.frames.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UIApplication {
    public static void main(String[] args) {
        String professorsTableSql = """
                create table if not exists professors (
                    ra       bigint(20) not null primary key,
                    name     varchar(100),
                    age      varchar(3),
                    email    varchar(100),
                    workload varchar(2)) auto_increment = 200;""";

        String studentsTableSql = """
                create table if not exists students (
                        ra       bigint(20) not null primary key auto_increment,
                        name     varchar(100),
                        age      varchar(3),
                        course   varchar(100),
                        schedule varchar(5),
                        absences int(11)) auto_increment = 100;""";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); Statement st = conn.createStatement()) {
            st.executeUpdate(professorsTableSql);
            st.executeUpdate(studentsTableSql);
            new MainFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Banco de dados não encontrado.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
