import java.util.ArrayList;
import java.util.Collections;

public class RefactoringSourceCode {

    public static class Replacement implements Comparable<Replacement> {
        String repl;
        String orig;
        int idx;
        public Replacement(int ind, String origi, String rep) {
            idx = ind;
            repl = rep;
            orig = origi;
        }

        @Override
        public int compareTo(Replacement comp) {
            return this.idx-comp.idx;
        }
    }

    public static void main(String args[]) {
        String text = "num foo;";

        ArrayList<Replacement> reps = new ArrayList<>();
        reps.add(new Replacement(0, "num", "String"));
        reps.add(new Replacement(4, "foo;", "bar"));

        Collections.sort(reps);

        StringBuffer buf = new StringBuffer(text);
        for(int i=reps.size()-1;i>=0;i--) {
            Replacement rep = reps.get(i);
            System.out.println(rep.idx);
            buf.replace(rep.idx, rep.idx + rep.orig.length(), rep.repl);
        }

        String mod = buf.toString();
        System.out.println(mod);
    }
}
