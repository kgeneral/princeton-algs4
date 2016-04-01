import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BareBonesWebCrawler {

    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        SET<String> discovered = new SET<>();

        String root = "http://www.naver.com";
        queue.enqueue(root);
        discovered.add(root);

        while (!queue.isEmpty()) {
            String v = queue.dequeue();
            StdOut.println(v);
            String input = "";
            try {
                In in = new In(v);
                input = in.readAll();
            } catch (Exception e) {
                StdOut.println("can't find : " + v);
            }

            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);
            while (matcher.find()) {
                String w = matcher.group();
                if (!discovered.contains(w)) {
                    discovered.add(w);
                    queue.enqueue(w);
                }

            }


        }
    }
}
