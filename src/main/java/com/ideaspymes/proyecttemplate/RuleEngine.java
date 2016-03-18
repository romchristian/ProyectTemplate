/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideaspymes.proyecttemplate;


import com.ideaspymes.proyecttemplate.configuracion.Producto;
import com.ideaspymes.proyecttemplate.configuracion.Resultado;
import java.io.UnsupportedEncodingException;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

/**
 *
 * @author cromero
 */
@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class RuleEngine {

    public String getKieSession(String condicion, String consecuencia) throws UnsupportedEncodingException {

        String dslr = "package com.ideaspymes.proyecttemplate;\n"
                + "import com.ideaspymes.proyecttemplate.model.*;\n"
                + "\n"
                + "expander dslExample.dsl\n"
                + " \n"
                + "rule \"Cuanto vendi\"\n"
                + "  when\n"
                + condicion + " \n"
                + " devuelve resultado \n"
                + "  then\n"
                + consecuencia + " \n"
                + "end";

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("dslExample.dsl"), ResourceType.DSL);
        //kbuilder.add(ResourceFactory.newClassPathResource("dslrExample.dslr"), ResourceType.DSLR);
        //ByteArrayResource dslResource =  (ByteArrayResource) ResourceFactory.newByteArrayResource(dsl.getBytes("UTF-8"));

        kbuilder.add(ResourceFactory.newByteArrayResource(dslr.getBytes("UTF-8")), ResourceType.DSLR);
        if (kbuilder.hasErrors()) {
            System.out.println("Hubo errores al compilar las reglas!!! : " + kbuilder.getErrors().toString());
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        Resultado R = new Resultado();
        ksession.insert(R);
        ksession.insert(new Producto("Palermo", 1000D, 50000000D));
        ksession.insert(new Producto("Kentucky", 3000D, 50000000D));
        int rulesFired = ksession.fireAllRules();
        System.out.println("Rules Fired: " + rulesFired);

        return R.getMensaje();
    }
}
