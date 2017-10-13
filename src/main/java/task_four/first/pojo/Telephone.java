package task_four.first.pojo;

public class Telephone {
    private Integer phone;
    private String nameOwner;

    public Telephone(Integer phone, String nameOwner) {
        this.phone = phone;
        this.nameOwner = nameOwner;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }
}
