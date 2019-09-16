package atividade01.socket.validar.cpf;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Vinicius
 */
public class Cliente {
    private static Socket socket;
    private static DataInputStream entrada;
    private static DataOutputStream saida;
    
    public static void main(String[] args) {
        try{
            socket = new Socket("127.0.0.1", 5000);
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());
            
            // Receber do usuário algum valor do cpf
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite algum cpf: ");
            String valor = br.readLine();
            
            // O valor é enviado ao servidor
            saida.writeUTF(valor);
            
            // Recebe-se o resultado do servidor
            boolean resultado = entrada.readBoolean();
            
            // Mostra o resultado na tela
            System.out.println(resultado);
            socket.close();
        }catch(Exception e){
            System.out.println("Ocorreu algum erro: " + e.getMessage());
        }
    }
}
