package com.revature.trms.service;

import com.revature.trms.bean.GradeFormat;
import com.revature.trms.dao.GradeFormatDao;
import com.revature.trms.dao.GradeFormatDaoJDBC;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

public class GradeFormatService {

  public static String getAllGradeFormats() {
    List<GradeFormat> formats = getGradeFormats();
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(formats);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  private static List<GradeFormat> getGradeFormats() {
    GradeFormatDao gfd = new GradeFormatDaoJDBC();
    return gfd.selectAllGradeFormats();
  }
}
