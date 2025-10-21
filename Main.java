import java.util.Scanner;

public class Main {
    public static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
        String enderecoIP;
        String classe = "";
        String status;
        System.out.print("Digite um endereco IP: ");
        enderecoIP = ler.nextLine();
        String[] octetos = enderecoIP.split("\\.");
        if (validar(octetos)) {
            System.out.println("Endereco IP valido.");
            if (Integer.parseInt(octetos[0]) < 128) {
                classe = "A";

            } else if (Integer.parseInt(octetos[0]) < 192) {
                classe = "B";

            } else if (Integer.parseInt(octetos[0]) < 224) {
                classe = "C";

            }
            System.out.println("Classe: " + classe);
            System.out.println(determinarStatus(octetos, classe));

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
    public static String determinarStatus(String[] octetos, String classe) {
        int i = 0;
        if (classe == "A") {
            i = 1;
        }
        if (classe == "B") {
            i = 2;
        }
        if (classe == "C") {
            i = 3;
        }
     
            for (; i < octetos.length; i++) {
                if (Integer.parseInt(octetos[i]) != 0 && Integer.parseInt(octetos[i]) != 255) {
                 return "Host";
                    
                } else if (Integer.parseInt(octetos[i]) == 0){
                    return "Rede";
                }
                 else{
                    return "Broadcast";
                }
            }
        
       
        return "Erro";
    }
}
