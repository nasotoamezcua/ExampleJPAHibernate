package com.soto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soto.beans.DireccionBean;
import com.soto.repository.dao.IDireccionDAO;
import com.soto.service.IDireccionBeanService;

@Service
public class DireccionBeanService implements IDireccionBeanService{
	
	@Autowired
	private IDireccionDAO dao;

	@Override
	public List<DireccionBean> getFindByCpOtraQueyNativeService(String codigoPostal) {
		List<DireccionBean> direcciones = new ArrayList<DireccionBean>();
		List<Object[]> list = dao.getFindByCpOtraQueyNative(codigoPostal);
		for(Object[] ob : list){
			direcciones.add(new DireccionBean((String) ob[1],(String) ob[2]));
		}
		return direcciones;
	}

}
