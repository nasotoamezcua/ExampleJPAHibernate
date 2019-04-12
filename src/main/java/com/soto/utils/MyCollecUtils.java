package com.soto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class MyCollecUtils {
	
	/**
	 * Convertit un Set a un List
	 * @param set
	 * @return
	 */
	public static <T> List<T> toList(Set<T> set) {
		return new ArrayList<>(set);
	}
	
	/**
	 * Converit un List a un Set
	 * @param list
	 * @return
	 */
	public static <T> Set<T> toSet(List<T> list) {
		return new HashSet<T>(list);
	}
	
	/**
	 * Converit un List a un Set y ordenar el set
	 * @param list
	 * @return
	 */
	public static <T> Set<T> toSetOrder(List<T> list) {
		return new LinkedHashSet<T>(list);
	}
	
	/**
	 * Elimimar los registros repetidos en una lista y ordenarlos
	 * @param list
	 * @return
	 */
	public static <T> List<T> sinDuplicar(List<T> list){
		return list.stream().distinct().collect(Collectors.toList());
	}

}
