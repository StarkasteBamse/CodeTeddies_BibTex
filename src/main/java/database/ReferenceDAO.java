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
import com.mongodb.Block;
import com.google.gson.Gson;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
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
    
    /*
    1. Reference tarvitsee metodit getRequiredFields ja getAllInfo
    2. 
    */
    @Override
    public void delete(Reference key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reference> getAll() {
        Gson mapper = new Gson();
        List<Reference> references = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                String json = cursor.next().toJson();
                
                if (json.contains("article")) {
                    Reference reference = mapper.fromJson(json, Article.class);
                    references.add(reference);
                    
                } else if (json.contains("book")) {
                    Reference reference = mapper.fromJson(json, Book.class);
                    references.add(reference);
                    
                } else if (json.contains("inproceedings")) {
                    Reference reference = mapper.fromJson(json, Inproceedings.class);
                    references.add(reference);
                }
                cursor.next().toJson();
            }
        } finally {
            cursor.close();
        }
        return references;
    }
    
    
}
