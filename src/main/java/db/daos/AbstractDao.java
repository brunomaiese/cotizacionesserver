package db.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDao<K, E> {

    public AbstractDao() {
    }

    public abstract EntityManager getEm();

    public abstract Class<E> getDaoClass();

    public E find(K id) {
        return this.getEm().find(this.getDaoClass(), id);
    }

    public E merge(E e) {
        return this.getEm().merge(e);
    }

    public void create(E e) {
        this.getEm().persist(e);
    }

    public void refresh(E e) {
        this.getEm().refresh(e);
    }

    public void remove(E e) {
        this.getEm().remove(e);
    }

    public E findSingleResultByField(String field, Object value) {
        try {
            CriteriaBuilder criteriaBuilder = this.getEm().getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
            Root entity = criteriaQuery.from(getDaoClass());
            criteriaQuery.select(entity);
            criteriaQuery.where(criteriaBuilder.equal(entity.get(field), criteriaBuilder.parameter(value.getClass(), field)));
            Query query = this.getEm().createQuery(criteriaQuery);
            query.setParameter(field, value);
            return (E) query.getSingleResult();
        } catch (NoResultException no) {
            return null;
        } catch (NonUniqueResultException notunique) {
            notunique.printStackTrace();
            return null;
        }
    }

    public List<E> findByField(String field, String value) {
        CriteriaBuilder criteriaBuilder = this.getEm().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root entity = criteriaQuery.from(getDaoClass());
        criteriaQuery.select(entity);
        criteriaQuery.where(criteriaBuilder.equal(entity.get(field), criteriaBuilder.parameter(value.getClass(), field)));
        Query query = this.getEm().createQuery(criteriaQuery);
        query.setParameter(field, value);
        return (List<E>) query.getResultList();

    }

    public List<E> findAll() {
        Query query = this.getEm().createQuery(
                "select e from " + getDaoClass().getSimpleName() + " e");
        return (List<E>) query.getResultList();
    }
}
