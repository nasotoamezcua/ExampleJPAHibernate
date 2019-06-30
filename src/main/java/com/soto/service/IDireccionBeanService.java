package com.soto.service;

import java.util.List;

import com.soto.beans.DireccionBean;

public interface IDireccionBeanService {
	
	List<DireccionBean> getFindByCpOtraQueyNativeService(String codigoPostal);

}
