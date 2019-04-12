package com.soto.service.assembler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

public abstract class Assembler<D, M> {

    /**
     * Transforms entity to DTO
     * @param mapping - Entity object
     * @return DTO object
     * @since 1.0
     */
    public abstract D getDTOTransform(M mapping);

    /**
     * Transforms a DTO to entity
     * @param dto - DTO object
     * @return  Entity object
     * @since 1.0
     */
    public abstract M getMappingTransform(D dto);

   
    /**
     * Transforms a list of entities to a list of DTOs
     * @param mappingList the entity list
     * @return the dto list
     */
    public List<D> getDTOListTransform(List<M> mappingList) {
        List<D> listaDTO = null;
        if (!CollectionUtils.isEmpty(mappingList)) {
            listaDTO = new ArrayList<D>();
            for (M mapping : mappingList) {
                D entity = this.getDTOTransform(mapping);
                listaDTO.add(entity);
            }
        }
        return listaDTO;
    }

    /**
     * Transforms a list of DTO object to a list of entity objects
     * @param dtoList the list
     * @return the entities lists
     */
    public List<M> getMappingListTransform(List<D> dtoList) {
        List<M> listaMapping = null;
        if (!CollectionUtils.isEmpty(dtoList)) {
            listaMapping = new ArrayList<M>();
            for (D dto : dtoList) {
                M mapping = this.getMappingTransform(dto);
                listaMapping.add(mapping);
            }
        }
        return listaMapping;
    }

  
    /**
     * Transforms a set of entity objects to a set of dto objects
     * @param mappingSet the set
     * @return a set of dto objects
     */
    public Set<D> getDTOSetTransform(Set<M> mappingSet) {
        Set<D> setDTO = null;
        if (!CollectionUtils.isEmpty(mappingSet)) {
            setDTO = new HashSet<D>();
            for (M mapping : mappingSet) {
                D dto = this.getDTOTransform(mapping);
                setDTO.add(dto);
            }
        }
        return setDTO;
    }

   
    /**
     * Transforms a set of dto objects to a set of entity objects
     * @param dtoSet the set of dto
     * @return the set of entity objects
     */
    public Set<M> getMappingSetTransform(Set<D> dtoSet) {
        Set<M> setMapping = null;
        if (!CollectionUtils.isEmpty(dtoSet)) {
            setMapping = new HashSet<M>();
            for (D dto : dtoSet) {
                M mapping = this.getMappingTransform(dto);
                setMapping.add(mapping);
            }
        }
        return setMapping;
    }
}