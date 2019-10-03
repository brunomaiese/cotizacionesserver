package db.daos;

import db.entidades.CasaCambiaria;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CasaCambiariaDao extends AbstractDao<Long, CasaCambiaria> {

    @PersistenceContext(unitName = "PG_COTIZACIONES")
    public EntityManager entityManager;

    @Override
    public EntityManager getEm() {
        return entityManager;
    }

    @Override
    public Class<CasaCambiaria> getDaoClass() {
        return CasaCambiaria.class;
    }


}
