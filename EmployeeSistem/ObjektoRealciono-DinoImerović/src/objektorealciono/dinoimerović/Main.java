package objektorealciono.dinoimerović;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        NewJFrame frame = new NewJFrame();
        frame.setLocation(700, 350);
        EmployeList emp = new EmployeList();
        UpdateFrame update = new UpdateFrame();
        List<Employe> list = new ArrayList();
        frame.show();
        
        
        frame.addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                Employe employe=new Employe();
                employe.setName(frame.nameTxt.getText());
                employe.setAge(Integer.valueOf(frame.ageVal.getValue().toString()));
                employe.setIncome(Double.valueOf(frame.incomeVal.getValue().toString()));
                employe.setAdress(frame.adressTxt.getText());
                
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/zaposleni?useTimezone=true&serverTimezone=UTC", "root", "123");) {
                    
                    PreparedStatement st = conn.prepareStatement("insert into employee(name,age,adress,income) values (?,?,?,?)");
                   
                    st.setString(1, employe.getName());
                    st.setString(2, String.valueOf(employe.getAge()));
                    st.setString(3, employe.getAdress());
                    st.setString(4, String.valueOf(employe.getIncome()));
                    st.execute();
                    
                   
                   } catch (Exception exc) {
                    System.out.println(exc);
                }   
            }
        });
        
        frame.listBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               listAll.listEmploye();
            }
        });
                    
        
                            
                           


    }
}
