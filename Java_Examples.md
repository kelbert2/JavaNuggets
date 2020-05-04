# Java Exampes
## Reverse Polish Notation

Reverse Polish notation is a mathematical notation in which every operator follows all of its operands.
2
3 4 - 5 +
3 6 /
Ouput:
4
0
```Java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        List<String[]> inputs = new ArrayList<String[]>();
        
        List<String> outputs = new ArrayList<String>();
        int index = 0;
        // first 
        scanner.nextLine();
        while(scanner.hasNextLine()) {
            // inputs.add(scanner.next()).split("");
            outputs.add(processLine((scanner.nextLine()).split(" ")));
        }
        for (int i = 0; i < outputs.size(); i++) {
            System.out.println(outputs.get(i));
        }
        
        scanner.close();
        
    }
    
    public static String processLine(String[] line) {
        ArrayList<String> operands = new ArrayList<String>();
        String operator = "";
        Pattern operandPattern = Pattern.compile("-?[0-9]+"); // allow for negatives and multiple digits
        Pattern operatorPattern = Pattern.compile("[+\\-*/xyz]");
        
        for (int i = 0; i < line.length; i++) {
            // extract digit operands
            if (operandPattern.matcher(line[i]).matches()) {
            // if (Pattern.matches("[0-9]", line[i])) {
                operands.add(line[i]);
            } else if (operatorPattern.matcher(line[i]).matches()) {
            // } else if (Pattern.matches("[+\\-*/xyz]", line[i])) {
                // System.out.println("Found operator: "  + line[i] + " for operands: " + operands.toString()); 
                // extract operator, if exists
                switch(line[i]){
                    // check there's enough operands for the operator, else return NO
                    case "y" :
                        if (operands.size() == 1) {
                            // perform operation
                            String res = "" + operation(operands, line[i]);
                            // clear operands array, put in result from operation
                            operands.clear();
                            operands.add(res);
                            // check for more digits and operators
                        } else {
                            return "NO";
                        }
                        break;
                    case "z" :
                        if (operands.size() == 3) {
                            String res = "" + operation(operands, line[i]);
                            // clear operands array, put in result from operation
                            operands.clear();
                            operands.add(res);
                        } else {
                            return "NO";
                        }
                        break;
                    default:
                        if (operands.size() == 2) {
                            String res = "" + operation(operands, line[i]);
                            // clear operands array, put in result from operation
                            operands.clear();
                            operands.add(res);
                        } else {
                            return "NO";
                        }
                        break;
                }
            } else {
                // unidentified characters.
                // return operands.toString();
                return "NO";
            }
        }
        // return first value of the operands array, converted to a string
            return operands.get(0);
    }
    // Need to output NO if operation fails
    public static int  operation(ArrayList<String> numbers, String operator) {
        switch (operator) {
            case "+":
                // if (numbers.size() > 1){
                return Integer.valueOf(numbers.get(0)) + Integer.valueOf(numbers.get(1));
                // }
                // return 0;
            case "-": 
                // if (numbers.size() > 1){
                return Integer.valueOf(numbers.get(0)) - Integer.valueOf(numbers.get(1));
                // }
                // return 0;
            case "*":
                // if (numbers.size() > 1){
                return Integer.valueOf(numbers.get(0)) * Integer.valueOf(numbers.get(1));
                // }
                // return 0;
            case "/":
                // if (numbers.size() > 1){
                return Integer.valueOf(numbers.get(0)) / Integer.valueOf(numbers.get(1));
                // }
                // return 0;
            case "x":
                // if (numbers.size() > 1){
                return (int) Math.pow(Integer.valueOf(numbers.get(0)), 2) + Integer.valueOf(numbers.get(1));
                // }
                // return 0;
            case "y":
                // if (numbers.size() > 0){
                return Integer.valueOf(numbers.get(0)) * 2 + 1;
                // }
                // return 0;
            case "z":
                // if (numbers.size() > 2){
                    return Integer.valueOf(numbers.get(0)) + 2 * Integer.valueOf(numbers.get(1)) + 3 * Integer.valueOf(numbers.get(2));
                // }
                // return 0;
            default:
                return Integer.valueOf(numbers.get(0));
        }
    }
}
```

