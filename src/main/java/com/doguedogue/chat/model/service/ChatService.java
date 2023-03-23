package com.doguedogue.chat.model.service;

import java.util.List;

import com.doguedogue.chat.model.document.Mensaje;

public interface ChatService {
	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar(Mensaje mensaje);
}
