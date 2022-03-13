package com.sovadeveloper;

import com.sovadeveloper.config.SpringConfig;
import com.sovadeveloper.entities.PetTypeEntity;
import com.sovadeveloper.services.Impl.PetTypeServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class PetClinic {
    public static void main(String[] args) throws Exception {
//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(SpringConfig.class);
//
//        PetTypeServiceImpl petTypeService = applicationContext.getBean(PetTypeServiceImpl.class);
//
//        PetTypeEntity petTypeEntity = new PetTypeEntity();
//        petTypeEntity.setName("JpaEntityAdd11");
//
//        System.out.println("123");
//        petTypeService.create(petTypeEntity);

        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
    }
}