## Get Day of Week
```Java
class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        int difference = countDays(day, month, year) - countDays(1, 1, 1971);
        String[] weekdays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        
        return weekdays[(difference + 5) % 7];
    }
    // Sun Mon Tues Wed Thurs Fri Sat
    // 0   1   2    3   4     5   6
    // 1 1 1971 was a Friday
    // Days since startDate + 5, mod 7
    
    public int countDays(int day, int month, int year) {
        return daysBeforeYear(year) + daysBeforeMonth(month, isLeapYear(year)) + day;
    }
    
    public boolean isLeapYear(int year) {
        // 2000 was a leap year
        // Leap years are divisible by 4 and not evenly divisible by 100 unless they're evenly divisible by 400
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
        
        // return year % 4 == 0 || ( year % 400 == 0 && !(year % 100 == 0));
    }
    
    public int leapDaysBefore(int year) {
        int years = year - 1;
        return years / 4 - years / 100 + years / 400; 
    }
    
    public int daysBeforeYear(int year) {
        return 365 * (year - 1) + leapDaysBefore(year);
    }
    
    public int daysBeforeMonth(int month, boolean leapYear) {
        /*
        switch (month) {
            case 12:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30;
            case 11:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31;
            case 10:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31 + 30 + 31 + 31 + 30;
            case 9:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31 + 30 + 31 + 31;
            case 8:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31 + 30 + 31;
            case 7:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31 + 30;
            case 6:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30 + 31;
            case 5:
                 return 31 + (leapYear ? 29 : 28) + 31 + 30;
            case 4:
                 return 31 + (leapYear ? 29 : 28) + 31;
            case 3:
                 return 31 + (leapYear ? 29 : 28);
            case 2:
                 return 31;
            case 1:
                return 0;
        } 
        */
        
        int ret = 0;
        switch (month) {
            case 12:
                ret += 30;
            case 11:
                ret += 31;
            case 10:
                ret += 30;
            case 9:
                ret += 31;
            case 8:
                ret += 31;
            case 7:
                ret += 30;
            case 6:
                ret += 31;
            case 5:
                ret += 30;
            case 4:
                ret += 31;
            case 3:
                ret += (leapYear ? 29 : 28);
            case 2:
                ret += 31;
            case 1:
                return ret;
            default:
                return ret;
        }
    }
}
```

## Maximum Subsequence of an Array
```Java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxSubarray function below.
    static int[] maxSubarray(int[] arr) {
        // each entry in array stores the max value that can be reached starting from them
        int lastValue = arr[arr.length - 1];
        int maxArray = lastValue;
        int maxValue = lastValue;
        int maxSubsequence = 0;
        int next = lastValue;
        // int[] toEnd = new int[arr.length];
        // toEnd[arr.length - 1] = lastValue;

        for (int index = arr.length - 1; index >= 0; index--) {
            maxSubsequence += ((arr[index] > 0) ? arr[index] : 0); // add to subsequence if greater than 0.
            maxValue = Math.max(arr[index], maxValue); // this will be the max subsequence if no values are greater than 0
            if (index < arr.length - 1) {
                // is it better to keep going or stop there? Set to max from there
                // toEnd[index] = Math.max(arr[index], toEnd[index + 1] + arr[index]);
                next = Math.max(arr[index], next + arr[index]); // must include current index
                maxArray = Math.max(next, maxArray);
                // maxArray = Math.max(toEnd[index], maxArray);
            }
        }
        // for (int i = 0; i < toEnd.length; i++) {
        //     maxArray = Math.max()
        // }
        int[] ret = new int[2];
        ret[0] = maxArray;
         ret[1] = maxSubsequence == 0 ? maxValue : maxSubsequence; // if maxSubsequence is still 0, it may be that no values above 0 were found
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];
 
            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] result = maxSubarray(arr);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

        scanner.close();
    }
}
```

Maximum mod:
```Java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maximumSum function below.
    // (a + b) % m = (a % m + b % m) % m
    static long maximumSum(long[] a, long m) {
        long lastValue = a[a.length - 1] % m;
        long modMax = lastValue;
        long next = 0;

        TreeSet<Long> prefix = new TreeSet<Long>();
        // prefix.get(i) == (a[0] + a[1] + ... + a[i]) % m;

        for (int i = 0; i < a.length; i++) {
            next = (a[i] % m + next) % m;
            // next has already been modded m
            modMax = Math.max(next, modMax);

            // Subarray: sumSub[i, j] == (previx[j] - prefix[i - 1] + m) % m; because subtracting cancels out the sum's contribution
            // looking for some prefix[j] > prefix[i] because when prefix[j] < prefix[i], both of which are < M:
            // (prefix[i] - prefix[j] + m) % m = prefix[i] - prefix[j] <= prefix[i];
            Long pre = prefix.higher(next);
            // want to get as close to m < m, so looking for the smallest number higher than prefix[i]
            if (pre != null) {
                modMax = Math.max((next - pre + m) % m, modMax);
            }
            prefix.add(next);
        }
        return modMax;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}


```
## Reversing a Binary Tree
Given a node at which to mirror all of its children, flip the tree there.

