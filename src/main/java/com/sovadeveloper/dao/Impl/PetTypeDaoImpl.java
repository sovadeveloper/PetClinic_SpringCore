package com.sovadeveloper.dao.Impl;

import com.sovadeveloper.dao.PetTypeDao;
import com.sovadeveloper.entities.PetTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("petTypeDaoImpl")
public class PetTypeDaoImpl implements PetTypeDao {
    private final HibernateTemplate ht;

    @Autowired
    public PetTypeDaoImpl(HibernateTemplate ht) {
        this.ht = ht;
    }

    @Override
    @Transactional(readOnly = false)
    public PetTypeEntity save(PetTypeEntity petTypeEntity) {
        return (PetTypeEntity) ht.save(petTypeEntity);
    }

    @Override
    public void update(PetTypeEntity petTypeEntity) {
        ht.update(petTypeEntity);
    }

    @Override
    public void delete(Long id) {
        ht.delete(id);
    }

    @Override
    public PetTypeEntity getById(Long id) {
        return ht.get(PetTypeEntity.class, id);
    }

    @Override
    public List<PetTypeEntity> getAll() {
        return ht.loadAll(PetTypeEntity.class);
    }
}
