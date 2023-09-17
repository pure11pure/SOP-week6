package com.example.week6.repository;

import com.example.week6.pojo.Wizard;
import com.example.week6.pojo.Wizards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WizardService {
    //ให้ไปหา Object ที่เกี่ยวข้องมาใส่ *ต่อให้เปลี่ยน db หน้านี้ไม่กระทบ
    @Autowired
    private WizardRepository repository;

//    // Constructor รับค่า
//    public WizardService(WizardRepository repository){
//        this.repository = repository;
//    }


    public Wizards getAllWizards(){
        //เริ่มต้นด้วยการสร้างอ็อบเจกต์ของ Wizards เพื่อเตรียมตัวให้พร้อมในการเก็บข้อมูลพ่อมดทั้งหมด
        Wizards wizards = new Wizards();
        //เราใช้ Repository ที่เชื่อมต่อกับฐานข้อมูล MongoDB ซึ่งอาจเป็น
        //Object.attribute จาก wizards
        //(ArrayList<Wizard>) ทำการแปลงผลลัพธ์ให้อยู่ในรูปแบบของ ArrayList<Wizard>
        wizards.wizards = (ArrayList<Wizard>) this.repository.findAll();
        //ส่ง Object ออกไป
        return wizards;
    }

    //add
    public void addWizard(Wizard wizard){
        repository.insert(wizard);
    }

    //update
    public void updateWizard(Wizard wizard){
        repository.save(wizard);
    }

    //delete
    public void deleteWizard(String _id){
        repository.deleteById(_id);
    }


}
