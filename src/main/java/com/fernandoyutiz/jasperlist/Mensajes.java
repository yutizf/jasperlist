package com.fernandoyutiz.jasperlist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Mensajes {
    private static int contador;

    public Mensajes() {
        contador=1;
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void saludo(){
        //System.out.println("Pasada num.: " + contador);
        log.info("Pasada num.: " + contador);
        contador++;
    }
}
