import java.util.Scanner;

public class teste {
     public static Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
        int decimal = ler.nextInt();
        int binario;
        binario = Integer.parseInt(Integer.toBinaryString(decimal));
        System.out.println(binario);
        
    }
}
