package �����ϵ�ṹʵ���.�¼�ϵͳ;

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
