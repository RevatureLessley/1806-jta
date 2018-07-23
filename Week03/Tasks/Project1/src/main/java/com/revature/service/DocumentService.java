package com.revature.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.EventDocument;
import com.revature.dao.EventDocumentDao;
import com.revature.displaywrapper.DocumentDisplay;
import com.revature.utils.StringManip;

public class DocumentService {

	public static String selectEventDocuments(Integer eventId) {
		List<EventDocument> docs = new EventDocumentDao().selectAllEventDocuments(eventId);
		List<DocumentDisplay> wrappedDocs = new ArrayList<>();

		for (EventDocument d : docs)
			wrappedDocs.add(new DocumentDisplay(d));

		return StringManip.getJSONString(wrappedDocs);
	}

	public static byte[] getDocumentBuffer(Integer evdocId) {

		
		return new EventDocumentDao().selectDocumentOutputSream(evdocId);

	}
}
