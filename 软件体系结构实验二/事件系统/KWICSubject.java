package 软件体系结构实验二.事件系统;

public class KWICSubject extends Subject{
    public void startKWIC(){
        for (int i = 0;i<3;i++){
            super.notifyOneObserver(i);
        }
    }

    public void addObserver(Input input) {
    }

    public void addObserver(Alphabetizer alphabetizer) {
    }

    public void addObserver(Output output) {
    }

    public void addObserver(Shift shift) {

    }
}
