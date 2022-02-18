public class sjfNonPreemptive {
	static int[][] processes = new int[10][6];
	static int num = 0;

	public static void main(String[] args) {

		processes[0][0] = 1; // p1
		processes[0][1] = 2;
		processes[0][2] = 3;

		processes[1][0] = 2; // p2
		processes[1][1] = 0;
		processes[1][2] = 4;

		processes[2][0] = 3; // p3
		processes[2][1] = 4;
		processes[2][2] = 2;

		processes[3][0] = 4; // p4
		processes[3][1] = 5;
		processes[3][2] = 4;
		num = 4;

		// sorts the processes in order of their arrivals
		arrangeArrival(num, processes);

		// calculates completion time
		findCompletionTime(num, processes);

		// catculates wt and tat
		WT_and_tat(num, processes);

		print();

	}

	public static void arrangeArrival(int num, int[][] processes) {
		for (int j = num - 1; j > 0; j--) {
			for (int i = 0; i < j; i++) {
				if (processes[i][1] > processes[i + 1][1]) {
					swap(i, i + 1);
				}
			}
		}

	}

	public static void findCompletionTime(int num, int[][] mat) {
		int temp, val = -1;
		// completion time of the first arriving process
		mat[0][3] = mat[0][1] + mat[0][2];
		// here the first process's completion time is calculated

		// calculating comp times for rest based on arrival and low burst
		for (int i = 1; i < num; i++) {
			temp = mat[i - 1][3]; // comp time of prev
			int low = mat[i][2]; // burst

			// to check which proc arrived and choose proc with the smallest burst
			for (int j = i; j < num; j++) {
				if (temp >= mat[j][1] && low >= mat[j][2]) {
					low = mat[j][2];
					val = j;
				}
			}
			mat[val][3] = temp + mat[val][2];// process found

			// now the process is swapped with the ith process
			// (to make tracking of process easier)
			for (int k = 0; k < 6; k++) {
				int tem = mat[val][k];
				mat[val][k] = mat[i][k];
				mat[i][k] = tem;
			}
		}

	}

	public static void WT_and_tat(int num, int[][] mat) {
		for (int i = 0; i < num; i++) {
			mat[i][5] = mat[i][3] - mat[i][1];
			mat[i][4] = mat[i][5] - mat[i][2];
		}
	}

	public static void swap(int a, int b) {
		for (int i = 0; i < 6; i++) {
			int temp = processes[a][i];
			processes[a][i] = processes[b][i];
			processes[b][i] = temp;
		}

	}

	public static void print() {
		System.out.println("Pid\tArrival\tBurst\tcomp\twt\ttat");
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < 6; j++)
				System.out.print(processes[i][j] + "\t");
			System.out.println("");
		}
	}
}