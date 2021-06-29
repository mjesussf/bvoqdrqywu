import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {
    public static final int HEADS = 0;
    public static final int TAILS = 1;

    public static boolean printChanges = true;

    public static boolean isFacingProperly(boolean headStartsEven, boolean indexIsEven, int value){
        return (headStartsEven && ((indexIsEven && value==HEADS)||(!indexIsEven && value==TAILS)))
                || (!headStartsEven && ((indexIsEven && value==TAILS)||(!indexIsEven && value==HEADS)));
    }

    public static int[] reverse(int[] A, List<Integer> toReverse){
        int[] reversed = Arrays.copyOf(A,A.length);
        for(int i: toReverse)
            reversed[i]=reversed[i]==HEADS?TAILS:HEADS;
        return reversed;
    }

    public static void printChanges(int[] A, List<Integer> toFlip){
        System.out.println();
        Arrays.stream(A).forEach(System.out::print);
        System.out.println();
        IntStream.range(0,A.length).forEach(i->System.out.print(toFlip.contains(i)?"*":" "));
        System.out.println();
        int[] flipped = reverse(A, toFlip);
        Arrays.stream(flipped).forEach(System.out::print);
        System.out.println();
    }

    public int solution(int[] A){
        List<Integer> headOnEvenReverse = new ArrayList<>();
        List<Integer> headOnOddReverse = new ArrayList<>();
        List<Integer> toReverse;
        for (int i = 0; i < A.length; i++){
            boolean indexIsEven = i%2==0;
            int value = A[i];
            boolean isFacingProperlyForEven = isFacingProperly(true, indexIsEven, value);
            if(!isFacingProperlyForEven)
                headOnEvenReverse.add(i);
            else
                headOnOddReverse.add(i);
        }
        toReverse = headOnEvenReverse.size()<=headOnOddReverse.size()?headOnEvenReverse:headOnOddReverse;
        if(printChanges)
            printChanges(A, toReverse);
        return toReverse.size();
    }

    public static void main(String[] args) {
        Solution demo = new Solution();
        System.out.println(demo.solution(new int[]{1,0,1,0,1,1}));
        System.out.println(demo.solution(new int[]{1,1,0,1,1}));
        System.out.println(demo.solution(new int[]{0,1,0}));
        System.out.println(demo.solution(new int[]{0,1,1,0}));
        System.out.println(demo.solution(new int[]{1,0,0,1}));
        Random random = new Random();
        System.out.println(demo.solution(IntStream.range(0, 101).map(i->random.nextInt(2)).toArray()));
        System.out.println(demo.solution(IntStream.range(0, 1000).map(i->random.nextInt(2)).toArray()));
    }
}
