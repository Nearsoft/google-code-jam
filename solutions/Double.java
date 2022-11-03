import java.util.*;

class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        //# cases
        int cases = sc.nextInt();
        sc.nextLine();
        
        for(int a=1; a <= cases; a++){
            //give word
            String word = sc.nextLine();
            //split string on letters in String array
            String[] newStr = word.split("");
            
            //variables
            int numAdd= 0;
            int rep = 0;
            String result="";
            
            //array of strings --> list of string
            List<String> letters= new ArrayList<>(Arrays.asList(newStr));
            
            //start comparing
            for(int i = 0; i < word.length()+numAdd; i++){
                String t = letters.get(i);
                char charAt = t.charAt(0);
                n="";
                o="";
                
                if (i==letters.size()-1){
                        
                }else{
                    String m = letters.get(i+1);
                    char charNext = m.charAt(0);
                    int res = Character.compare(charNext, charAt); 
                    
                    if(res>0){
                        if(rep>0){
                            for(int j=0; j<=rep;j++){
                                letters.add(i,t);
                                i=i+1;
                                numAdd=numAdd+1;
                                
                            }
                            rep=0;
                        }else{
                            letters.add(i,t);
                            i=i+1;
                            numAdd=numAdd+1;
                        }
                    }
                    if(res==0){
                        rep=rep+1;
                    }
                    if(res<0){
                        rep=0;
                    }
                }  
            }
            for(String s : letters){
                result = result + s;
            }
            System.out.println("Case #"+a+": "+result);
        }
    }
    
}