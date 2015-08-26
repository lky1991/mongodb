package com.lky.mogodb;

import java.util.ArrayList;
import java.util.List;
import java.net.UnknownHostException;




import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

public class Conn
{
	static MongoClient client;
	static DB db;
	static DBCollection collection;

	static
	{
		try
		{
			client = new MongoClient("127.0.0.1", 27017);
			db = client.getDB("test");
			collection = db.getCollection("c1");
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
	}

	public static void add(DBObject dbObject)
	{
		long old=dbCount();
		collection.insert(dbObject);
		if (dbCount()-old>0)
		{
			System.out.println("添加数据成功!!!!");
			return;
		}
		System.out.println("添加数据失败!!!!");
	}

	public static void del(DBObject dbObject)
	{
		WriteResult writeResult = null;
		writeResult = collection.remove(dbObject);
		if (writeResult.getN() > 0)
		{
			System.out.println("删除数据成功!!!!");
			return;
		}
		System.out.println("删除数据失败!!!!");
	}

	public static void update(DBObject dbObject1, DBObject dbObject2)
	{	WriteResult writeResult = null;
		writeResult=collection.update(dbObject1, dbObject2);
		if (writeResult.getN() > 0)
		{
			System.out.println("更新数据成功!!!!");
			return;
		}
		System.out.println("更新数据失败!!!!");
	}

	public static DBObject queryOneByKey(DBObject dbObject, DBObject keys)
	{
		DBCursor dbCursor = collection.find(dbObject, keys);
		if (dbCursor.hasNext())
		{
			return dbCursor.next();
		}
		return null;
	}

	public static long dbCount()
	{
		long count = 0;
		count = collection.count();
		return count;
	}

	public static List<DBObject> query(DBObject dbObject)
	{
		List<DBObject> resut = new ArrayList<DBObject>();
		DBCursor dbCursor = collection.find(dbObject);
		while (dbCursor.hasNext())
		{
			resut.add(dbCursor.next());
		}
		return resut;
	}

	public static void close()
	{
		client.close();
	}

	public static void main(String args[])
	{
		DBCursor cursor=Conn.collection.find().limit(2);
		while(cursor.hasNext())
		{
			System.out.println(cursor.next());
		}
	}
}
