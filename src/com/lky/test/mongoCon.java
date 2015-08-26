package com.lky.test;



import java.util.ArrayList;
import java.util.List;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class mongoCon
{

	public static void main(String[] args)
	{
		MongoClient mongo=null;
		
		try
		{
			ServerAddress sAddress=new ServerAddress("127.0.0.1",27017);
			MongoCredential mCredential=MongoCredential.createCredential("lky", "test", "lky".toCharArray());
			List<MongoCredential> ssCredentials=new ArrayList<MongoCredential>();
			ssCredentials.add(mCredential);
			mongo=new MongoClient(sAddress,ssCredentials);
			DB db=mongo.getDB("test");
			DBCollection collection=db.getCollection("c1");
			BasicDBObject doc=new BasicDBObject();
			doc.put("name", "MongoDB");
			collection.insert(doc);
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}finally
		{
			if(mongo!=null)
			{
				mongo.close();
			}
		}
	}
}
