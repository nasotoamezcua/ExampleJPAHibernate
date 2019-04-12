package com.soto.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soto.repository.entity.Direccion;

@Repository
public interface IDireccionDAO extends JpaRepository<Direccion, Long> {

}
