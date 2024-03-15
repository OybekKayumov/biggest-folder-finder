import java.io.File;

public class Main {

    public static void main(String[] args) {

        String folderPath = "c:/users/o.kayumov/Desktop";
        File file = new File(folderPath);

        System.out.println(file.length());
        System.out.println(getFolderSize(file));
        //Desktop size: 81,3 МБ (85349011 байт) -> 85349011
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}
