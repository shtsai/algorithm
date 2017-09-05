import java.util.*;

class BestAverageGrade {
    private static class TotalGrade {
	double total;
	int examCount;
    }
    
    public static int bestAverageGrade(String[][] grades) {
	HashMap<String, TotalGrade> map = new HashMap<>();
        double max = Double.MIN_VALUE;
	for (int i = 0; i < grades.length; i++) {
	    if (!map.containsKey(grades[i][0])) {	// don't have record for this student yet
		map.put(grades[i][0], new TotalGrade());
	    }
	    TotalGrade record = map.get(grades[i][0]);
	    record.total += Double.parseDouble(grades[i][1]);
	    record.examCount++;
    	}

	for (TotalGrade record : map.values()) {
	    Double newAverage = record.total/record.examCount;
	    if (newAverage > max) max = newAverage;
	}

	return (int) Math.floor(max);
    } 
    
    
    public static void main(String[] args) {
	String[][] input = new String[5][2];
	input[0][0] = "Sean";
	input[0][1] = "100";
	input[1][0] = "Iris";
	input[1][1] = "90.5";
	input[2][0] = "Sean";
	input[2][1] = "50.75";
	input[3][0] = "Iris";
	input[3][1] = "20.0";
	input[4][0] = "Sean";
	input[4][1] = "-100.0";
	int res = bestAverageGrade(input);
	System.out.println(res);
    }
}
