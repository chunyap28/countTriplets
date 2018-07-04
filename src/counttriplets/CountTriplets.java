/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counttriplets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author chunyap
 */
public class CountTriplets {

    // Complete the countTriplets function below.
    static long countTriplets(long[] arr, long r) {
        //contains indexes by value
        HashMap<Long, info> numberMap = new HashMap<>();
        long count = 0;
        for(int i=arr.length-1; i>=0; i--){
            //check has next 
            info next = new info(0,0);
            info current;
            if( numberMap.containsKey(arr[i]*r) ){
                next = numberMap.get(arr[i]*r);
            }
            
            //save current info
            if( !numberMap.containsKey(arr[i]) ){
                current = new info(1,next.count);
                numberMap.put(arr[i], current);
            }else{
                current = numberMap.get(arr[i]);
                current.addCount(1);
                current.addMatchedNextCount(next.count);
            }
            
            //check has 3 level match
            if( r == 1 ){
                count += ((current.count-1)*(current.count-2))/2;
            }else{
                count += next.matchedNextCount;    
            }            
        }
        
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        long[] arr = new long[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {            
            long arrItem = Long.parseLong(arrItems[i]);            
            arr[i] = arrItem;
        }

        long ans = countTriplets(arr, r);

        System.out.println(ans);
        //bufferedWriter.write(String.valueOf(ans));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        scanner.close();
    }
}

class info{
    public long count;
    public long matchedNextCount;
    
    info(long count, long matchedCount){
        this.count = count;
        this.matchedNextCount = matchedCount;
    }
    
    public void addCount(long count){
        this.count += count;
    }
    
    public void addMatchedNextCount(long count){
        this.matchedNextCount += count;
    }
}