
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //t cases
        int t= Integer.parseInt(in.readLine());

        for(int i = 1 ; i<=t ; i++){

            //numero actividades
            int n = Integer.parseInt(in.readLine());

            Activity[] activities= new Activity[n];

            //tomar datos de actividades
            for(int j=0; j<n;j++){
                String[] data = in.readLine().split(" ");
                Activity act = new Activity(j,Integer.parseInt(data[0]),Integer.parseInt(data[1]));
                activities[j]=act;
            }

            //ordenar
            Arrays.sort(activities);


            String[] parents = new String[]{"C","J"};
            int currentParent=0;

            int[] finishTime = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};

            String[] ans = new String[n];

            ans[activities[0].index]="C";
            finishTime[0]=activities[0].endDate;

            boolean isPosible=true;

            for(int j=1; j<n; j++){
                int finish = activities[j-1].endDate;

                int start = activities[j].startDate;

                if(start>=finish){
                    ans[activities[j].index]=parents[currentParent];
                    finishTime[currentParent]=activities[j].endDate;
                }else{
                    currentParent=(currentParent==0)?1:0;
                    if(start>=finishTime[currentParent]){
                        ans[activities[j].index]=parents[currentParent];
                        finishTime[currentParent]=activities[j].endDate;
                    }else{
                        isPosible=false;
                        break;
                    }

                }

            }

            String answerString = "";
            if(isPosible){

                for(String whoIs: ans){
                    answerString+=whoIs;
                }
            }else{
                answerString="IMPOSSIBLE";
            }

            System.out.println("Case #"+i+": "+answerString);
        }




    }

}

class Activity implements Comparable<Activity>{
    int index;
    int startDate;
    int endDate;

    public Activity(int index, int startDate, int endDate) {
        this.index = index;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    @Override
    public int compareTo(Activity o) {
        if(this.startDate==o.startDate)
            return this.endDate-o.endDate;

        return this.startDate-o.startDate;
    }
}
