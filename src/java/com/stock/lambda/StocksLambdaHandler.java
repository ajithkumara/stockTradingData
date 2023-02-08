package com.stock.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.JsonParser;
import com.stock.pojo.Stock;

public class StocksLambdaHandler implements RequestStreamHandler{

 

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(outputStream);
		BufferedReader reader     = new BufferedReader(new InputStreamReader(inputStream));
		JSONObject responseObject = new JSONObject();
		JSONObject responseBody   = new JSONObject();
		JSONParser jsonParser     = new JSONParser();
		
		AmazonDynamoDB dbClient   = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDB dynamoDB         = new DynamoDB(dbClient); 
		
		try{
		
			JSONObject reqObject  = (JSONObject)jsonParser.parse(reader);
			
			if (null!=reqObject.get("body")) {
				
				Stock stock = new Stock((String)reqObject.get("body"));
				
				dynamoDB.getTable("stock_trading_daily")
				        .putItem(new PutItemSpec().withItem(new Item()
				        		.withString("date",stock.getDate())
				        		.withString("stockName",stock.getStockName()) 
				        		.withDouble("open",stock.getOpen())
				        		.withDouble("high",stock.getHigh())
				        		.withDouble("low",stock.getLow())
				        		.withDouble("close",stock.getClose())
				        		.withDouble("adjClose",stock.getAdjClose())
				        		.withInt("volume", stock.getVolume())
				        		));
				responseBody.put("message", "new Stock added!");
				responseObject.put("statusCode", 200);
				responseObject.put("body", responseBody.toString());
				
				
			}
			
		}catch(Exception e) {
			
			responseObject.put("statusCode", 400);//Bad Request 
			responseObject.put("error",e);
			
		}
		
		writer.write(responseObject.toString());
		reader.close();
		writer.close();
		
 
	}
}
