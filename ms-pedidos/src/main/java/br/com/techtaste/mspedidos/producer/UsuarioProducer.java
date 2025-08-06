package br.com.techtaste.mspedidos.producer;

import br.com.techtaste.mspedidos.dto.EmailDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("fila.mensagem.usuario")
    private String queue;

    public void enviarEmail(EmailDto email){
        rabbitTemplate.convertAndSend(queue, email);
        System.out.println(email);
    }
}
