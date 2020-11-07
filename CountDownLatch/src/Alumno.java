import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Alumno implements Runnable{
    private final HermanoMayor hermano;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Alumno(HermanoMayor hermano) {
        this.hermano = hermano;
    }

    @Override
    public void run() {
        int aportacion;
        try {
            aportacion = buscarDinero();
            if (hermano.countDownLatch.getCount() < 1){
                System.out.printf("%s -> %s ha reunido %d euros, pero el hermano ya había salido a la tienda\n", LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName(), aportacion);
            }else{
                hermano.recaudar(Thread.currentThread().getName(),aportacion);
            }
        }catch (InterruptedException e){

        }

    }
    public int buscarDinero() throws InterruptedException{
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(8)+3);
        return ThreadLocalRandom.current().nextInt(3)+2;
    }
}
