package T4;
import java.util.Scanner;

public class Main {
    public static Scanner ler = new Scanner(System.in);
    int faixa;

    public static void main(String[] args) {
        String enderecoIP;
        String rede, broadcast;
        String conversãoRede, conversãoBroadcast, binario;
        String[] intervaloHosts;
        conversãoRede = conversãoBroadcast = broadcast = rede = "";
        int mascara;

        System.out.print("Digite um endereco IP e máscara de rede: ");
        enderecoIP = ler.nextLine();

        String[] octetos = enderecoIP.split("\\.");
        int i = 0;

        String[] separador = octetos[3].split("/");
        octetos[3] = separador[0];
        mascara = Integer.parseInt(separador[1]);
        i = mascara / 8;
        binario = adquirirBinario(octetos[i]);
        mascara %= 8;
        for (int j = 0; j < i; j++) {
            rede += octetos[j] + ".";
            broadcast += octetos[j] + ".";
        }
        for (int j = binario.length(); j < 8; j++) {
            binario = "0" + binario;
        }
        for (int j = 0; j < mascara; j++) {
            String bit = String.valueOf(binario.charAt(j));
            conversãoRede += bit;
            conversãoBroadcast += bit;

        }
        for (int j = mascara; j < 8; j++) {
            conversãoRede += "0";
            conversãoBroadcast += "1";
        }
        // rede += ".";
        // broadcast += ".";
        rede += Integer.parseInt(conversãoRede, 2) + ".";
        broadcast += Integer.parseInt(conversãoBroadcast, 2) + ".";
        for (int j = i + 1; j < 4; j++) {
            rede += "0.";
            broadcast += "255.";
        }
        rede = rede.substring(0, rede.length() - 1);
        broadcast = broadcast.substring(0, broadcast.length() - 1);
        intervaloHosts = calcularHosts(rede, broadcast);

        System.out.println("Rede: " + rede);
        System.out.println("Broadcast: " + broadcast);
        System.out.println("Hosts: de " + intervaloHosts[0] + " a " + intervaloHosts[1]);

    }

    public static String[] calcularHosts(String redes, String broadcast) {
        String[] hosts = new String[2];
        hosts[0] = "";
        hosts[1] = "";
        String[] redeOctetos = redes.split("\\.");
        String[] broadcastOctetos = broadcast.split("\\.");
        for (int i = 0; i < 4; i++) {
            if (i != 3) {
                hosts[0] += redeOctetos[i] + ".";
                hosts[1] += broadcastOctetos[i] + ".";

            } else {

                hosts[0] += (Integer.parseInt(redeOctetos[i]) + 1);
                hosts[1] += (Integer.parseInt(broadcastOctetos[i]) - 1);

            }
        }
        return hosts;

    }

    public static String adquirirBinario(String decimal) {
        String binario;
        binario = (Integer.toBinaryString(Integer.parseInt(decimal)));

        return binario;

    }
}
