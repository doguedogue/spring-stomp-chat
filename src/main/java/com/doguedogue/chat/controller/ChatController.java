package com.doguedogue.chat.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.doguedogue.chat.model.document.Mensaje;

@Controller
public class ChatController {

	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if (mensaje.getTipo().equals("USUARIO_NUEVO")) {
			mensaje.setTexto("usuario nuevo ");
		}		
		return mensaje;
	}
	
}
