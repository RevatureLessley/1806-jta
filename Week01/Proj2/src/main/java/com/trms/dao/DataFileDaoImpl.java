package com.trms.dao;

import static com.trms.util.Convert.blobToBytes;
import static com.trms.util.Convert.bytesToBlob;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.DataFile;
import com.trms.util.SqlBuilder;;

public class DataFileDaoImpl extends GenericDaoImpl<DataFile> implements Dao<DataFile, Integer> {
	final String tName = "DataFile", idField = "fileID";
	final Integer col = 5;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	
	Function<Object[], DataFile> objectReturnBehaviour = 
			item -> new DataFile(
					(int) item[0], //    fileID number(6),
					(int) item[1], //    requestID number(6),
					item[3].toString(), //    fileType varchar2(15),
					item[4].toString(), //    filePath varchar2(1024),
					blobToBytes(item[5])//    data Blob,
					);
			

	
	@Override
	public List<DataFile> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public DataFile selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<DataFile, List<Object>> loadingBehaviour =
			item -> Arrays.asList(
					item.getRequestID(),//    requestID number(6),
					item.getFile().getName(),//    fileName varchar2(500),
					item.getFileType(),//    fileType varchar2(15),
					item.getFile().getAbsolutePath(),//    filePath varchar2(1024),
					bytesToBlob(item.getData())
					);
	
	@Override
	public Boolean insertNew(DataFile t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(DataFile t) {
		return callProcedure(t, sb.callUpdate(tName, t.getFileID(), col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<DataFile> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
