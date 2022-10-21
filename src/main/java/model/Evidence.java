package model;

public class Evidence {
    private String description;
    private byte[] image;
    

    public Evidence(byte[] image, String description){
        this.description = description;
        this.image = image;
       
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }
 
}
