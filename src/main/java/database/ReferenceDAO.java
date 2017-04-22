/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Reference;
import java.util.List;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import com.mongodb.client.MongoCursor;
import domain.Article;
import domain.Book;
import domain.Inproceedings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author Willburner
 */
public class ReferenceDAO implements DAO<Reference> {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    public ReferenceDAO(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("bibdb");
        this.collection = database.getCollection("references");
    }

    public ReferenceDAO() {
        this(new MongoClient( "localhost" , 27017 ));
    }
    
    @Override
    public void delete(Reference key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void add(Reference reference) {
        Document doc = new Document("type", reference.toString());
        HashMap<String, String> fields = reference.getFieldsMap();
        
        for (String field : fields.keySet()) {
            doc.append(field, fields.get(field));
        }
        collection.insertOne(doc);
    }

    @Override
    public void update(Reference key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Reference> getAll() {
        List<Reference> references = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                String json = cursor.next().toJson();
                
                if (json.contains("article")) {
                    references.add(fetchSingleReference(json, 
                                        new Article()));
                    
                } else if (json.contains("book")) {
                    references.add(fetchSingleReference(json, 
                                        new Book()));
                    
                } else if (json.contains("inproceedings")) {
                    references.add(fetchSingleReference(json, 
                                        new Inproceedings()));
                }
            }
        } catch (JsonSyntaxException se) {
            System.out.println(se.getMessage());
        } finally {
            cursor.close();
        }
        return references;
    }
    
    private Reference fetchSingleReference(String json, Reference reference) 
                                                throws JsonSyntaxException{
        Gson mapper = new Gson();
        HashMap<String, String> fieldMap = mapper.fromJson(json, HashMap.class);
        
        for (String field : reference.getRequiredFields()) {
            if (fieldMap.containsKey(field)) {
                reference.setField(field, fieldMap.get(field));
            }
        }
        for (String field : reference.getOptionalFields()) {
            if (fieldMap.containsKey(field)) {
                reference.setField(field, fieldMap.get(field));
            }
        }
        return reference;
    }
}
