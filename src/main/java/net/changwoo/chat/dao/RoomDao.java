package net.changwoo.chat.dao;

import net.changwoo.chat.entity.Room;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class RoomDao extends GenericDaoImpl<Room, Integer> {

	@Autowired
	RoomDao(SessionFactory sf) {
		super(sf);
	}

	@Override
	protected Class<Room> getEntityClass() {
		return Room.class;
	}
	
}