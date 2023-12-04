package br.udesc.weparty.Model;

public class UploadResponse {

    private String image;

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "imageUrl='" + image + '\'' +
                '}';
    }
}
