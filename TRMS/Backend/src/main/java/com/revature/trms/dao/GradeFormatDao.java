package com.revature.trms.dao;

import com.revature.trms.bean.GradeFormat;
import java.util.List;

public interface GradeFormatDao {

  List<GradeFormat> selectAllGradeFormats();
}
