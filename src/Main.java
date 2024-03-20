import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};

    public static void main(String[] args) {

//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//
//        thread.start();
//        thread2.start();

//        String folderPath = "c:/users/user/Desktop";
//        File file = new File(folderPath);
//
//        System.out.println(file.length());
//        System.out.println(getFolderSize(file));
        //Desktop size: 81,3 МБ (85349011 байт) -> 85349011

        //*
        String folderPath = "c:/users/o.kayumov/Desktop";
        File file = new File(folderPath);
        Node root = new Node(file);

        FolderSizeCalc calc = new FolderSizeCalc(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calc);
        System.out.println(root.getSize());

        //*
        System.out.println(getHumanReadableSize(240640));
        System.exit(0);

        //*
        System.out.println(getSizeFromHumanReadable("235K"));
        System.exit(0);
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

    public static String getHumanReadableSize(long size) {

        for (int i = 0; i < sizeMultipliers.length; i++) {
            double value = size / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value) + "" +
                        sizeMultipliers[i] +
                        (i > 0 ? "b" : "");
            }
        }
        return "Very big!";
    }

    public static long getSizeFromHumanReadable(String size) {
        HashMap<Character, Integer> char2multiplier = getMultipliers();
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(
                size.replaceAll("[^0-9]", "")
        );

        return length;
    }

    private static HashMap<Character, Integer> getMultipliers() {
        //char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < sizeMultipliers.length; i++) {
            char2multiplier.put(
                    sizeMultipliers[i],
                    (int) Math.pow(1024, i)
            );
        }
        return char2multiplier;
    }
}
