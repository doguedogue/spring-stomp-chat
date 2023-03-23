package com.doguedogue.chat.model.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.doguedogue.chat.model.document.Mensaje;


public interface ChatRepository extends MongoRepository<Mensaje, String> {
	public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