Given a tree input as an array, where:
```Java
int parentIndex = (index - 2) / 2;
int leftChild = index * 2 + 1;
int rightChild = index * 2 + 2;
```
Recursively, we just go through each parent and switch its children.
```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        return invertChildren(root);
    }
    public TreeNode invertChildren(TreeNode node) {
        if (node == null) {
            return node;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        if (node.left != null) {
            invertChildren(node.left);
        }
        if (node.right != null) {
            invertChildren(node.right);
        }
        return node;
    }
}
```

## Text Justification
Pad with spaces between words, except if last line.

Test Case:
```Java
     ["This", "is", "an", "example", "of", "text","asa", "1234567890123456", "123456789", "123456", "a", "justification."]
     16
```
Output:
```Java
["This    is    an","example  of text","asa             ","1234567890123456","123456789 123456","a justification."]
```
```Java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();
        
        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            List<String> line = new ArrayList<String>();
            line.add(words[wordIndex]); // assume no word.length() > maxWidth
            int lineLength = words[wordIndex].length();
            
            while (wordIndex + 1 < words.length && (lineLength + 1 + words[wordIndex + 1].length()) <= maxWidth) { // next word exists and can fit with a space before it
                    wordIndex++;
                    line.add(words[wordIndex]); // add word
                    lineLength += (words[wordIndex].length() + 1); // need at least one space before the word, so factor this into our current line Length
            }
            // line is as full of words as can be
            if (wordIndex < words.length - 1) { // is not the last word
                int minSpaceCount = 0;
                if (line.size() > 1) {
                    // System.out.println("linelength with single space between: " + lineLength);
                    minSpaceCount = (maxWidth - lineLength) / (line.size() - 1); // allocate as many extra spaces between each word (line.size() - 1 spaces for space) as possible
                    lineLength += minSpaceCount * (line.size() - 1);
                
                     minSpaceCount++; // add the extra space we've already added to line length between words
                    
                    // System.out.println("minSpaceCount: " + minSpaceCount);
                }
                
                StringBuilder spaceBuilder = new StringBuilder();
                for (int s = 0; s < minSpaceCount; s++) {
                    spaceBuilder.append(" ");
                }
                String spaces = spaceBuilder.toString();
                
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < line.size() - 1; j++) { // giving special treatment for the last word
                    sb.append(line.get(j));
                    sb.append(spaces);
                    if (lineLength < maxWidth) {
                        sb.append(" ");
                        lineLength++;
                        // System.out.println("Line length after appending one: " + lineLength);
                    }
                }
                // last word doesn't have spaces appended behind it unless it is the only word for that line
                sb.append(line.get(line.size() - 1));
                
                while (lineLength < maxWidth) { // in the case of there being only one word in this line and it not being the last line
                    sb.append(" ");
                    lineLength++;
                }
                lines.add(sb.toString());
            } else {
                // Last word means we're on the last line, which gets special treatment
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < line.size(); j++) {
                    sb.append(line.get(j));
                    if (j < line.size() - 1) {
                        sb.append(" "); // insert space after word if it is not the last one.
                    }   
                }
                while (lineLength < maxWidth) {
                    sb.append(" ");
                    lineLength++;
                }
                lines.add(sb.toString());
            }         
        }
        return lines;
    }
}
```

