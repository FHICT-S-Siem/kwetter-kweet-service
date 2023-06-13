package siem.kwetter.kweet;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.Base64;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kweet extends PanacheEntity {
    @Column(nullable = false)
    public String message;

//    @Lob
//    @Column(columnDefinition = "BLOB")
//    public byte[] picture;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public byte[] getPicture() {
//        return picture;
//    }
//
//    public void setPicture(String base64Picture) {
//        this.picture = convertBase64ToByteArray(base64Picture);
//    }
//
//    private byte[] convertBase64ToByteArray(String base64Image) {
//        String base64ImageString = base64Image.split(",")[1]; // Remove the data URL prefix
//        return Base64.getDecoder().decode(base64ImageString);
//    }
}
