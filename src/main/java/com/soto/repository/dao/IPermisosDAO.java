package com.soto.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soto.repository.entity.Permiso;

@Repository
public interface IPermisosDAO extends JpaRepository<Permiso, Long> {

}
