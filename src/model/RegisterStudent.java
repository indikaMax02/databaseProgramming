package model;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

public class RegisterStudent {
    private String registerId;
    private String id;
    private String name;
    private String date;
    private String email;
    private String contact;
    private String address;
    private String nic;
    private String course;
    private String intake;
    private double payment;
    private String cost;

    public RegisterStudent() {
    }

    public RegisterStudent(String registerId, String id, String name, String date, String email, String contact, String address, String nic, String course, String intake, double payment, String cost) {
        this.registerId = registerId;
        this.id = id;
        this.name = name;
        this.date = date;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.nic = nic;
        this.course = course;
        this.intake = intake;
        this.payment = payment;
        this.cost = cost;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "RegisterStudent{" +
                "registerId='" + registerId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", course='" + course + '\'' +
                ", intake='" + intake + '\'' +
                ", payment=" + payment +
                ", cost=" + cost +
                '}';
    }
}
