import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    public static void main(String[] args) {

        ParametersBag bag = new ParametersBag(args);

        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        FolderSizeCalc calc = new FolderSizeCalc(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calc);
        System.out.println(root);
    }
}
