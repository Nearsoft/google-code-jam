import java.util.*;

class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        //Dame # casos
        int cases = sc.nextInt();
        sc.nextLine();
        
        for(int a=1; a <= cases; a++){

            //Dame datos impresora
            String imp1 = sc.nextLine();
            String[] newStr1 = imp1.split("\\s+");
            String imp2 = sc.nextLine();
            String[] newStr2 = imp2.split("\\s+");
            String imp3 = sc.nextLine();
            String[] newStr3 = imp3.split("\\s+");
            
            //Variables
            int suma = 0;
            int sumaF = 1000000;
            int numMen = 0;
            int minCyan = 0;
            int minMagen = 0;
            int minYell = 0;
            int minBla= 0;
            int testCyan = 0;
            int testMagen = 0;
            int testYell=0;
            int testBla=0;
            String result="";
            boolean pass = false;
            String r = "IMPOSSIBLE";
            ArrayList<Integer> datosFinal = new ArrayList<Integer>();    
            
            ArrayList<Integer> datosImp1 = new ArrayList<Integer>();
            ArrayList<Integer> datosImp2 = new ArrayList<Integer>();
            ArrayList<Integer> datosImp3 = new ArrayList<Integer>();
                
            //Array de string pasa a Arraylist de int
            for (int i = 0; i < 4; i++) {
                datosImp1.add(Integer.parseInt(newStr1[i]));
                datosImp2.add(Integer.parseInt(newStr2[i]));
                datosImp3.add(Integer.parseInt(newStr3[i]));
            }
            
            //Verificar datos impresora 
                //pass = true;
                //cyan color
                int c1 = datosImp1.get(0);
                int c2 = datosImp2.get(0);
                int c3 = datosImp3.get(0);
                
                //magenta color
                int m1 = datosImp1.get(1);
                int m2 = datosImp2.get(1);
                int m3 = datosImp3.get(1);
                
                //yellow color
                int y1 = datosImp1.get(2);
                int y2 = datosImp2.get(2);
                int y3 = datosImp3.get(2);
                
                //black color
                int b1 = datosImp1.get(3);
                int b2 = datosImp2.get(3);
                int b3 = datosImp3.get(3);
                
                
                minCyan = Math.min(c1, c2); 
                minCyan = Math.min(minCyan,c3);
                
                minMagen = Math.min(m1, m2); 
                minMagen = Math.min(minMagen,m3);
                
                minYell = Math.min(y1, y2); 
                minYell = Math.min(minYell,y3);
                
                minBla = Math.min(b1, b2); 
                minBla = Math.min(minBla,b3);
            
                if(c1+m1+y1+b1<sumaF || c2+m2+y2+b2<sumaF || c3+m3+y3+b3<sumaF ){
                    System.out.println("Case #"+a+": "+r);
                } else{
                    if(c1==0||c2==0||c3==0||m1==0||m2==0||m3==0||y1==0||y2==0||y3==0||b1==0||b2==0||b3==0){
                       System.out.println("Case #"+a+": "+r); 
                    }else{
                        if(minCyan+minMagen+minYell+minBla==sumaF){
                            result =String.valueOf(minCyan)+" "+String.valueOf(minMagen)+" "
                            +String.valueOf(minYell)+" "+String.valueOf(minBla);
                            System.out.println("Case #"+a+": "+result);
                        } else{
                            while(pass == false){
                                testCyan =(int) (Math.random()*minCyan+1);
                                testMagen =(int) (Math.random()*minMagen+1);
                                testYell =(int) (Math.random()*minYell+1);
                                testBla =(int) (Math.random()*minBla+1);
                                if(testCyan+testMagen+testYell+testBla==sumaF){
                                    result =String.valueOf(testCyan)+" "+String.valueOf(testMagen)+" "
                                    +String.valueOf(testYell)+" "+String.valueOf(testBla);
                                    System.out.println("Case #"+a+": "+result);
                                    pass=true;
                                }else{
                                    pass=false;
                                }
                            }
                        }
                    }
                }
        }
    }
    
}