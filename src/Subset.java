import java.util.Iterator;


public class Subset {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        if (k == 0) return;

        RandomizedQueue<String> q = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }

        Iterator<String> it = q.iterator();

        for (int i = 0; i < k; ++i) {
            System.out.println(it.next());
        }
    }

}
