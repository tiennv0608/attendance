package com.example.attendance.service.classes;

import com.example.attendance.model.Classes;
import com.example.attendance.service.IGeneralService;

public interface IClassesService extends IGeneralService<Classes> {

    Boolean existsByName(String name);

}
