import java.util.Arrays;
public class fcfs{
	public static void main(String[] args) {
		int[] processes ={1,2,3};
		int[] burst= {10,5,8};
		System.out.println("P \t"+ Arrays.toString(processes) );
		int[] wt=findWaitingTime(processes,burst);
		findTurnaroundTime(processes,burst,wt);
	}
	public static int[] findWaitingTime(int[] processes,int[] burst){
		int[] waitingTime= new int[processes.length];
		int time=0;

		for(int i=1;i<processes.length;i++){
			waitingTime[i]=burst[i-1]+waitingTime[i-1];
		}

		System.out.printf("WT \t"+ Arrays.toString(waitingTime) + " \tAvg %.2f \n", Average(waitingTime));
		return waitingTime;
	}
	public static void findTurnaroundTime(int processes[],int bt[], int wt[]) {
        int[] tat= new int[processes.length];
		int time=0;

        for (int i = 0; i < processes.length; i++) {
            tat[i] = bt[i] + wt[i];
        }

		System.out.printf("tat \t"+ Arrays.toString(tat)+ " \tAvg %.2f \n", Average(tat));
		//System.out.println("Average WT "+ Average(tat));
		
    }
    public static double Average(int[] values){
    	double sum=0;
    	for(int i:values)
    		sum+=i;
    	return sum/values.length;
    }
	
}