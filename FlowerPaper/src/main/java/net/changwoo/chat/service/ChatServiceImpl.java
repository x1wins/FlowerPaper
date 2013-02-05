package net.changwoo.chat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.changwoo.chat.dao.JoinDao;
import net.changwoo.chat.dao.MessageDao;
import net.changwoo.chat.dao.RoomDao;
import net.changwoo.x1wins.dao.UserDao;
import net.changwoo.chat.entity.Join;
import net.changwoo.chat.entity.Message;
import net.changwoo.chat.entity.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	MessageDao messageDao;
	@Autowired
	JoinDao joinDao;
	@Autowired
	RoomDao roomDao;
	@Autowired
	UserDao userDao;
	
	int DELETE_VALUE;
	int EXIST_VALUE;
	
	public ChatServiceImpl(){
		DELETE_VALUE = 0;
		EXIST_VALUE = 1;
	}
	
	public int saveMessage(Message message) throws Exception {
		// TODO Auto-generated method stub
		return messageDao.save(message);
	}
	
	public List findMessage(int roomid) throws Exception {
		
		List list = new ArrayList();
		Map roomidMap = new HashMap();
		roomidMap.put("propertyName", "roomid");
		roomidMap.put("value", roomid);
		list.add(roomidMap);
		List messages = messageDao.findAllByProperty(list);
		return messages; 
	}
	
	public int saveJoin(Join join) throws Exception {
		return joinDao.save(join);
	}
	
	public List findUserJoins(int userid) throws Exception {
		
		Map param = new HashMap();
		param.put("propertyName", "userid");
		param.put("value", userid);
//		Map param2 = new HashMap();
//		param2.put("propertyName", "joinStatus");//java entity bean property name
//		param2.put("value", EXIST_VALUE);
		
		List list = new ArrayList();
		list.add(param);
//		list.add(param2);
		
		List joins = joinDao.findAllByProperty(list);

		return joins;
	}
	
	public List findRoomJoins(int roomid) throws Exception {
		
		Map param = new HashMap();
		param.put("propertyName", "roomid");
		param.put("value", roomid);
		Map param2 = new HashMap();
		param2.put("propertyName", "joinStatus");//java entity bean property name
		param2.put("value", EXIST_VALUE);
		
		List list = new ArrayList();
		list.add(param);
		list.add(param2);
		
		List joins = joinDao.findAllByProperty(list);

		return joins;
	}
	
	public void deleteJoin(int userid, int roomid) throws Exception {
		
		Map param1 = new HashMap();
		param1.put("propertyName", "userid");
		param1.put("value", userid);
		Map param2 = new HashMap();
		param2.put("propertyName", "roomid");
		param2.put("value", roomid);
		
		List list = new ArrayList();
		list.add(param1);
		list.add(param2);
		
		Join join = joinDao.findAllByProperty(list).get(0);
		join.setJoinStatus(DELETE_VALUE);
		
		joinDao.update(join);
	}
	
	public int saveRoom(Room room) throws Exception {

		//save room
		int result = roomDao.save(room);
		
		//join
		int roomid = room.getId();
		int userid = room.getMasterUserid();
		Join join = new Join();
		join.setRoomid(roomid);
		join.setUserid(userid);
		joinDao.save(join);		
		
		return result;
	}
	
	public List findRooms() throws Exception {
		
		Map param2 = new HashMap();
		param2.put("propertyName", "openStatus");//java entity bean property name
		param2.put("value", EXIST_VALUE);
		
		List list = new ArrayList();
		list.add(param2);
		
		List rooms = roomDao.findAllByProperty(list);
		
		return rooms;
	}
	
	public void updateRoom(Room room) throws Exception {

		//validation need
		roomDao.update(room);
		
	}
	
	public void deleteRoom(int userid, int roomid) throws Exception {
		
		Map param1 = new HashMap();
		param1.put("propertyName", "masterUserid");
		param1.put("value", userid);
		Map param2 = new HashMap();
		param2.put("propertyName", "id");
		param2.put("value", roomid);
		
		List list = new ArrayList();
		list.add(param1);
		list.add(param2);
		
		Room room = roomDao.findAllByProperty(list).get(0);
		room.setOpenStatus(DELETE_VALUE);
		
		roomDao.update(room);
	}

}
