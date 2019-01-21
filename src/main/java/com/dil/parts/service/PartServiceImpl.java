package com.dil.parts.service;

import com.dil.parts.dao.PartDao;
import com.dil.parts.model.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    public void setPartDao(PartDao partDao) {
        this.partDao = partDao;
    }

    @Override
    @Transactional
    public void addPart(Part part) {
        partDao.addPart(part);
    }

    @Override
    @Transactional
    public void updatePart(Part part) {
        partDao.updatePart(part);
    }

    @Override
    @Transactional
    public void removePart(int id) {
        partDao.removePart(id);
    }

    @Override
    @Transactional
    public Part getPartById(int id) {
        return partDao.getPartById(id);
    }

    @Override
    @Transactional
    public List<Part> getPartsByTitle(String title) {
        return partDao.getPartsByTitle(title);
    }

    @Override
    @Transactional
    public Integer getNumOfParts(String typeSort) {
        return partDao.getNumOfParts(typeSort);
    }

    @Override
    @Transactional
    public Integer getMaxAssembling() {
        return partDao.getMaxAssembling();
    }

    @Override
    @Transactional
    public List<Part> getParts(String typeSort, int page) {
        return partDao.getParts(typeSort, page);
    }
}
