package db.daos;

import db.entidades.CasaCambiaria;
import db.entidades.Direccion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DireccionDao extends AbstractDao<Long, Direccion> {

    @PersistenceContext(unitName = "PG_COTIZACIONES")
    public EntityManager entityManager;

    @Override
    public EntityManager getEm() {
        return entityManager;
    }

    @Override
    public Class<Direccion> getDaoClass() {
        return Direccion.class;
    }


}
