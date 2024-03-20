import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalc extends RecursiveTask {

    private Node node;

    public FolderSizeCalc(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        //? if it is a file. we just return it's size
        File folder = node.getFolder();
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        List<FolderSizeCalc> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();

        for (File file : files) {
            Node child = new Node(file);
            FolderSizeCalc task = new FolderSizeCalc(child);
            task.fork(); //? запустим асинхронно
            subTasks.add(task);
            node.addChild(child);
        }

        for (FolderSizeCalc task : subTasks) {
            //? дождемся выполнения задачи и прибавляем результат
            sum += (long) task.join();
        }


        node.setSize(sum);
        return sum;
    }
}
