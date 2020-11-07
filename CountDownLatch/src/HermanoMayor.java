import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class HermanoMayor implements Runnable{
    protected final CountDownLatch countDownLatch;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private  final int cantidad;

    public HermanoMayor(int cantidad) {
        this.cantidad = cantidad;
        countDownLatch = new CountDownLatch(cantidad);
    }

    @Override
    public void run() {
        System.out.printf("%s -> El hermano mayor de uno de los alumnos está esperando a que haya %d euros recaudados\n",
                LocalTime.now().format(dateTimeFormatter), cantidad);

        try {
            countDownLatch.await();
            System.out.printf("%s -> El hermano mayor tiene los %d euros, va hacia la tienda a comprar el regalo\n",
                    LocalTime.now().format(dateTimeFormatter), cantidad);
        } catch (InterruptedException e) {
            System.out.printf("Hermano mayor ha sido interrumpido mientras esperaba que hubiera %d euros", cantidad);
        }
    }

    public void recaudar(String nombreAlumno, int aportacion){
        System.out.printf("%s -> %s ha aportado %d euros\n",
                LocalTime.now().format(dateTimeFormatter), nombreAlumno, aportacion);
        for (int i = 0; i < aportacion; i++) {
            countDownLatch.countDown();
        }
    }
}
