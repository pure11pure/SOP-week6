package com.example.week6.view;


import com.example.week6.pojo.Wizard;
import com.example.week6.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


//http://localhost:8080/mainPage.it
@Route(value = "mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField t_Fullname, t_dollar;
    private RadioButtonGroup<String> rd_gender;
    private ComboBox cb_position, cb_school, cb_house;

    private Button btLeft, btCreate, btUpdate, btDelete , btRight;

    private Wizards wizards;

    private int index;

    private Notification nf;

    public MainWizardView(){

        this.wizards = WebClient.create()
                .get()
                .uri("http://localhost:8080/wizards")
                .retrieve()
                .bodyToMono(Wizards.class)
                .block();

        this.t_Fullname = new TextField();
        this.t_Fullname.setPlaceholder("Fullname");

        this.rd_gender = new RadioButtonGroup<>();
        this.rd_gender.setLabel("Gender: ");
        this.rd_gender.setItems("Male", "Female");

        this.cb_position = new ComboBox();
        this.cb_position.setPlaceholder("Position");
        this.cb_position.setItems("Student", "Teacher");

        this.t_dollar = new TextField("Dollars");
        this.t_dollar.setPrefixComponent(new Span("$"));

        this.cb_school = new ComboBox();
        this.cb_school.setPlaceholder("School");
        this.cb_school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");

        this.cb_house = new ComboBox();
        this.cb_house.setPlaceholder("House");
        this.cb_house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin");

        this.btLeft = new Button("<<");
        this.btCreate = new Button("Create");
        this.btUpdate = new Button("Update");
        this.btDelete = new Button("Delete");
        this.btRight = new Button(">>");

        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(btLeft, btCreate, btUpdate, btDelete, btRight);

        this.nf = new Notification();
        this.nf.setDuration(3000);

        this.btLeft.addClickListener(event -> {
           if(index > 0){
               index --;
               setData(index);
           }
        });

        this.btRight.addClickListener(event -> {
            if(index < this.wizards.wizards.size() - 1){
                index ++;
                setData(index);
            }
        });

        this.btCreate.addClickListener(event -> {
           //ส่งค่าหลายค่า
            MultiValueMap<String , String> formData = new LinkedMultiValueMap<>();
            formData.add("fullName", this.t_Fullname.getValue());
            formData.add("sex", this.rd_gender.getValue());
            formData.add("position", (String) this.cb_position.getValue());
            formData.add("dollar", this.t_dollar.getValue());
            formData.add("school", (String) this.cb_school.getValue());
            formData.add("house", (String) this.cb_house.getValue());

            Wizard out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addWizard")
                    //post ส่งผ่าน body
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(Wizard.class)
                    .block();
            this.wizards.wizards.add(out);
            this.nf.setText("Wizard has been Created");
            this.nf.open();

        });

        this.btUpdate.addClickListener(event -> {
            //ส่งค่าหลายค่า
            MultiValueMap<String , String> formData = new LinkedMultiValueMap<>();
            formData.add("fullName", this.t_Fullname.getValue());
            formData.add("sex", this.rd_gender.getValue());
            formData.add("position", (String) this.cb_position.getValue());
            formData.add("money", this.t_dollar.getValue());
            formData.add("school", (String) this.cb_school.getValue());
            formData.add("house", (String) this.cb_house.getValue());

            Wizard out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/updateWizard")
                    //post ส่งผ่าน body
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(Wizard.class)
                    .block();
//            this.t_Fullname.setValue(String.valueOf(out));
            this.wizards.wizards.set(this.index, out);
            this.nf.setText("Wizard has been Update");
            this.nf.open();

        });

        this.btDelete.addClickListener(event -> {
            MultiValueMap<String , String> formData = new LinkedMultiValueMap<>();
            formData.add("_id", this.wizards.wizards.get(this.index).get_id());

            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard")
                    //post ส่งผ่าน body
//                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            if (out.equals("Delete Success")) {
                this.wizards.wizards.remove(this.index);
                this.nf.setText("Wizard has been removed");
                this.nf.open();
            }
        });

        setData(0);

        this.add(t_Fullname, rd_gender, cb_position, t_dollar, cb_school, cb_house, h1);
    }


    //set ค่าเริ่มต้น
    public void setData(int num){
        //type  object  =  this.object.attribute
        Wizard wizard = this.wizards.wizards.get(num);
        this.t_Fullname.setValue(wizard.getName());
        this.rd_gender.setValue(wizard.getSex().equals("m") ? "Male" : "Female");
        this.cb_position.setValue(wizard.getPosition());
        this.t_dollar.setValue(String.valueOf(wizard.getMoney()));
        this.cb_school.setValue(wizard.getSchool());
        this.cb_house.setValue(wizard.getHouse());

    }

}
