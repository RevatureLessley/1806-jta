package com.revature.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.EventDocument;
import com.revature.dao.EventDocumentDao;
import com.revature.displaywrapper.DocumentDisplay;
import com.revature.utils.MasterLogger;
import com.revature.utils.StringManip;

public class DocumentService {

	/**
	 * Gets a JSON string containing display data for all events associated with the given
	 * event
	 * 
	 * @param evdocId
	 * @return
	 */
	public static String selectEventDocuments(Integer eventId) {
		List<EventDocument> docs = new EventDocumentDao().selectAllEventDocuments(eventId);
		List<DocumentDisplay> wrappedDocs = new ArrayList<>();

		for (EventDocument d : docs)
			wrappedDocs.add(new DocumentDisplay(d));

		return StringManip.getJSONString(wrappedDocs);
	}

	/**
	 * Gets the bytes of the file associated with the given id
	 * 
	 * @param evdocId
	 * @return
	 */
	public static byte[] getDocumentBuffer(Integer evdocId) {
		return new EventDocumentDao().selectDocumentOutputSream(evdocId);
	}
	
	/**
	 * Submit a document to be stored in the database. Document is associated with metadata and an input stream
	 * 
	 * @param eventId
	 * @param docName
	 * @param fileData
	 * @param type
	 */
	public static void submitDocument(Integer eventId, String docName, InputStream fileData, Integer type) {
		EventDocumentDao evDao = new EventDocumentDao();

		evDao.addDocument(eventId, docName, fileData, type);
		
		MasterLogger.info(EventRequestService.class, "document " + docName + " added to event " + eventId);
	}
}
