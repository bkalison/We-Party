package br.udesc.weparty.Model;

public class UploadResponse {

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "UploadResponse{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
