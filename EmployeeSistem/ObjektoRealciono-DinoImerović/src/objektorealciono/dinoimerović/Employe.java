
package objektorealciono.dinoimerović;

import java.io.Serializable;


public class Employe implements Serializable {
      int id;
      String name;
      int age;
      String adress;
      double income;

      public Employe(){
          
      }
      
      public int getId(){
          return id;
      }
     
      public String getName(){
          return name;
      }
      
     
      public int getAge(){
          return age;
      }
      public String getAdress(){
          return adress;
      }
      public double getIncome(){
          return income;
      }
      
     
      public void setName(String name){
          this.name=name;
      }
       public void setAge(int age){
          this.age=age;
      } 
       public void setAdress(String adress){
           this.adress=adress;
       }
       public void setId(int id){
          this.id=id;
      }
       public void setIncome(double income){
           this.income=income;
       }
      
      @Override
      public String toString(){
          return id + " " +name + "; " + age +"; "+adress+"; "+income + "\n";
      }
}
