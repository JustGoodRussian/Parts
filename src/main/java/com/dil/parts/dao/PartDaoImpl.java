package com.dil.parts.dao;

import com.dil.parts.model.Part;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PartDaoImpl implements PartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
    }

    @Override
    public void updatePart(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    @Override
    public void removePart(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = session.get(Part.class, new Integer(id));

        if (part != null) {
            session.delete(part);
        }
    }

    @Override
    public Part getPartById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Part part = session.get(Part.class, new Integer(id));
        return part;
    }

    @Override
    public List<Part> getPartsByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Part where lower(title) like lower(:title)");
        query.setParameter("title", "%" + title + "%");
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Integer getNumOfParts(String typeSort) {
        Session session = sessionFactory.getCurrentSession();

        if (typeSort.equals("necessity")) {
            return session.createQuery("from Part where necessity = 1").list().size();
        }
        else if (typeSort.equals("auxiliary")) {
            return  session.createQuery("from Part where necessity = 0").list().size();
        }
        else {
            return session.createQuery("from Part").list().size();
        }
    }

    @Override
    public Integer getMaxAssembling() {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.createQuery("select min (p.quantity) from Part p where p.necessity = 1").list().get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Part> getParts(String typeSort, int page) {
        Session session = sessionFactory.getCurrentSession();
        List<Part> parts;
        if (typeSort.equals("necessity")) {
            parts = (List<Part>) session.createQuery("from Part where necessity = 1").list();
        }
        else if (typeSort.equals("auxiliary")) {
            parts = (List<Part>) session.createQuery("from Part where necessity = 0").list();
        }
        else if (typeSort.equals("all")) {
            parts = (List<Part>) session.createQuery("from Part").list();
        }
        else {
            Query query = session.createQuery("from Part where title = :partName");
            query.setParameter("partName", typeSort);
            parts = (List<Part>) query.list();
        }

        if (parts.size() > 10) {
            return parts.subList((page - 1) * 10, ((page * 10) > parts.size() - 1)? parts.size(): page * 10);
        }
        else {
            return parts;
        }
    }
}