## Bipartite Graph Maximum Matching
```Java
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the realEstateBroker function below.
     */
    static int realEstateBroker(int[][] clients, int[][] houses) {
        // Bipartite graph connecting clients to houses that fit their criteria. Then find the maximum matching, which is the maximum flow between some source common to all houses and a sink common to all houses.
        // clients[i][0] < houses[j][0]
        // clients[i][1] >= houses[j][1]
        // uses indices:
        boolean[][] graph = new boolean[clients.length][houses.length];
        // value at graph[i][j] = 1 if client[i] would accept house[j]
        int[] matches = new int[houses.length]; // store clientIndex stored at houseIndex, -1 if no one is assigned
        Arrays.fill(matches, -1);

        // Fill up the graph
        for (int i = 0; i < clients.length; i++) {
            for (int j = 0; j < houses.length; j++) {
                graph[i][j] = (clients[i][0] < houses[j][0] && clients[i][1] >= houses[j][1]);
                // if (matches[j] == 0) {
                //     matches[j] = -1; // fill matches to be unassigned
                // }
            }
        }
        int assignedCount = 0;
        // if client cannot accept any other houses, assign to that house to them. If 
        for (int u = 0; u < clients.length; u++) {
            boolean[] seen = new boolean[houses.length];
            // for (int i = 0; i < houses.length; i++) {
            //     seen[i] = false; // reset seen array for next applicate
            // }
            if (bpm(graph, u, seen, matches)) { // other client can buy another house
                assignedCount++;
            }
        }

        return assignedCount;
    }

    // DFS-based recursive function that true if a matching for vertex u is possible
    // bipartitle matching
    static boolean bpm(boolean[][] graph, int u, boolean[] seen, int[] matches) {
        // try each house
        for (int v = 0; v < seen.length; v++) {
            if (graph[u][v] && !seen[v]) { // there is an edge to a node that has not been visited
                seen[v] = true; // mark as visited

                // if house v is not assigned to a client 
                // OR previously assigned client for hourse v, 
                // as stored in matches[v], has alternative houses they'd accept
                if (matches[v] < 0 || bpm(graph, matches[v], seen, matches)) {
                    matches[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0].trim());

        int m = Integer.parseInt(nm[1].trim());

        int[][] clients = new int[n][2];

        for (int clientsRowItr = 0; clientsRowItr < n; clientsRowItr++) {
            String[] clientsRowItems = scanner.nextLine().split(" ");

            for (int clientsColumnItr = 0; clientsColumnItr < 2; clientsColumnItr++) {
                int clientsItem = Integer.parseInt(clientsRowItems[clientsColumnItr].trim());
                clients[clientsRowItr][clientsColumnItr] = clientsItem;
            }
        }

        int[][] houses = new int[m][2];

        for (int housesRowItr = 0; housesRowItr < m; housesRowItr++) {
            String[] housesRowItems = scanner.nextLine().split(" ");

            for (int housesColumnItr = 0; housesColumnItr < 2; housesColumnItr++) {
                int housesItem = Integer.parseInt(housesRowItems[housesColumnItr].trim());
                houses[housesRowItr][housesColumnItr] = housesItem;
            }
        }

        int result = realEstateBroker(clients, houses);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
```


## Word Ladder
```Java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0; // not there, can't reach
        Queue<String> queue = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        
        queue.add(beginWord);
        wordSet.add(endWord);
        int level = 1;
        
        // bfs to find shortest path from beginWord to endWord
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char[] current = queue.poll().toCharArray();
                for (int j = 0; j < current.length; j++) {
                    char temp = current[j];
                    // for long wordLists, is quicker to make every iteration of the word (String.length() * 25) than it is to check against each in wordList
                    for (char ch='a'; ch <= 'z'; ch++) {
                        if (current[j] == ch) continue; // don't need to check
                        current[j] = ch;
                        String check = new String(current);
                        if (wordSet.contains(check)) {
                            if (check.equals(endWord)) return level + 1; // found it!
                            queue.add(check);
                            wordSet.remove(check); // seen
                        }
                    }
                    current[j] = temp;
                }
            }
            level++;
        }
        return 0; // couldn't find
    }
        // Create graph with edges connecting words that only differ by one letter
        /*
        int[][] graph = new int[wordList.size() + 2][wordList.size() + 2];
        for (int i = 0; i < wordList.size() + 2; i ++) {
            for (int j = 0; j < wordList.size()+ 2; j ++) {
                // check for equality
                if (i != j) {
                    // may need to try to avoid duplicated check
                String wordA;
                String wordB;
                if (i == wordList.size()) {
                    wordA = beginWord;
                } else if (i == wordList.size() + 1) {
                    wordA = endWord;
                } else {
                    wordA = wordList.get(i);
                }
                if (j == wordList.size()) {
                    wordB = beginWord;
                } else if (j == wordList.size() + 1) {
                    wordB = endWord;
                } else {
                    wordB = wordList.get(j);
                }
                
                if (oneDifference(wordA, wordB)) {
                    graph[i][j] = 1;
                }
                }
            }
        }
        */

    public List<String> getNeighbors(String word, List<String> wordList) {
        // return List of words that differ from input word by only one letter
        List<String> res = new ArrayList<String>();
        for (String str: wordList) {
            if (oneDifference(word, str)){
                res.add(str);
            }
        }
        return res;
    }
    // for two strings with the same number of letters
    public boolean oneDifference(String wordA, String wordB) {
        int differences = 0;
        char[] a = wordA.toCharArray();
        char[] b = wordB.toCharArray();
        for (int i = 0; i < a.length; i ++) {
            if (a[i] != b[i]) {
                differences++;
                if (differences > 1) {
                    return false;
                }
            }
        }
        if (differences == 0) {
            return false; // same word, don't connect
        }
        return true;
    }
}
```