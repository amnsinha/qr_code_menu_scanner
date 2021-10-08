package com.hotbot.mongoCrudHandler;

import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.AbstractDocument;
import com.mongodb.DB;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class CURDMongoHandler<T extends AbstractDocument> {

    @Autowired
    protected MongoOperations mongoOperation;
    protected GridFsOperations fsOperations;
    protected DB mongoDB;

    @SuppressWarnings("deprecation")


    public boolean saveDocument(Object objectToSave, String collectionName) {
        try {
            this.mongoOperation.save(objectToSave, collectionName);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean updateDocument(Update update, Query updateQuery,
                                  String collectionName) {
        try {
            this.mongoOperation
                    .updateFirst(updateQuery, update, collectionName);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public T getDocument(Class<T> object, Query searchQuery,
                         String collectionName) throws MongoDBDocumentNotFoundException {
        T t = this.mongoOperation.findOne(searchQuery, object, collectionName);
        if (t == null) {
            throw new MongoDBDocumentNotFoundException();
        }
        return t;
    }

    public List<T> getDocuments(Class<T> object, Query searchQuery,
                                String collectionName) {
        return this.mongoOperation.find(searchQuery, object, collectionName);
    }

    public boolean updateDocument(Query updateQuery, Update update,
                                  Class<?> objectToSave, String collectionName) {
        try {
            this.mongoOperation.upsert(updateQuery, update, objectToSave, collectionName);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public long getDocumentsCount(Class<T> object, Query searchQuery,
                                  String collectionName) {
        return this.mongoOperation.count(searchQuery, object, collectionName);
    }

    public boolean saveFile(MultipartFile multipart, String fileName, String ContentType, String collectionName) {
        try {
            GridFS gridfs = new GridFS(this.mongoDB, collectionName);
            byte[] bs = multipart.getBytes();
            if (bs != null && bs.length > 0) {
                GridFSInputFile gfsFile = gridfs.createFile(bs);
                gfsFile.setFilename(fileName);
                gfsFile.setContentType(ContentType);
                gfsFile.save();
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public GridFSDBFile getFile(String reportName, String collectionName) {
        GridFSDBFile gfsFile = null;
        try {
            GridFS gridfs = new GridFS(this.mongoDB, collectionName);
            gfsFile = gridfs.findOne(reportName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gfsFile;
    }


    public boolean checkExist(Class<T> object, Query searchQuery,
                              String collectionName) {
        return this.mongoOperation.exists(searchQuery, object, collectionName);
    }

    public boolean removeDocument(Class<T> object, Query searchQuery,
                                  String collectionName) {
        DeleteResult deleteResult = this.mongoOperation.remove(searchQuery, object, collectionName);
		return deleteResult.getDeletedCount() > 0;

    }


}
