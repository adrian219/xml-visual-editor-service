package pl.adrianwieczorek.xmlvisualeditorservice.contant;

public enum UserRole {
  USER("User default group"), ADMIN("Admin default group");
  private String description;

  UserRole(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }
}
