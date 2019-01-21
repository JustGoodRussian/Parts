package com.dil.parts.service;

import com.dil.parts.model.Part;

import java.util.List;

public interface PartService {

    void addPart(Part part);

    void updatePart(Part part);

    void removePart(int id);

    Part getPartById(int id);

    Integer getNumOfParts(String typeSort);

    Integer getMaxAssembling();

    List<Part> getPartsByTitle(String title);

    List<Part> getParts(String typeSort, int page);

}
