package com.example.week6.repository;

import com.example.week6.pojo.Wizard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository //ใช้ระบุว่า Object ของคลาสนี้เป็น Spring bean มีหน้าที่จัดส่วน Persistance Layer
public interface WizardRepository extends MongoRepository<Wizard, String> {

    //เอาไว้สร้าง query ที่ไม่ได้มีมาให้
}
