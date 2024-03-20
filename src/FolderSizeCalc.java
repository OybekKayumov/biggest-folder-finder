import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalc extends RecursiveTask {

    private File folder;

    public FolderSizeCalc(File folder) {
        this.folder = folder;
    }

    @Override
    protected Long compute() {
        //? if it is a file. we just return it's size
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        List<FolderSizeCalc> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();

        for (File file : files) {
            FolderSizeCalc task = new FolderSizeCalc(file);
            task.fork(); //? запустим асинхронно
            subTasks.add(task);
        }

        for (FolderSizeCalc task : subTasks) {
            //? дождемся выполнения задачи и прибавляем результат
            //sum += task.join();
        }
        return sum;
    }
}
