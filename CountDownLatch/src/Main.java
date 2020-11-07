public class Main {
    private static final int cantidadRegalo = 10;

    public static void main(String[] args) {
        HermanoMayor hermano = new HermanoMayor(cantidadRegalo);
        new Thread(hermano).start();
        for (int i = 1; i < 6; i++) {
            new Thread(new Alumno(
                    hermano),"Alumno #"+i).start();
        }
    }
}
