package com.example.week6.pojo;

import java.io.Serializable;
import java.util.ArrayList;


public class Wizards implements Serializable {
    //เป็นมาตรฐานในการจัดเก็บและส่งอ็อบเจกต์ใน Java

    //เก็บข้อมูลเกี่ยวกับพ่อมด (Wizards) ที่ถูกดึงมาจากฐานข้อมูลหรืออื่น ๆ
    //สิ่งที่คลาสนี้ทำคือกำหนดโครงสร้างข้อมูลที่จะถูกใช้ในรูปแบบของ ArrayList ของอ็อบเจกต์ Wizard แล้วรวมไว้ในอ็อบเจกต์ชื่อ wizards
    //เพื่อให้ง่ายต่อการเข้าถึงและใช้ข้อมูล
    public ArrayList<Wizard> wizards;
    //Wizards เป็นโครงสร้างข้อมูลที่ถูกใช้ในการเก็บรายการของพ่อมด
    //โดย wizards เป็นอาร์เรย์หรือรายการของอ็อบเจกต์ (Wizard)
}
