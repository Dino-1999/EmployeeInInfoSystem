
package objektorealciono.dinoimerović;

import com.sun.java.swing.plaf.motif.MotifButtonListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class listAll {
    public static void listEmploye(){
        
    NewJFrame frame=new NewJFrame();
    
    EmployeList empList=new EmployeList();
    frame.setLocation(700, 350);
    empList.setLocation(700, 350);
    Employe employe=null;
    UpdateFrame updateF=new UpdateFrame();
    updateF.setLocation(700, 350);
    List <Employe> list=new ArrayList();
                    DefaultListModel mod=new DefaultListModel();
                    empList.employeList.setModel(mod);
                empList.show(true);
                
              
                    try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/zaposleni?useTimezone=true&serverTimezone=UTC", "root", "123");){
                    Statement st=conn.createStatement();
                    st.execute("select*from employee");
                    ResultSet rs=st.getResultSet();
                    while(rs.next()){ 
                        employe = new Employe();
                        employe.setId(rs.getInt("ID"));
                        employe.setName(rs.getString("name"));
                        employe.setAge(rs.getInt("age"));
                        employe.setAdress(rs.getString("adress"));
                        employe.setIncome(rs.getDouble("income"));
                        list.add(employe);
                                            
                    }
                    rs.next();
                    for(int i=0;i<=list.size();i++){
                        mod.addElement(list.get(i));
                    }
                   
                } catch (Exception exc) {
                    
                }
                
            empList.closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                empList.show(false);
                mod.removeAllElements();
                
            }
            });
            empList.removeBtnAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/zaposleni?useTimezone=true&serverTimezone=UTC", "root", "123");){
                    Statement st=conn.createStatement();
                    st.execute("delete from employee");
                    st.execute("truncate table employee");
                    JOptionPane.showMessageDialog(null, "All removed sucessufully");
                    mod.removeAllElements();
                    list.removeAll(list);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            });
            empList.removeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt){
                try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/zaposleni?useTimezone=true&serverTimezone=UTC","root","123");){
                    
                    Employe employe=new Employe();
                    Statement st=conn.createStatement();
                    ResultSet rs=st.getResultSet();
                    
                   
                    
                    
                    
                    st.executeUpdate("delete from employee where ID="+list.get(empList.employeList.getSelectedIndex()).getId());
                       
                    
                    
                    mod.removeElementAt(empList.employeList.getSelectedIndex());
                    if(mod.isEmpty()){
                        Statement stm=conn.createStatement();
                        stm.execute("truncate table employee");
                    }
                    
                } catch (Exception e) {
                }
            }
            });
            empList.updateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                int index=empList.employeList.getSelectedIndex();
                
                try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/zaposleni?useTimezone=true&serverTimezone=UTC", "root", "123");){
                updateF.show();
                
                updateF.idLbl.setText(String.valueOf(list.get(index).getId()));
                updateF.nameLbl.setText(String.valueOf(list.get(index).getName()));
                updateF.ageLbl.setText(String.valueOf(list.get(index).getAge()));
                updateF.adressLbl.setText(list.get(index).getAdress());
                updateF.incomeLbl.setText(String.valueOf(list.get(index).getIncome()));
                
                } catch (Exception exc) {
                    System.out.println(exc);
                }
                updateF.okBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    try (Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/zaposleni?useTimezone=true&serverTimezone=UTC", "root", "123");){
                        PreparedStatement st=conn.prepareStatement("update employee set name=?,age=?,adress=?,income=? where ID=?");
                        ResultSet rs=st.getResultSet();
                        
                        
                        String name=updateF.tf1.getText();
                        int index=empList.employeList.getSelectedIndex();
                        int age=Integer.valueOf(updateF.tf2.getText());
                        String adress=updateF.tf3.getText();
                        double income=Double.valueOf(updateF.tf4.getText());
                        
                        
                        st.setString(1, name);
                        st.setInt(2, age);
                        st.setString(3, adress);
                        st.setDouble(4,income);
                        st.setInt(5,empList.employeList.getSelectedIndex());
                        st.executeUpdate();
                        updateF.show(false);
                        list.get(index).setName(name);
                        list.get(index).setAge(age);
                        list.get(index).setIncome(income);
                        int id=Integer.valueOf(list.get(index).getId());
                        list.get(index).setAdress(adress);
                        list.get(index).setName(name);
                        list.get(index).setAdress(adress);
                        list.get(index).setIncome(income);
                        mod.remove(index);
                        mod.add(index, list.get(index));
                        JOptionPane.showMessageDialog(null, "Update employee at index="+index +"\n" + "Please reopen the list");
                        
                        } catch (Exception exc) {
                        System.out.println(exc);
                    }
                }
                
                });
            }
            });
    }

}