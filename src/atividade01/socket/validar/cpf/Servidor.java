package atividade01.socket.validar.cpf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import atividade01.socket.validar.cpf.ValidarCpf;

/**
 *
 * @author Vinicius
 */
public class Servidor {
    private static Socket socket;
    private static ServerSocket server;
    
    private static ValidarCpf validarCpf;
    
    private static DataInputStream entrada;
    private static DataOutputStream saida;
    
    public static void main(String[] args) {
        try{
            // Criar porta de recepção
            server = new ServerSocket(5000);
            socket = server.accept();
            
            validarCpf = new ValidarCpf();
            
            // Criar os fluxos de entrada e saída
            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());
            
            // Recebimento do valor String
            String valor = entrada.readUTF();
            System.out.println(valor);
            
            // Processamento do valor
            boolean resultado = validarCpf.validarCpf(valor);
            
            // Envio dos dados (resultado)
            saida.writeBoolean(resultado);
            socket.close();
        }catch(Exception e){
            System.out.println("Ocorreu um erro no servidor: " + e.getMessage());
        }
    }
}
