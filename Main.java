import java.util.Scanner;

public class Main {
    public static Scanner ler = new Scanner(System.in);
    int faixa;

    public static void main(String[] args) {
        String enderecoIP;
        String classe = "";
        String[] saida = new String[2];
        System.out.print("Digite um endereco IP: ");
        enderecoIP = ler.nextLine();
        String[] octetos = enderecoIP.split("\\.");
        int i = 0;

        if (validar(octetos)) {
            System.out.println("Endereco IP valido.");
            if (Integer.parseInt(octetos[0]) < 128) {
                classe = "A";

            } else if (Integer.parseInt(octetos[0]) < 192) {
                classe = "B";

            } else if (Integer.parseInt(octetos[0]) < 224) {
                classe = "C";

            }
            if (classe == "A") {
                i = 1;
            }
            if (classe == "B") {
                i = 2;
            }
            if (classe == "C") {
                i = 3;
            }
            System.out.println("Classe: " + classe);
            System.out.println(determinarStatus(octetos, classe, i));
            saida = determinarFaixa(i, octetos);
            System.out.println(saida[0]);
            System.out.println(saida[1]);
        } else {
            System.out.println("Endereco IP invalido.");
        }

    }

    public static boolean validar(String[] octetos) {
        if (octetos.length != 4) {
            return false;
        }
        for (int i = 0; i < octetos.length; i++) {
            int valor = Integer.parseInt(octetos[i]);
            if (valor > 255 || valor < 0) {
                return false;
            }

        }

        return true;

    }

    public static String determinarStatus(String[] octetos, String classe, int i) {
        String retorno = "erro";

        for (int j = i; j < octetos.length; j++) {
            if (Integer.parseInt(octetos[j]) != 0 && Integer.parseInt(octetos[i]) != 255) {
                retorno = "Host";
                break;
            } else if (Integer.parseInt(octetos[j]) == 0) {
                retorno = "rede";

            } else {
                retorno = "Broadcast";
            }
        }
        return retorno;

        // return "Erro";
    }

    public static String[] determinarFaixa(int i, String[] octetos) {
        String[] faixa = new String[2];
        faixa[1] = "";
        faixa[0] = "";
        for (int j = 0; j < i; j++) {
            faixa[0] += octetos[j] + ".";
            faixa[1] += octetos[j] + ".";
        }
        for (; i < 3; i++) {

            faixa[0] += "0" + ".";
            faixa[1] += "255" + ".";
        }
        faixa[0] += "1";
        faixa[1] += "254";
        return faixa;

    }
}
