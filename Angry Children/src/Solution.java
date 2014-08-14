import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// The part of the program involving reading from STDIN and writing to STDOUT has been provided by us.

public class Solution {
   static BufferedReader in = new BufferedReader(new InputStreamReader(
         System.in));
   static StringBuilder out = new StringBuilder();

   private static int computeUnfairness(List<Integer> sortedList, int numberOfElementsToPick) {
	
	   int unfairness = Integer.MAX_VALUE;
	   for(int i = 0; i <= sortedList.size() - numberOfElementsToPick; i++) {
		   int currentUnFairness = sortedList.get(i+numberOfElementsToPick - 1) - sortedList.get(i);
		   if (unfairness > currentUnFairness) {
			   unfairness = currentUnFairness;
		   }
	   }
	   return unfairness;
   }
   
   public static void main(String[] args) throws NumberFormatException, IOException {
      int numPackets = Integer.parseInt(in.readLine());
      int numKids = Integer.parseInt(in.readLine());
      ArrayList<Integer> packets = new ArrayList<Integer>();
      
      for(int i = 0; i < numPackets; i ++)
      {
         packets.add(Integer.parseInt(in.readLine()));
      }
      
      int unfairness = Integer.MAX_VALUE;
      
        // Write your code here, to process numPackets N, numKids K, and the packets of candies
      // Compute the ideal value for unfairness over here
      
      Collections.sort(packets);
      unfairness = computeUnfairness(packets, numKids);
      
      System.out.println(unfairness);
   }
}






















