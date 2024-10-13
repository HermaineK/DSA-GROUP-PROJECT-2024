package pba;

public class Contact {
    private String name;
    private String phoneNumber;
    private boolean isFavorite; // Favorite field (Checkmark for UI)
    private String group; // Group field

    public Contact(String name, String phoneNumber, boolean isFavorite, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isFavorite = isFavorite; // Checkbox to indicate if favorite
        this.group = group; // Group
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getGroup() {
        return group;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
