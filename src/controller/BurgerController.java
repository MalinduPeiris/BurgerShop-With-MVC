package controller;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Burger;

public class BurgerController {
    
    public static String generateOrderId() throws FileNotFoundException, IOException{
        BufferedReader br=new BufferedReader(new FileReader("BurgerShop.txt"));
        String line=br.readLine();
        String lastLine=null;
        while(line!=null){
            lastLine=line;
            line=br.readLine();
        }
        if(lastLine==null){
            return "B0001";
        }
        
        String[] word=lastLine.split(",");
        String lastId=word[0].trim();
        int id=Integer.parseInt(lastId.substring(1));
        return String.format("B%04d",id+1);
    }
    
    public static boolean addOrder(Burger burger) throws IOException{
        FileWriter fw=new FileWriter("BurgerShop.txt",true);
        fw.write(burger.getOrderId()+" , "+burger.getCustomerId()+" , "+burger.getCustName()+" , "+burger.getQty()+" , "+burger.getTotal()+" , "+burger.getStatus()+"\n");
        fw.close();
        return true;
    }
    
    public static Burger[] searchBestCustomers() throws FileNotFoundException, IOException{
        BufferedReader br=new BufferedReader(new FileReader("BurgerShop.txt"));
        String line=br.readLine();
        
        Burger[] burgerArray=new Burger[0];
                
        while(line!=null){
            String[] lineArray=line.split(",");
            
            Burger burger=new Burger(lineArray[0].trim(),lineArray[1].trim(),lineArray[2].trim(),Integer.parseInt(lineArray[3].trim()),Double.parseDouble(lineArray[4].trim()),Integer.parseInt(lineArray[5].trim()));
            burgerArray=extendsBurgerArrayBestCustomer(burgerArray,burger);
            line=br.readLine();
        }
        return burgerArray;
    }
    
    public static Burger[] serachCustomerDetails(String customerId) throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("BurgerShop.txt"));
        String line=br.readLine();
        
        Burger[] burgerArray=new Burger[0];
                
        while(line!=null){
            String[] lineArray=line.split(",");
            
            Burger burger=new Burger(lineArray[0].trim(),lineArray[1].trim(),lineArray[2].trim(),Integer.parseInt(lineArray[3].trim()),Double.parseDouble(lineArray[4].trim()),Integer.parseInt(lineArray[5].trim()));
            burgerArray=extendsArray(burgerArray,burger,customerId);
            line=br.readLine();
        }
        return burgerArray;
    
    }
    
    
    
    public static Burger[] extendsArray(Burger[] burgerArray,Burger burger,String custName){
        boolean have=false;
        if(burger.getCustomerId().equalsIgnoreCase(custName)){
            have=true;
        }
        if(have){
            Burger[] temp=new Burger[burgerArray.length+1];
            for (int i = 0; i < burgerArray.length; i++) {
                temp[i]=burgerArray[i];
            }
            temp[temp.length-1]=burger;
            return temp;
        }else{
            return burgerArray;
        }
    }
    
    public static Burger[] extendsBurgerArrayBestCustomer(Burger[] burgerArray,Burger burger){
        boolean isDuplicate=false;
        for (int i = 0; i < burgerArray.length; i++) {
            if(burger.getCustomerId().equalsIgnoreCase(burgerArray[i].getCustomerId())){
                isDuplicate=true;
                burgerArray[i].setTotal(burgerArray[i].getTotal()+burger.getTotal());
            }
        }
        
        if(!isDuplicate){
            Burger[] temp=new Burger[burgerArray.length+1];
            for (int i = 0; i < burgerArray.length; i++) {
                temp[i]=burgerArray[i];
            }
            temp[temp.length-1]=burger;
            return temp;
        }else{
            return burgerArray;
        }
    }
    
    public static Burger containsOrderId(String orderId) throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("BurgerShop.txt"));
        String line=br.readLine();
              
        while(line!=null){
            String[] lineArray=line.split(",");
            
            Burger burger=new Burger(lineArray[0].trim(),lineArray[1].trim(),lineArray[2].trim(),Integer.parseInt(lineArray[3].trim()),Double.parseDouble(lineArray[4].trim()),Integer.parseInt(lineArray[5].trim()));
            if(burger.getOrderId().equalsIgnoreCase(orderId)){
                return burger;
            }
            line=br.readLine();
        }
        return null;
        
    }
    
    public static boolean updateOrderDetails(Burger burgerData) throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("BurgerShop.txt"));
        String line=br.readLine();
        
        Burger[] burgerArray=new Burger[0];
        while(line!=null){
            String[] lineArray=line.split(",");
            Burger burger=new Burger(lineArray[0].trim(),lineArray[1].trim(),lineArray[2].trim(),Integer.parseInt(lineArray[3].trim()),Double.parseDouble(lineArray[4].trim()),Integer.parseInt(lineArray[5].trim()));
            
            if(!(burger.getOrderId().equalsIgnoreCase(burgerData.getOrderId()))){
                burgerArray=extendsFullArray(burgerArray);
                burgerArray[burgerArray.length-1]=burger;
            }else{
                burgerArray=extendsFullArray(burgerArray);
                burgerArray[burgerArray.length-1]=burgerData;
            }
            line=br.readLine();
        }
    
        FileWriter fw=new FileWriter("BurgerShop.txt",false);
        fw.write("");
        
        FileWriter newFw=new FileWriter("BurgerShop.txt",true);
        for (int i = 0; i < burgerArray.length; i++) {
            newFw.write(burgerArray[i].getOrderId()+" , "+burgerArray[i].getCustomerId()+" , "+burgerArray[i].getCustName()+" , "+burgerArray[i].getQty()+" , "+burgerArray[i].getTotal()+" , "+burgerArray[i].getStatus()+"\n");
        }
        newFw.close();
        
        return true;
    }
    
    public static Burger[] extendsFullArray(Burger[] burgerArray){
        Burger[] temp=new Burger[burgerArray.length+1];
        for (int i = 0; i < burgerArray.length; i++) {
            temp[i]=burgerArray[i];
        }
        return temp;
    }
    
    public static Burger[] getAllBurgers() throws IOException{
        BufferedReader br=new BufferedReader(new FileReader("BurgerShop.txt"));
        String line=br.readLine();
        
        Burger[] burgerArray=new Burger[0];
        while(line!=null){
            String[] lineArray=line.split(",");
            Burger burger=new Burger(lineArray[0].trim(),lineArray[1].trim(),lineArray[2].trim(),Integer.parseInt(lineArray[3].trim()),Double.parseDouble(lineArray[4].trim()),Integer.parseInt(lineArray[5].trim()));
            
            burgerArray=extendsFullArray(burgerArray);
            burgerArray[burgerArray.length-1]=burger;
           
            line=br.readLine();
        }
        
        return burgerArray;
    }
    
}
