package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import domain.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;
/**
 *
 * @author Willburner
 */
public class ReferenceDAO implements DAO<Reference> {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private boolean testMode;
    
    public ReferenceDAO(MongoClient mongoClient, boolean mode) {
        setLogging();
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("bibdb");
        this.testMode = mode;
        String collectionName = "references";
        if (mode) {
            collectionName = "test";
        }
        this.collection = database.getCollection(collectionName);
    }
    
//CHECKSTYLE:OFF
    public ReferenceDAO(boolean mode) {
            this(new MongoClient("localhost", 27017), mode);
    }
    
    private void setLogging() {
        Logger mongoLogger = Logger.getLogger("com.mongodb");
        mongoLogger.setLevel(Level.SEVERE);
    }
//CHECKSTYLE:ON    
    @Override
    public void add(Reference reference) {
        Document doc = new Document("type", reference.toString());
        HashMap<String, String> fields = reference.getFieldsMap();
        doc.append("_id", reference.getID());
        
        for (String field : fields.keySet()) {
            doc.append(field, fields.get(field));
        }
        collection.insertOne(doc);
    }
    
    @Override
    public List<Reference> getAll() {
        List<Reference> references = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                String json = cursor.next().toJson();
                String jsonCaseInsensitive = json.toLowerCase();
                Reference referenceType = identifyReferenceType(
                                          jsonCaseInsensitive,
                                          references
                                          );
                references.add(fetchSingleReference(json, referenceType));
            }
        } catch (Exception se) {
            System.out.println(se.getMessage());
        } finally {
            cursor.close();
        }
        return references;
    }

    public Reference identifyReferenceType(String jsonCaseInsensitive,
                    List<Reference> references) throws Exception {
        if (jsonCaseInsensitive.contains("\"type\" : \"article\"")) {
            return new Article();
        } else if (jsonCaseInsensitive.contains("\"type\" : \"book\"")) {
            return new Book();
        } else if (jsonCaseInsensitive.contains("\"type\" : "
                                              + "\"inproceedings\"")) {
            return new Inproceedings();
        } else if (jsonCaseInsensitive.contains("\"type\" : \"phdthesis\"")) {
            return new PhdThesis();
        } else if (jsonCaseInsensitive.contains("\"type\" : \"manual\"")) {
            return new Manual();
        }
        return null;
    }
    
    private Reference fetchSingleReference(String json, Reference reference) 
                                                throws JsonSyntaxException {
        Gson mapper = new Gson();
        HashMap<String, String> fieldMap = mapper.fromJson(json, HashMap.class);
        reference.setID(fieldMap.get("_id"));
        
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
    
    @Override
    public void clearDatabase() {
        this.collection.drop();
    }
    
    @Override
    public void delete(Reference key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void update(Reference key) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String getNewId() {
        return new ObjectId().toString();
    }
}
