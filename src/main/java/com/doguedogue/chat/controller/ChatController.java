package com.doguedogue.chat.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.doguedogue.chat.model.document.Mensaje;
import com.doguedogue.chat.model.service.ChatService;

@Controller
public class ChatController {
	
	private String [] colores = {"red", "green", "blue", "magenta", "purple", "orange"};
	
	@Autowired
	private ChatService chatService;

	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if (mensaje.getTipo().equals("USUARIO_NUEVO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("usuario nuevo ");
		} else {
			chatService.guardar(mensaje);
		}
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String recibeMensaje(String username) {
		return username.concat(" está escribiendo ...");
	}
	
	
	@MessageMapping("/historial")
	public void historial(String clientId) {
		webSocket.convertAndSend("/chat/historial/"+clientId, 
				chatService.obtenerUltimos10Mensajes());
	}
	
}
