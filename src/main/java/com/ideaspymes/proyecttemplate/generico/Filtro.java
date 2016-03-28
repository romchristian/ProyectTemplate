/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate.generico;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 *
 * @author Christian
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD})
public @interface Filtro {
     /**
     * Tipo: like,rangoFecha,rangoNumero,selectEnum,selectEntidad,autocomplete
     * @return 
     */
    @Nonbinding
    String tipo() default "";

    /**
     * Defines if value for the given key must be defined.
     * @return 
     */
    @Nonbinding
    String campo() default "";
    
    
    @Nonbinding
    String descripcion() default "";
    
    
}
