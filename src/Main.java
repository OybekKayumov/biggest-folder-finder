import java.io.File;

public class Main {

    public static void main(String[] args) {

        MyThread thread = new MyThread(1);
        MyThread thread2 = new MyThread(2);

        thread.start();
        thread2.start();

//        String folderPath = "c:/users/o.kayumov/Desktop";
//        File file = new File(folderPath);
//
//        System.out.println(file.length());
//        System.out.println(getFolderSize(file));
        //Desktop size: 81,3 МБ (85349011 байт) -> 85349011
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
}
