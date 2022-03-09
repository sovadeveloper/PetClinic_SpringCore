package com.sovadeveloper;

import com.sovadeveloper.config.SpringConfig;
import com.sovadeveloper.entities.PetTypeEntity;
import com.sovadeveloper.services.PetTypeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PetClinic {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        PetTypeService petTypeService = applicationContext.getBean("petTypeService", PetTypeService.class);

        PetTypeEntity petTypeEntity = new PetTypeEntity();
        petTypeEntity.setName("Собака");

        System.out.println("123");
        petTypeService.add(petTypeEntity);
    }
}
